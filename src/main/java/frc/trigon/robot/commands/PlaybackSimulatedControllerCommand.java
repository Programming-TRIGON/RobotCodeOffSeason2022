package frc.trigon.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.trigon.robot.RobotContainer;
import frc.trigon.robot.controllers.simulation.Log;
import frc.trigon.robot.controllers.simulation.SimulateableController;

public class PlaybackSimulatedControllerCommand extends CommandBase {
    private final Log[] logs;
    private double startTime;
    private final SimulateableController controller;

    public PlaybackSimulatedControllerCommand(SimulateableController controller, Log[] logs) {
        this.controller = controller;
        this.logs = logs;
    }

    @Override
    public void initialize() {
        if(logs == null || logs.length == 0)
            this.cancel();
        startTime = Timer.getFPGATimestamp();
    }

    @Override
    public void execute() {
        for(int i = 0; i < logs.length && logs[i].getTime() <= startTime; i++) {
            if(logs[i].getTime() > Timer.getFPGATimestamp() - startTime)
                controller.setCurrentLog(logs[i]);
        }
    }

    @Override
    public boolean isFinished() {
        return logs[logs.length - 1].getTime() < Timer.getFPGATimestamp() - startTime;
    }

    @Override
    public void end(boolean interrupted) {
        RobotContainer.driverController.setCurrentLog(null);
    }
}
