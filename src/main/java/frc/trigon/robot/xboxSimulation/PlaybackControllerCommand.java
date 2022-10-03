package frc.trigon.robot.xboxSimulation;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.trigon.robot.RobotContainer;

public class PlaybackControllerCommand extends CommandBase {
    public static Log currentLog = null;
    private final Log[] logs;
    private double startTime;

    public PlaybackControllerCommand(Log[] logs) {
        this.logs = logs;
    }

    @Override
    public void initialize() {
        RobotContainer.driverController.setInPlayback(true);
        startTime = Timer.getFPGATimestamp();
    }

    @Override
    public void execute() {
        for(Log log : logs) {
            if(log.getTime() > Timer.getFPGATimestamp() - startTime) {
                currentLog = log;
                break;
            }
        }
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        RobotContainer.driverController.setInPlayback(false);
    }
}
