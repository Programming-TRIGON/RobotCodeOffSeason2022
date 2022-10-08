package frc.trigon.robot.subsystems.ballscounter;

import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.trigon.robot.subsystems.shooter.Shooter;

public class CountBallsCommand extends CommandBase {
    private boolean ballInAlready = false;
    BallsCounter ballsCounter = BallsCounter.getInstance();

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
        if(Shooter.getInstance().hasShotBall()) {
            BallsCounter.getInstance().pushBalls();
            Shooter.getInstance().resetBallFlag();
        }
    }
    //
    //    private void addBall() {
    //        if(firstBall == null) {
    //            firstBall = getColor();
    //            new WaitUntilCommand(this::isBallOut).andThen(
    //                    Loader.getInstance().getLoadCommand().until(() -> !ballInLoader.get())
    //            ).schedule();
    //        } else if(secondBall == null) {
    //            secondBall = getColor();
    //        } else {
    //            System.out.println("Balls are full! Pushing anyway!");
    //            pushBalls();
    //            secondBall = getColor();
    //        }
    //    }

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
