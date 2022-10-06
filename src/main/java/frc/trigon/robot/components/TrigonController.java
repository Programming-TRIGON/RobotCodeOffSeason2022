package frc.trigon.robot.components;

import edu.wpi.first.wpilibj.XboxController;

import static frc.trigon.robot.xboxSimulation.PlaybackControllerCommand.currentLog;

public class TrigonController extends XboxController {
    private boolean inPlayback = false;

    public TrigonController(int id) {
        super(id);
    }

    public void setInPlayback(boolean inPlayback) {
        this.inPlayback = inPlayback;
    }

    @Override
    public double getRightX() {
        return inPlayback ? currentLog.getRightX() : super.getRightX();
    }

    @Override
    public double getRightY() {
        return inPlayback ? currentLog.getRightY() : super.getRightY();
    }

    @Override
    public double getLeftX() {
        return inPlayback ? currentLog.getLeftX() : super.getLeftX();
    }

    @Override
    public double getLeftY() {
        return inPlayback ? currentLog.getLeftY() : super.getLeftY();
    }

    @Override
    public double getRightTriggerAxis() {
        return inPlayback ? currentLog.getRightTrigger() : super.getRightTriggerAxis();
    }

    @Override
    public double getLeftTriggerAxis() {
        return inPlayback ? currentLog.getLeftTrigger() : super.getLeftTriggerAxis();
    }

    @Override
    public boolean getAButton() {
        return inPlayback ? currentLog.getA() : super.getAButton();
    }

    @Override
    public boolean getBButton() {
        return inPlayback ? currentLog.getB() : super.getBButton();
    }

    @Override
    public boolean getXButton() {
        return inPlayback ? currentLog.getX() : super.getXButton();
    }

    @Override
    public boolean getYButton() {
        return inPlayback ? currentLog.getY() : super.getYButton();
    }

    @Override
    public boolean getRightBumper() {
        return inPlayback ? currentLog.getRightBumper() : super.getRightBumper();
    }

    @Override
    public boolean getLeftBumper() {
        return inPlayback ? currentLog.getLeftBumper() : super.getLeftBumper();
    }
}
