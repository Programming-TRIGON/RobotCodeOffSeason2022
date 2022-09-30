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

    public void setTargetAngle(double degrees) {
        double ticks = Conversions.degreesToMagTicks(degrees);
        motor.set(ControlMode.Position, ticks + PitcherConstants.MIN_TICKS);
    }

    public double getError() {
        double error = motor.getClosedLoopError();
        return Conversions.magTicksToDegrees(error);
    }

    public double getAngle() {
        double ticks = motor.getSelectedSensorPosition() - PitcherConstants.MIN_TICKS;
        double motorAngle = Conversions.magTicksToDegrees(ticks);
        return Conversions.motorPositionToSystemPosition(motorAngle, PitcherConstants.GEAR_RATIO);
    }
}

