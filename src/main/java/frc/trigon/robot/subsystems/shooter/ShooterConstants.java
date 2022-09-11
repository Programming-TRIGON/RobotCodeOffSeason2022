package frc.trigon.robot.subsystems.shooter;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class ShooterConstants {
    private static final int masterMotorID = 3;
    private static final int motor2ID = 4;
    protected static final WPI_TalonFX masterMotor = new WPI_TalonFX(masterMotorID);
    protected static final WPI_TalonFX motor2 = new WPI_TalonFX(motor2ID);

    static {
        motor2.follow(masterMotor);
    }
}
