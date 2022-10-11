package frc.trigon.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.trigon.robot.controllers.simulation.SimulateableController;
import frc.trigon.robot.subsystems.swerve.FieldRelativeSupplierDrive;
import frc.trigon.robot.subsystems.swerve.Swerve;

public class autoAimCommand extends ParallelCommandGroup {
    Swerve swerve = Swerve.getInstance();

    public autoAimCommand(SimulateableController controller) {
        super(new FieldRelativeSupplierDrive(
                controller::getLeftY,
                () -> -controller.getLeftX(),
                () -> 0
        ), Commands.getTurnToLimelight0Command());
        addRequirements(swerve);
    }
}