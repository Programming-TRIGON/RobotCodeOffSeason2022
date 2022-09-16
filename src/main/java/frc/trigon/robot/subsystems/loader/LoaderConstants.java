package frc.trigon.robot.subsystems.loader;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class LoaderConstants {
    static final double VOLTAGE_COMPENSATION = 10.5;
    private static final double
            LOAD_POWER = 6,
            EJECT_POWER = -6;
    private static final int MOTOR_ID = 0;
    static final WPI_TalonSRX MOTOR = new WPI_TalonSRX(MOTOR_ID);

    static {
        MOTOR.enableVoltageCompensation(true);
        MOTOR.configVoltageCompSaturation(VOLTAGE_COMPENSATION);
    }

    public enum LoaderState {
        LOAD(LOAD_POWER),
        OFF(0),
        EJECT(EJECT_POWER);

        final double power;

        LoaderState(double voltage) {
            this.power = voltage;
        }
    }
}