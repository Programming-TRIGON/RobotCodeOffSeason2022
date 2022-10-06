// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.trigon.robot;

import edu.wpi.first.wpilibj.XboxController;
import frc.trigon.robot.subsystems.swerve.FieldRelativeSupplierDrive;
import frc.trigon.robot.subsystems.swerve.Swerve;

public class RobotContainer {

    XboxController controller;

    //commands
    FieldRelativeSupplierDrive fieldRelativeSupplierDrive;

    public RobotContainer() {
        controller = new XboxController(0);

        initCommand();
        bindCommands();
    }

    private void initCommand() {
        fieldRelativeSupplierDrive = new FieldRelativeSupplierDrive(
                () -> -controller.getLeftY(),
                () -> -controller.getLeftX(),
                () -> -controller.getRightX()
        );
    }

    private void bindCommands() {
        Swerve.getInstance().setDefaultCommand(fieldRelativeSupplierDrive);
    }
}
