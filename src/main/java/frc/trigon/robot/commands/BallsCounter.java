package frc.trigon.robot.commands;

import com.revrobotics.ColorSensorV3;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import frc.trigon.robot.subsystems.loader.Loader;
import frc.trigon.robot.subsystems.shooter.Shooter;

public class BallsCounter extends CommandBase {
    private final static BallsCounter INSTANCE = new BallsCounter();

    private boolean ballInAlready = false;
    public static final ColorSensorV3 colorSensor = new ColorSensorV3(I2C.Port.kOnboard);
    public static final DigitalInput ballInLoader = new DigitalInput(0);
    public static String
            firstBall = null,
            secondBall = null;

    private static final int
            BALL_IN_THRESHOLD = 900,
            BALL_OUT_THRESHOLD = 150;

    public static BallsCounter getInstance() {
        return INSTANCE;
    }

    private BallsCounter() {
    }

    @Override
    public void initialize() {
        ballInAlready = false;
        firstBall = secondBall = null;
    }

    @Override
    public void execute() {
        if(!ballInAlready) {
            if(isBallIn()) {
                ballInAlready = true;
                addBall();
            }
        } else {
            if(isBallOut()) {
                ballInAlready = false;
            }
        }
        if(Shooter.getInstance().hasShotBall()) {
            pushBalls();
            Shooter.getInstance().resetBallFlag();
        }
    }

    private void addBall() {
        if(firstBall == null) {
            firstBall = getColor();
            new WaitUntilCommand(this::isBallOut).andThen(
                    Loader.getInstance().getLoadCommand().until(() -> !ballInLoader.get())
            ).schedule();
        } else if(secondBall == null) {
            secondBall = getColor();
        } else {
            System.out.println("Balls are full! Pushing anyway!");
            pushBalls();
            secondBall = getColor();
        }
    }

    private void pushBalls() {
        if(firstBall == null) {
            System.out.println("No balls to push!");
        } else if(secondBall == null) {
            System.out.println("Pushing " + firstBall + " ball!");
            firstBall = null;
        } else {
            System.out.println("Pushing " + firstBall + " and " + secondBall + " balls!");
            firstBall = secondBall;
            secondBall = null;
        }
    }

    private String getColor() {
        return colorSensor.getRed() > colorSensor.getBlue() ? "red" : "blue";
    }

    private boolean isBallIn() {
        return colorSensor.getProximity() > BALL_IN_THRESHOLD;
    }

    private boolean isBallOut() {
        return colorSensor.getProximity() < BALL_OUT_THRESHOLD;
    }

    @Override
    public boolean isFinished() {
        // TODO: Make this return true when this Command no longer needs to run execute()
        return false;
    }

    @Override
    public void end(boolean interrupted) {

    }

    @Override
    public void initSendable(SendableBuilder builder) {
        super.initSendable(builder);
        builder.addBooleanProperty("Is Ball In already", () -> ballInAlready, null);
        builder.addBooleanProperty("Ball In", this::isBallIn, null);
        builder.addBooleanProperty("Ball Out", this::isBallOut, null);
        builder.addStringProperty("First Ball", () -> deNull(firstBall), null);
        builder.addStringProperty("Second Ball", () -> deNull(secondBall), null);
        builder.addStringProperty("Color", () -> deNull(getColor()), null);
        builder.addDoubleProperty("Proximity", colorSensor::getProximity, null);
        builder.addDoubleProperty("Red", colorSensor::getRed, null);
        builder.addDoubleProperty("Blue", colorSensor::getBlue, null);
    }

    @Override
    public boolean runsWhenDisabled() {
        return true;
    }

    private String deNull(String str) {
        if(str == null)
            return "";
        return str;
    }
}
