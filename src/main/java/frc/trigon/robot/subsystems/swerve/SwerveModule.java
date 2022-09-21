package frc.trigon.robot.subsystems.swerve;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import frc.trigon.robot.utilities.Conversions;

public class SwerveModule {
    private final WPI_TalonSRX angleEncoder;
    private final WPI_TalonFX angleMotor;
    private final WPI_TalonFX driveMotor;

    private final double encoderOffset;
    private SwerveModuleState targetState;

    public SwerveModule(SwerveModuleConstants moduleConstants) {
        this.angleEncoder = moduleConstants.angleEncoder;
        this.angleMotor = moduleConstants.angleMotor;
        this.driveMotor = moduleConstants.driveMotor;

        this.encoderOffset = moduleConstants.encoderOffset;

        ChangeToOuterEncoder();

        setDriveMotorSensorZero();
    }

    public void setTargetState(SwerveModuleState targetState) {
        this.targetState = targetState;
        optimizeTargetState();
        setAngleMotor();
        setDriveMotor();
    }

    public SwerveModuleState getCurrentState() {
        return new SwerveModuleState(getDriveMotorState(), getAngleMotorState());
    }

    private void optimizeTargetState() {
        double scoped = scope(targetState.angle.getDegrees());
        double flipped = scope(180 + targetState.angle.getDegrees());
        double scopeDiff = Math.abs(scoped - getDegrees());
        double flippedDiff = Math.abs(flipped - getDegrees());
        if(scopeDiff < flippedDiff)
            targetState.angle = Rotation2d.fromDegrees(scoped);
        else
            targetState.angle = Rotation2d.fromDegrees(flipped);
        targetState.speedMetersPerSecond *= -1;
    }

    public double scope(double targetAngle) {
        double rawCurrentAngle = getDegrees() % 360;
        double rawTargetAngle = targetAngle % 360;
        double difference = rawTargetAngle - rawCurrentAngle;
        if(difference < -180)
            difference += 360;

        else if(difference > 180)
            difference -= 360;

        return difference + getDegrees();
    }

    public void setAngleMotor() {
        angleMotor.set(ControlMode.Position, Conversions.degreesToMag(targetState.angle.getDegrees()));
    }

    public void setDriveMotor() {
        driveMotor.set(
                ControlMode.Velocity,
                Conversions.mpsToFalcon(
                        targetState.speedMetersPerSecond,
                        SwerveModuleConstants.WHEEL_CIRCUMFERENCE_METER,
                        SwerveModuleConstants.DRIVE_GEAR_RATIO
                )
        );
    }

    private double getDriveMotorState() {
        return Conversions.falconToMps(
                Conversions.falconToRotations(driveMotor.getSelectedSensorVelocity()),
                SwerveModuleConstants.WHEEL_CIRCUMFERENCE_METER, SwerveModuleConstants.DRIVE_GEAR_RATIO);
    }

    private Rotation2d getAngleMotorState() {
        return Rotation2d.fromDegrees(Conversions.magToDegrees(angleMotor.getSelectedSensorPosition()));
    }

    private double getDegrees() {
        return Conversions.magToDegrees(angleMotor.getSelectedSensorPosition());
    }

    public void ChangeToOuterEncoder() {
        angleMotor.configRemoteFeedbackFilter(angleEncoder, 0);
        angleMotor.configSelectedFeedbackSensor(FeedbackDevice.RemoteSensor0);
    }

    public void setDriveMotorSensorZero() {
        driveMotor.setSelectedSensorPosition(0);
    }
}

