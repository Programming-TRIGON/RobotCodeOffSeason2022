package frc.trigon.robot.subsystems.loader;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class LoaderConstants {
    private static final int MOTOR_ID = 0;
    protected static final WPI_TalonSRX MOTOR = new WPI_TalonSRX(MOTOR_ID);
    protected static final double
            LOAD_VOLTAGE = 6,
            EJECT_VOLTAGE = -6;
    protected static final double VOLTAGE_COMPENSATION = 10.5;

    public static enum LoaderState {
        LOAD(LOAD_VOLTAGE),
        OFF(0),
        EJECT(EJECT_VOLTAGE);

        final double voltage;

        LoaderState(double voltage) {
            this.voltage = voltage;
        }
    }
}