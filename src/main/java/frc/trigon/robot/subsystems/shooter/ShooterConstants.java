package frc.trigon.robot.subsystems.shooter;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

public class ShooterConstants {

    private static final int
            LEFT_MOTOR_ID = 3,
            RIGHT_MOTOR_ID = 4;

    private static final double VOLTAGE_COMPENSATION = 0;

    private static final double
            P = 0,
            I = 0,
            D = 0,
            F = 0;

    static final double S = 0;

    static final WPI_TalonFX
            LEFT_MOTOR = new WPI_TalonFX(LEFT_MOTOR_ID),
            RIGHT_MOTOR = new WPI_TalonFX(RIGHT_MOTOR_ID),
            MASTER_MOTOR = RIGHT_MOTOR,
            FOLLOWER_MOTOR = LEFT_MOTOR == MASTER_MOTOR ? RIGHT_MOTOR : LEFT_MOTOR;

    static {
        FOLLOWER_MOTOR.follow(MASTER_MOTOR);

        MASTER_MOTOR.enableVoltageCompensation(true);

        MASTER_MOTOR.configVoltageCompSaturation(VOLTAGE_COMPENSATION);

        MASTER_MOTOR.config_kP(0, P);

        MASTER_MOTOR.config_kI(0, I);

        MASTER_MOTOR.config_kD(0, D);

        MASTER_MOTOR.config_kD(0, F);
    }
}
