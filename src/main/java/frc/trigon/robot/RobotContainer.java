// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.trigon.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.Button;
import frc.trigon.robot.commands.CollectCommand;
import frc.trigon.robot.commands.ShootCommand;
import frc.trigon.robot.subsystems.collector.Collector;
import frc.trigon.robot.subsystems.swerve.FieldRelativeSupplierDrive;
import frc.trigon.robot.subsystems.swerve.Swerve;

public class RobotContainer {

    //non
    XboxController controller;

    //commands
    FieldRelativeSupplierDrive fieldRelativeSupplierDrive;
    CollectCommand collectCommand;
    ShootCommand shootCommand;

    public RobotContainer() {
        controller = new XboxController(0);

        initCommands();
        bindCommands();
    }

    private void initCommands() {
        fieldRelativeSupplierDrive = new FieldRelativeSupplierDrive(
                () -> -controller.getLeftY(),
                () -> -controller.getLeftX(),
                () -> -controller.getRightX()
        );
        collectCommand = new CollectCommand();
        shootCommand = new ShootCommand();
    }

    private void bindCommands() {
        Swerve.getInstance().setDefaultCommand(fieldRelativeSupplierDrive);
        new Button(controller::getAButton).whenPressed(collectCommand).whenReleased(Collector.getInstance()::close);
        new Button(controller::getXButton).whenPressed(shootCommand);
    }
}
