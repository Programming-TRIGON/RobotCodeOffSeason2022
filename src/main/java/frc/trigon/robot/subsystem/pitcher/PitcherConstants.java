package frc.trigon.robot.subsystem.pitcher;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class PitcherConstants {
    static final int MIN_TICKS = 894, MAX_TICKS = 2650;
    static final double GEAR_RATIO = 10;
    private static final int MOTOR_ID = 8;
    final static WPI_TalonSRX MOTOR = new WPI_TalonSRX(MOTOR_ID);
    private static final double
            P = 0.01,
            I = 0,
            D = 0;
    private static final boolean
            INVERTED = false,
            SENSOR_PHASE = true;

    static {
        MOTOR.config_kP(0, P);
        MOTOR.config_kI(0, I);
        MOTOR.config_kD(0, D);

        MOTOR.setInverted(INVERTED);
        MOTOR.setSensorPhase(SENSOR_PHASE);
    }
}
