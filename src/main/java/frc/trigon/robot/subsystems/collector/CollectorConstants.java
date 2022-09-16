package frc.trigon.robot.subsystems.collector;

import com.ctre.phoenix.motorcontrol.StatorCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

public class CollectorConstants {
    static final double
            COLLECTING_POWER = 0.5,
            OPENING_POWER = 0.5,
            EJECTING_POWER = -0.5,
            CLOSING_POWER = -0.5;
    private static final double
            OPENER_CURRENT_LIMIT_PEAK_CURRENT = 10,
            OPENER_CURRENT_LIMIT_PEAK_DURATION = 0.2,
            OPENER_CURRENT_LIMIT = 0.5;
    private static final double
            COLLECTOR_CURRENT_LIMIT_PEAK_CURRENT = 20,
            COLLECTOR_CURRENT_LIMIT_PEAK_DURATION = 0.8,
            COLLECTOR_CURRENT_LIMIT = 8;
    private static final int
            OPENER_MOTOR_ID = 0,
            COLLECTOR_MOTOR_ID = 1;
    static final WPI_TalonFX
            OPENER_MOTOR = new WPI_TalonFX(OPENER_MOTOR_ID),
            COLLECTOR_MOTOR = new WPI_TalonFX(COLLECTOR_MOTOR_ID);
    private static final boolean
            OPENER_MOTOR_INVERTED = false,
            COLLECTOR_MOTOR_INVERTED = false;

    static {
        OPENER_MOTOR.setInverted(OPENER_MOTOR_INVERTED);
        COLLECTOR_MOTOR.setInverted(COLLECTOR_MOTOR_INVERTED);

        OPENER_MOTOR.configStatorCurrentLimit(
                new StatorCurrentLimitConfiguration(
                        true,
                        OPENER_CURRENT_LIMIT,
                        OPENER_CURRENT_LIMIT_PEAK_CURRENT,
                        OPENER_CURRENT_LIMIT_PEAK_DURATION
                )
        );
        COLLECTOR_MOTOR.configStatorCurrentLimit(
                new StatorCurrentLimitConfiguration(
                        true,
                        COLLECTOR_CURRENT_LIMIT,
                        COLLECTOR_CURRENT_LIMIT_PEAK_CURRENT,
                        COLLECTOR_CURRENT_LIMIT_PEAK_DURATION
                )
        );
    }
}
