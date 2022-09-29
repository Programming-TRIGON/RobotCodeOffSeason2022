package frc.trigon.robot.xboxSimulation;

import edu.wpi.first.wpilibj.XboxController;

public class AutonomousControl extends XboxController {
    private Log currentLog;

    public AutonomousControl(int id, Log[] logs, double time) {
        super(id);
        for(Log log : logs) {
            if(time > log.getTime())
                currentLog = log;
        }
        if(currentLog == null)
            currentLog = logs[logs.length - 1];
    }

    @Override
    public double getRightX() {
        return currentLog.getRightStickX();
    }

    @Override
    public double getRightY() {
        return currentLog.getRightStickY();
    }

    @Override
    public double getLeftX() {
        return currentLog.getLeftStickX();
    }

    @Override
    public double getLeftY() {
        return currentLog.getLeftStickY();
    }
}
