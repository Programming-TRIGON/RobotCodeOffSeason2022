package frc.trigon.robot.subsystems.shooter;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class ShooterConstants {

    private static final int LEFT_MOTOR_ID = 3;
    private static final int RIGHT_MOTOR_ID = 4;

    static final WPI_TalonFX
            LEFT_MOTOR = new WPI_TalonFX(LEFT_MOTOR_ID),
            RIGHT_MOTOR = new WPI_TalonFX(RIGHT_MOTOR_ID),
            MASTER_MOTOR = RIGHT_MOTOR,
            FOLLOWER_MOTOR = LEFT_MOTOR == MASTER_MOTOR ? RIGHT_MOTOR : LEFT_MOTOR;

    static {
        FOLLOWER_MOTOR.follow(MASTER_MOTOR);
    }
}
