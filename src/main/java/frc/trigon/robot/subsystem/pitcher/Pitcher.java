package frc.trigon.robot.subsystem.pitcher;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.trigon.robot.utilities.Conversions;

public class Pitcher extends SubsystemBase {
    private final WPI_TalonSRX motor;

    private final static Pitcher INSTANCE = new Pitcher();

    public static Pitcher getInstance() {
        return INSTANCE;
    }

    private Pitcher() {
        motor = PitcherConstants.MOTOR;
    }

    public void setPosition() {

    }
        private double getCorrectedTicks() {
            if(motor.getSelectedSensorPosition() < PitcherConstants.MIN_TICKS) {
                return motor.getSelectedSensorPosition() + Conversions.MAG_TICKS - PitcherConstants.MIN_TICKS;
            }
            return motor.getSelectedSensorPosition() - PitcherConstants.MIN_TICKS;
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

