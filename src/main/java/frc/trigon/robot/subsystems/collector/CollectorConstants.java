package frc.trigon.robot.subsystems.collector;

import com.ctre.phoenix.motorcontrol.StatorCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

public class CollectorConstants {
    protected static final double
            COLLECTING_VOLTAGE = 1,
            OPENING_VOLTAGE = 1,
            EJECTING_VOLTAGE = -6,
            CLOSING_VOLTAGE = -6;
    static final double
            OPENER_CURRENT_LIMIT_PEAK_CURRENT = 10,
            OPENER_CURRENT_LIMIT_PEAK_DURATION = 0.2,
            OPENER_CURRENT_LIMIT = 0.5;
    static final double
            COLLECTOR_CURRENT_LIMIT_PEAK_CURRENT = 20,
            COLLECTOR_CURRENT_LIMIT_PEAK_DURATION = 0.8,
            COLLECTOR_CURRENT_LIMIT = 8;
    private static final int

            MOTOR_OPENER_ID = 0,
            MOTOR_COLLECTOR_ID = 1;
    protected static final WPI_TalonFX
            OPENER_MOTOR = new WPI_TalonFX(MOTOR_OPENER_ID),
            COLLECTOR_MOTOR = new WPI_TalonFX(MOTOR_COLLECTOR_ID);
    private static final boolean
            MOTOR_OPENER_INVERTED = false,
            MOTOR_COLLECTOR_INVERTED = false;

    static {
        OPENER_MOTOR.setInverted(MOTOR_OPENER_INVERTED);
        COLLECTOR_MOTOR.setInverted(MOTOR_COLLECTOR_INVERTED);
        OPENER_MOTOR.configStatorCurrentLimit(
                new StatorCurrentLimitConfiguration(
                        true,
                        OPENER_CURRENT_LIMIT,
                        OPENER_CURRENT_LIMIT_PEAK_CURRENT,
                        OPENER_CURRENT_LIMIT_PEAK_DURATION
                )
        );
        
        COLLECTOR_MOTOR.configSupplyCurrentLimit(
                new SupplyCurrentLimitConfiguration(
                        true,
                        COLLECTOR_CURRENT_LIMIT,
                        COLLECTOR_CURRENT_LIMIT_PEAK_CURRENT,
                        COLLECTOR_CURRENT_LIMIT_PEAK_DURATION
                )
        );
    }
}
