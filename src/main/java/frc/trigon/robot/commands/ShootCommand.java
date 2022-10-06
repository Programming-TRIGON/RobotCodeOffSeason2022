package frc.trigon.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import frc.trigon.robot.subsystems.backspinreducer.BackspinReducer;
import frc.trigon.robot.subsystems.loader.Loader;
import frc.trigon.robot.subsystems.pitcher.Pitcher;
import frc.trigon.robot.subsystems.shooter.Shooter;

public class ShootCommand extends ParallelCommandGroup {
    private static final double
            maxAngleError = 1,
            maxClosedLoopError = 2;

    public ShootCommand() {
        super(
                Shooter.getInstance().getPrimeShooterCommand(() -> 3500),
                Pitcher.getInstance().getPitchingCommand(() -> 3500),
                new WaitUntilCommand(ShootCommand::atRequiredError).andThen(
                        BackspinReducer.getInstance().getReducerCommand(), Loader.getInstance().getLoadCommand()));
    }

    private static boolean atRequiredError() {
        return Math.abs(Pitcher.getInstance().getError()) <= maxAngleError && Math.abs(
                Shooter.getInstance().getError()) <= maxClosedLoopError;
    }
}