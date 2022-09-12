package frc.trigon.robot.subsystems.shooter;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class ShooterConstants {
    private static final int masterMotorId = 3;
    private static final int motor2Id = 4;
    static final WPI_TalonFX masterMotor = new WPI_TalonFX(masterMotorId);
    static final WPI_TalonFX motor2 = new WPI_TalonFX(motor2Id);

    static {
        motor2.follow(masterMotor);
    }
}
