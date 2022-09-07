package frc.trigon.robot.subsystems.collector;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class CollectorConstants {
    private static final int
            MOTOR_OPENER_ID = 0,
            MOTOR_COLLECTOR_ID = 1;
    private static final boolean
            MOTOR_OPENER_INVERTED = false,
            MOTOR_COLLECTOR_INVERTED = false;
    protected static final WPI_TalonSRX
            OPENER_MOTOR = new WPI_TalonSRX(MOTOR_OPENER_ID),
            COLLECTOR_MOTOR = new WPI_TalonSRX(MOTOR_COLLECTOR_ID);
    protected static final double
            MOTOR_COLLECTOR_VOLTAGE = 1,
            MOTOR_OPENER_VOLTAGE = 1;
    static final double
            CURRENT_LIMIT_PEAK_CURRENT = 10,
            CURRENT_LIMIT_PEAK_DURATION = 0.2,
            CURRENT_LIMIT = 0.5;
    static {
        OPENER_MOTOR.setInverted(MOTOR_OPENER_INVERTED);
        COLLECTOR_MOTOR.setInverted(MOTOR_COLLECTOR_INVERTED);
    }
}
