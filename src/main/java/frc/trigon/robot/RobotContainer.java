// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.trigon.robot;

import frc.trigon.robot.commands.PlaybackSimulatedControllerCommand;
import frc.trigon.robot.commands.RecordControllerCommand;
import frc.trigon.robot.controllers.simulation.SimulateableController;

public class RobotContainer {
    public static final SimulateableController driverController = new SimulateableController(0);
    PlaybackSimulatedControllerCommand playbackSimulatedControllerCommand;
    RecordControllerCommand recordControllerCommand;

    public RobotContainer() {
        //        playbackSimulatedControllerCommand = new PlaybackSimulatedControllerCommand(
        //                driverController, JsonHandler.parseJsonFileToObject("logs.json"));
        //
        //        SmartDashboard.putData(playbackSimulatedControllerCommand);
        //        SmartDashboard.putData(recordControllerCommand);
    }
}
