package frc.trigon.robot.subsystems.loader;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.trigon.robot.utilities.Conversions;

public class LoaderConstants {
    private static final double
            LOAD_VOLTAGE = 6,
            EJECT_VOLTAGE = -6;
    public static final double VOLTAGE_COMPENSATION_SATURATION = 10.5;
    private static final boolean MOTOR_INVERTED = false;
    private static final int MOTOR_ID = 0;
    static final WPI_TalonSRX MOTOR = new WPI_TalonSRX(MOTOR_ID);

    // Setting the motor inverted, enabling the voltage compensation and configuring the voltage
    // compensation saturation.
    static {
        MOTOR.setInverted(MOTOR_INVERTED);
        MOTOR.enableVoltageCompensation(true);
        MOTOR.configVoltageCompSaturation(VOLTAGE_COMPENSATION_SATURATION);
    }

    //setting the voltage of each of the loader states.
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