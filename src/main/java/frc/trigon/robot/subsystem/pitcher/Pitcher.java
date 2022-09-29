package frc.trigon.robot.subsystem.pitcher;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.trigon.robot.utilities.Conversions;

public class Pitcher extends SubsystemBase {
    private final static Pitcher INSTANCE = new Pitcher();
    private final WPI_TalonSRX motor = PitcherConstants.MOTOR;

    public static Pitcher getInstance() {
        return INSTANCE;
    }

    public void setPosition(double degrees) {
        motor.set(ControlMode.Position, Conversions.degreesToMagTicks(degrees));
    }

    public double getDegrees() {
        double motorAngle = Conversions.magTicksToDegrees(motor.getSelectedSensorPosition());
        return Conversions.motorPositionToSystemPosition(motorAngle, PitcherConstants.GEAR_RATIO);
    }
}

