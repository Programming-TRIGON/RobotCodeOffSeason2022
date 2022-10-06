// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.trigon.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.trigon.robot.components.TrigonController;
import frc.trigon.robot.utilities.JsonHandler;
import frc.trigon.robot.xboxSimulation.RecordControllerCommand;
import frc.trigon.robot.xboxSimulation.PlaybackControllerCommand;
import frc.trigon.robot.xboxSimulation.Log;

public class RobotContainer {
    public static final TrigonController driverController = new TrigonController(0);
    PlaybackControllerCommand playbackControllerCommand = new PlaybackControllerCommand(JsonHandler.parseJsonFileToObject("XboxLogs", Log[].class));
    RecordControllerCommand recordControllerCommand =new RecordControllerCommand();

    public RobotContainer() {
        SmartDashboard.putData(playbackControllerCommand);
        SmartDashboard.putData(recordControllerCommand);
    }
}
