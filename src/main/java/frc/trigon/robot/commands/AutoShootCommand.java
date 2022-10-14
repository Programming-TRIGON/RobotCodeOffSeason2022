package frc.trigon.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.button.Button;
import frc.trigon.robot.RobotContainer;
import frc.trigon.robot.subsystems.backspinreducer.BackspinReducer;
import frc.trigon.robot.subsystems.loader.Loader;
import frc.trigon.robot.subsystems.pitcher.Pitcher;
import frc.trigon.robot.subsystems.shooter.Shooter;
import frc.trigon.robot.subsystems.transporter.Transporter;

public class AutoShootCommand extends ParallelCommandGroup {
    Button canShootBtn;

    public AutoShootCommand() {
        super(
                Commands.getPrimeShooterByLimelightCommand(),
                Commands.getPitchByLimelightCommand(),
                Commands.getTurnToLimelight0Command()
        );
        canShootBtn = new Button(() -> canShoot() && isScheduled());
        canShootBtn.whileHeld(
                parallel(
                        Loader.getInstance().getLoadCommand(),
                        Transporter.getInstance().getLoadCommand(),
                        BackspinReducer.getInstance().getReducerCommand()
                )
        );
    }

    private boolean canShoot() {
        return Shooter.getInstance().shotsDetectorCommand.getIsStable() &&
                Pitcher.getInstance().atTargetAngle() &&
                RobotContainer.hubLimelight.isCentered();
    }
}
