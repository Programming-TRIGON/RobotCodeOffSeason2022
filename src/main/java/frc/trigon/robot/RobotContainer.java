// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.trigon.robot;

import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.trigon.robot.commands.PlaybackSimulatedControllerCommand;
import frc.trigon.robot.commands.RecordControllerCommand;
import frc.trigon.robot.controllers.simulation.Log;
import frc.trigon.robot.controllers.simulation.SimulateableController;
import frc.trigon.robot.utilities.JsonHandler;

public class RobotContainer {
    public static final SimulateableController driverController = new SimulateableController(0);
    public PlaybackSimulatedControllerCommand playbackSimulatedControllerCommand;
    public RecordControllerCommand recordControllerCommand;

    public RobotContainer() {
        playbackSimulatedControllerCommand = new PlaybackSimulatedControllerCommand(
                driverController, JsonHandler.parseJsonFileToObject("XboxLogs", Log[].class)).raceWith(new WaitCommand(5));
        recordControllerCommand = new RecordControllerCommand(driverController);
    }
}
