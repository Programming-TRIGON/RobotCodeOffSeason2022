package frc.trigon.robot.subsystems.shooter;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

public class ShooterConstants {
    private static final int
            LEFT_MOTOR_ID = 3,
            RIGHT_MOTOR_ID = 4;

    private static final boolean INVERTED = false;

    private static final double VOLTAGE_SATURATION = 10;

    private static final double
            P = 0.12,
            I = 0.00002,
            D = 0,
            V = 0.0522,
            MAX_I = 1300000;
    static final double S = 0.03;

    private static final WPI_TalonFX
            LEFT_MOTOR = new WPI_TalonFX(LEFT_MOTOR_ID),
            RIGHT_MOTOR = new WPI_TalonFX(RIGHT_MOTOR_ID);

    static final WPI_TalonFX
            MASTER_MOTOR = RIGHT_MOTOR;

    private static final WPI_TalonFX FOLLOWER_MOTOR =
            LEFT_MOTOR == MASTER_MOTOR ? RIGHT_MOTOR : LEFT_MOTOR;

    static {
        MASTER_MOTOR.setInverted(INVERTED);
        FOLLOWER_MOTOR.setInverted(!INVERTED);

        FOLLOWER_MOTOR.follow(MASTER_MOTOR);

        MASTER_MOTOR.enableVoltageCompensation(true);
        MASTER_MOTOR.configVoltageCompSaturation(VOLTAGE_SATURATION);

        MASTER_MOTOR.config_kP(0, P);
        MASTER_MOTOR.config_kI(0, I);
        MASTER_MOTOR.config_kD(0, D);
        MASTER_MOTOR.config_kF(0, V);

        MASTER_MOTOR.configMaxIntegralAccumulator(0,MAX_I);
    }
}