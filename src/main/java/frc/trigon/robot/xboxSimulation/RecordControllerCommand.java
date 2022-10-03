package frc.trigon.robot.xboxSimulation;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.trigon.robot.RobotContainer;

import java.io.IOException;
import java.util.ArrayList;

public class RecordControllerCommand extends CommandBase {
    private final XboxController controller = RobotContainer.driverController;
    private ArrayList<Log> logs;
    private double startTime;

    public RecordControllerCommand() {
    }

    @Override
    public void initialize() {
        logs = new ArrayList<Log>();
        startTime = Timer.getFPGATimestamp();
    }

    @Override
    public void execute() {
        logs.add(new Log(controller.getRightX(), controller.getRightY(), controller.getLeftX(), controller.getLeftY(),
                controller.getRightTriggerAxis(), controller.getLeftTriggerAxis(), controller.getAButton(),
                controller.getBButton(), controller.getXButton(), controller.getYButton(), controller.getRightBumper(),
                controller.getLeftBumper(), Timer.getFPGATimestamp() - startTime));
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        try {
            assert logs != null;
            Log[] logsArray = new Log[logs.size()];
            logs.toArray(logsArray);
            XboxLogsHandler.writeLogs(logsArray);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
