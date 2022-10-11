package frc.trigon.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.trigon.robot.subsystems.loader.Loader;
import frc.trigon.robot.subsystems.shooter.Shooter;
import frc.trigon.robot.subsystems.shooter.ShooterConstants;
import frc.trigon.robot.subsystems.transporter.Transporter;

public class CloseShootingCommand extends SequentialCommandGroup {
    public CloseShootingCommand() {
        super(Shooter.getInstance().getPrimeShooterCommand(() -> ShooterConstants.CLOSE_SHOOTING_TARGET_VELOCITY),
                Loader.getInstance().getLoadCommand().alongWith(Transporter.getInstance().getLoadCommand()));
    }
}