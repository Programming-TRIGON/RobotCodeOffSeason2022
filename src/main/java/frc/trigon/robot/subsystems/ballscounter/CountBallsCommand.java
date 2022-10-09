package frc.trigon.robot.subsystems.ballscounter;

import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.Button;
import frc.trigon.robot.subsystems.shooter.Shooter;

public class CountBallsCommand extends CommandBase {
    private boolean ballInAlready = false;
    BallsCounter ballsCounter;
    Button shotBallBtn = new Button(
            () -> Shooter.getInstance().wasInSetpoint && !Shooter.getInstance().shotsDetectorCommand.getIsStable());

    public CountBallsCommand() {
        ballsCounter = BallsCounter.getInstance();
        shotBallBtn.whenPressed(() -> BallsCounter.getInstance().pushBalls());
    }

    @Override
    public void initialize() {
        ballInAlready = false;
    }

    @Override
    public void execute() {
        if(!ballInAlready) {
            if(ballsCounter.isBallIn()) {
                ballInAlready = true;
                BallsCounter.getInstance().registerCurrentBall();
            }
        } else {
            if(ballsCounter.isBallOut()) {
                ballInAlready = false;
            }
        }
    }

    @Override
    public boolean runsWhenDisabled() {
        return true;
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        super.initSendable(builder);
        builder.addBooleanProperty("Ball in already", () -> ballInAlready, null);
    }
}
