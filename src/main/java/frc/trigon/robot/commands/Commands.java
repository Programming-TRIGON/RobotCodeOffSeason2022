package frc.trigon.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.trigon.robot.RobotContainer;
import frc.trigon.robot.subsystems.pitcher.Pitcher;
import frc.trigon.robot.subsystems.shooter.Shooter;
import frc.trigon.robot.subsystems.swerve.TurnToTargetCommand;
import frc.trigon.robot.utilities.ShootingCalculations;

public class Commands {
    public static CommandBase getPrimeShooterByLimelightCommand() {
        return Shooter.getInstance().getPrimeShooterCommandWithDefault(
                () -> RobotContainer.hubLimelight.hasTarget() ?
                      ShootingCalculations.getShootingVelocityFromLimelight() :
                      0
        );
    }

    public static CommandBase getPitchByLimelightCommand() {
        return Pitcher.getInstance().getPitchingCommandWithDefault(
                () -> RobotContainer.hubLimelight.hasTarget() ?
                      ShootingCalculations.getShootingAngleFromLimelight() :
                      0
        );
    }

    public static TurnToTargetCommand getTurnToLimelight0Command() {
        PIDController pidController = new PIDController(0.2, 0.2, 0);
        return new TurnToTargetCommand(
                pidController,
                RobotContainer.hubLimelight::getTx,
                RobotContainer.hubLimelight::hasTarget,
                0
        );
    }
}
