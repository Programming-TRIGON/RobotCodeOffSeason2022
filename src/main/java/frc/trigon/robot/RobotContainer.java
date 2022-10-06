// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.trigon.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.trigon.robot.commands.CollectCommand;
import frc.trigon.robot.controllers.XboxController;
import frc.trigon.robot.subsystems.pitcher.Pitcher;
import frc.trigon.robot.subsystems.shooter.Shooter;

public class RobotContainer {

    XboxController controller = new XboxController(0);
    CollectCommand collectCommand;

    public RobotContainer() {
        collectCommand = new CollectCommand();

        bindCommands();
        putStuffOnSmartDashboard();
    }

    private void initCommands() {
    }

    private void bindCommands() {
        controller.getABtn().whileHeld(collectCommand);
    }

    private void putStuffOnSmartDashboard() {
        SmartDashboard.putData(Shooter.getInstance());
        SmartDashboard.putData(Pitcher.getInstance());
    }
}
