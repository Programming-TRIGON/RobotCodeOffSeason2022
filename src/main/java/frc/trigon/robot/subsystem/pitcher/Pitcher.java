package frc.trigon.robot.subsystem.pitcher;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Pitcher extends SubsystemBase {
    private final static Pitcher INSTANCE = new Pitcher();
    private final WPI_TalonSRX motor = PitcherConstants.MOTOR;
    
    public static Pitcher getInstance() {
        return INSTANCE;
    }

    public void setPosition() {

    }

    public double getDegrees() {
        return 0;
    }

    public double ticksToDegrees(double ticks) {
        double rotations = ticks / 4096;
        double degrees = rotations * 360;
        return degrees / PitcherConstants.GEAR_RATIO;
    }

    public double degreesToTicks(double degrees) {
        double rotations = degrees / 360;
        double ticks = rotations * 4096;
        return ticks / PitcherConstants.GEAR_RATIO;
    }
}

