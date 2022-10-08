// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.trigon.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.trigon.robot.commands.PlaybackSimulatedControllerCommand;
import frc.trigon.robot.commands.RecordControllerCommand;
import frc.trigon.robot.controllers.simulation.SimulateableController;
import frc.trigon.robot.subsystems.swerve.FieldRelativeSupplierDrive;
import frc.trigon.robot.subsystems.swerve.Swerve;

public class RobotContainer {
    public static final SimulateableController driverController = new SimulateableController(0);
    private PlaybackSimulatedControllerCommand playbackSimulatedControllerCommand;
    private RecordControllerCommand recordControllerCommand;
    private FieldRelativeSupplierDrive swerveCommand;
    private Swerve swerve;

    public RobotContainer() {
        playbackSimulatedControllerCommand = new PlaybackSimulatedControllerCommand(driverController);
        recordControllerCommand = new RecordControllerCommand(driverController);
        swerveCommand = new FieldRelativeSupplierDrive(driverController::getLeftX,
               driverController::getLeftY, driverController::getRightX);
        swerve = Swerve.getInstance();
        swerve.setDefaultCommand(swerveCommand);
        SmartDashboard.putData(recordControllerCommand);
        SmartDashboard.putData(playbackSimulatedControllerCommand);
    }
}
