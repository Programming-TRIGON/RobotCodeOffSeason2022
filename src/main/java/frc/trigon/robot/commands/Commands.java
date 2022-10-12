package frc.trigon.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.Button;
import frc.trigon.robot.RobotContainer;
import frc.trigon.robot.subsystems.pitcher.Pitcher;
import frc.trigon.robot.subsystems.shooter.Shooter;
import frc.trigon.robot.subsystems.swerve.TurnToTargetCommand;
import frc.trigon.robot.utilities.ShootingCalculations;

import java.util.function.BooleanSupplier;

public class Commands {
    public static CommandBase getPrimeShooterByLimelightCommand() {
        return Shooter.getInstance().getPrimeShooterCommandWithIdleMode(
                () -> RobotContainer.hubLimelight.hasTarget() ?
                      ShootingCalculations.getShootingVelocityFromLimelight() :
                      0
        );
    }

    public static CommandBase getShooterEjectCommand() {
        return Shooter.getInstance().getEjectShooterCommand();
    }

    public static CommandBase getPitchByLimelightCommand() {
        return Pitcher.getInstance().getPitchingCommandWithIdleMode(
                () -> RobotContainer.hubLimelight.hasTarget() ?
                      ShootingCalculations.getShootingAngleFromLimelight() :
                      0
        );
    }

    public static TurnToTargetCommand getTurnToLimelight0Command() {
        final double P = 0.2;
        final double I = 0.2;
        final double D = 0.0;

        PIDController pidController = new PIDController(P, I, D);
        return new TurnToTargetCommand(
                pidController,
                RobotContainer.hubLimelight::getTx,
                RobotContainer.hubLimelight::hasTarget,
                0
        );
    }

    static void runCommandWhile(BooleanSupplier condition, Command command) {
        Button button = new Button(condition);
        button.whileHeld(command);
    }
}
