package frc.trigon.robot.subsystems.collector;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

public class CollectorConstants {
    private static final int
            MOTOR_OPENER_ID = 0,
            MOTOR_SPINNER_ID = 1;
    private static final boolean
            MOTOR_OPENER_INVERTED = false,
            MOTOR_SPINNER_INVERTED = false;
    protected static final WPI_TalonFX
            OPENER_MOTOR = new WPI_TalonFX(MOTOR_OPENER_ID),
            SPINNER_MOTOR = new WPI_TalonFX(MOTOR_SPINNER_ID);
    protected static final double MOTOR_SPINNER_VOLTAGE = 1;
    protected static final double
            OPEN_TICKS = 100,
            CLOSE_TICKS = -100;
    static {
        OPENER_MOTOR.setInverted(MOTOR_OPENER_INVERTED);
        SPINNER_MOTOR.setInverted(MOTOR_SPINNER_INVERTED);
    }
}
