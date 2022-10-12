package frc.trigon.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.trigon.robot.controllers.simulation.SimulateableController;
import frc.trigon.robot.subsystems.swerve.FieldRelativeSupplierDrive;
import frc.trigon.robot.subsystems.swerve.Swerve;

public class AutoAimCommand extends ParallelCommandGroup {
    Swerve swerve = Swerve.getInstance();

    public AutoAimCommand(SimulateableController controller) {
        super(new FieldRelativeSupplierDrive(
                controller::getLeftY,
                () -> -controller.getLeftX(),
                () -> 0
        ), Commands.getTurnToLimelight0Command());
        addRequirements(swerve);
    }
}