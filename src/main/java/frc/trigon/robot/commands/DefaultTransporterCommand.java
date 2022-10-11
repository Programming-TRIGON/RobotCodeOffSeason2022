package frc.trigon.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.trigon.robot.subsystems.ballscounter.BallsCounter;
import frc.trigon.robot.subsystems.transporter.Transporter;

public class DefaultTransporterCommand extends ParallelCommandGroup {
    public DefaultTransporterCommand() {
        super();
        if(!BallsCounter.getInstance().getSecondBall().equals("")) {
            Commands.runCommandWhile(
                    this,
                    () -> !BallsCounter.getInstance().isTouchingBall(),
                    Transporter.getInstance().getDefaultCommand()
            );
        } else {
            Commands.runCommandWhile(
                    this,
                    () -> !BallsCounter.getInstance().isLoaderSwitchHeld(),
                    Transporter.getInstance().getDefaultCommand()
            );
        }
    }
}