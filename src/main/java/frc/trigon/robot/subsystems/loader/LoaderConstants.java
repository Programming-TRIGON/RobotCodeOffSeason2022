package frc.trigon.robot.subsystems.loader;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.trigon.robot.utilities.Conversions;

public class LoaderConstants {
    private static final double
            LOAD_VOLTAGE = 6,
            EJECT_VOLTAGE = -6;
    private static final boolean IS_INVERTED = false;
    private static final int MOTOR_ID = 0;
    static final WPI_TalonSRX MOTOR = new WPI_TalonSRX(MOTOR_ID);

    static {
        MOTOR.setInverted(IS_INVERTED);
        MOTOR.enableVoltageCompensation(true);
        MOTOR.configVoltageCompSaturation(Conversions.VOLTAGE_SATURATION);
    }

    public enum LoaderState {
        LOAD(LOAD_VOLTAGE),
        OFF(0),
        EJECT(EJECT_VOLTAGE);

        final double voltage;

        LoaderState(double voltage) {
            this.voltage = voltage;
        }
    }
}