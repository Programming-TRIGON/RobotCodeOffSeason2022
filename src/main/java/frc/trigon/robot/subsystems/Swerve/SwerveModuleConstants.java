package frc.trigon.robot.subsystems.Swerve;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class SwerveModuleConstants {
    public WPI_TalonSRX angleEncoder;
    public WPI_TalonFX angleMotor;
    public WPI_TalonFX driveMotor;

    public double encoderOffset;

    public static final double WHEEL_DIAMETER = 0.1;
    public static final double WHEEL_CIRCUMFERENCE = WHEEL_DIAMETER * Math.PI;
    public static final double DRIVE_GEAR_RATIO = (8.14 / 1.0);

    public SwerveModuleConstants(WPI_TalonSRX angleEncoder, WPI_TalonFX driveMotor, WPI_TalonFX angleMotor, double encoderOffset) {
        this.angleEncoder = angleEncoder;
        this.driveMotor = driveMotor;
        this.angleMotor = angleMotor;
        angleMotor.configSelectedFeedbackSensor(FeedbackDevice.RemoteSensor0);
        this.encoderOffset = encoderOffset;
    }
}

