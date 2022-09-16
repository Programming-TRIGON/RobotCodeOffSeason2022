package frc.trigon.robot.subsystems.transporter;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class TransporterConstants {
    static final double VOLTAGE_COMPENSATION_SATURATION = 10.5;
    private static final int MOTOR_ID = 0;
    static final WPI_TalonSRX MOTOR = new WPI_TalonSRX(MOTOR_ID);
    private static final double
            LOAD_POWER = 0.4,
            EJECT_POWER = -0.4;
    private static final boolean MOTOR_INVERTED = false;

    static {
        MOTOR.setInverted(MOTOR_INVERTED);
    }

    public enum TransporterState {
        LOAD(LOAD_POWER),
        EJECT(EJECT_POWER),
        OFF(0);
        final double power;

        TransporterState(double power) {
            this.power = power;
        }
    }
}
