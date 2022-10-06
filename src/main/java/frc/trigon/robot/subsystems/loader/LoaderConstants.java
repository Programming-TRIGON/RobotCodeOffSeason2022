package frc.trigon.robot.subsystems.loader;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class LoaderConstants {
    private static final int MOTOR_ID = 0;
    static final WPI_TalonSRX MOTOR = new WPI_TalonSRX(MOTOR_ID);
    static final double VOLTAGE_COMPENSATION_SATURATION = 10.5;
    private static final double
            LOAD_VOLTAGE = 6,
            EJECT_VOLTAGE = -6;
    private static final boolean MOTOR_INVERTED = false;

    static {
        MOTOR.setInverted(MOTOR_INVERTED);
        MOTOR.enableVoltageCompensation(true);
        MOTOR.configVoltageCompSaturation(VOLTAGE_COMPENSATION_SATURATION);
    }

    public enum LoaderState {
        LOAD(LOAD_VOLTAGE),
        EJECT(EJECT_VOLTAGE),
        OFF(0);

        final double voltage;

        LoaderState(double voltage) {
            this.voltage = voltage;
        }
    }
}