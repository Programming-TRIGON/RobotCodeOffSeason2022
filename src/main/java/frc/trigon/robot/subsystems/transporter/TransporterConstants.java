package frc.trigon.robot.subsystems.transporter;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class TransporterConstants {
    private static final int MOTOR_ID = 0;
    private static final double
            LOAD_VOLTAGE = 10,
            EJECT_VOLTAGE = -10;
    private static final boolean MOTOR_INVERTED = false;
    static final double VOLTAGE_COMPENSATION = 10.5;
    protected static final WPI_TalonSRX MOTOR = new WPI_TalonSRX(MOTOR_ID);


    static {
        MOTOR.setInverted(MOTOR_INVERTED);
        MOTOR.enableVoltageCompensation(true);
        MOTOR.configVoltageCompSaturation(VOLTAGE_COMPENSATION);
    }

    public enum TransporterState {
        LOAD(LOAD_VOLTAGE),
        EJECT(EJECT_VOLTAGE),
        OFF(0);
        final double voltage;

        TransporterState(double voltage) {
            this.voltage = voltage;
        }
    }
}
