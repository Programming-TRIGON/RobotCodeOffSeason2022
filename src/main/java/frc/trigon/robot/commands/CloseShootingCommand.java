package frc.trigon.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.button.Button;
import frc.trigon.robot.subsystems.loader.Loader;
import frc.trigon.robot.subsystems.pitcher.Pitcher;
import frc.trigon.robot.subsystems.pitcher.PitcherConstants;
import frc.trigon.robot.subsystems.shooter.Shooter;
import frc.trigon.robot.subsystems.shooter.ShooterConstants;
import frc.trigon.robot.subsystems.transporter.Transporter;

public class CloseShootingCommand extends ParallelCommandGroup {
    Button canShootBtn;

    public CloseShootingCommand() {
        super();
        canShootBtn = new Button(() -> canShoot() && isScheduled());
        addCommands(
                Shooter.getInstance().getPrimeShooterCommand(() -> ShooterConstants.CLOSE_SHOOTING_TARGET_VELOCITY),
                Pitcher.getInstance().getPitchingCommand(() -> PitcherConstants.CLOSE_SHOOTING_TARGET_ANGLE)
        );
        canShootBtn.whileHeld(
                Loader.getInstance().getLoadCommand().alongWith(Transporter.getInstance().getLoadCommand())
        );
        canShootBtn.whenReleased(
                Loader.getInstance().getLoadCommand().withInterrupt(() -> !isScheduled()).withTimeout(0.5));
    }

    private boolean canShoot() {
        return Shooter.getInstance().shotsDetectorCommand.getIsStable() && Pitcher.getInstance().atTargetAngle();
    }
}
