package frc.trigon.robot.subsystems.shooter;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ShotsDetectorCommand extends CommandBase {
    private double lastErrorTimer = 0;
    private boolean isStable = false;

    public ShotsDetectorCommand() {
    }

    @Override
    public void execute() {
        if(Shooter.getInstance().atTargetVelocity()) {
            if(Timer.getFPGATimestamp() >= lastErrorTimer + ShooterConstants.TIME_TOLERANCE) {
                isStable = true;
            }
        } else {
            isStable = false;
            lastErrorTimer = Timer.getFPGATimestamp();
        }
    }

    public boolean getIsStable() {
        return isStable;
    }
}
