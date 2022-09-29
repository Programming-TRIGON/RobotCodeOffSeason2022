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

        angleMotor.configSelectedFeedbackSensor(FeedbackDevice.RemoteSensor0);
        configRemoteSensor();
    }

    public void setTargetState(SwerveModuleState targetState,boolean isOpenLoop) {
        this.targetState = targetState;
        optimizeTargetState();
        setMotorsToStates(
                isOpenLoop,
                targetState.angle.getDegrees(),
                targetState.speedMetersPerSecond
        );
    }

    public SwerveModuleState getCurrentState() {
        return new SwerveModuleState(getVelocity(), getModuleAngle());
    }

    private void optimizeTargetState() {
        double scoped = scope(targetState.angle.getDegrees());
        double flipped = scope(180 + targetState.angle.getDegrees());
        double scopeDiff = Math.abs(scoped - getDegrees());
        double flippedDiff = Math.abs(flipped - getDegrees());
        if(scopeDiff < flippedDiff)
            targetState.angle = Rotation2d.fromDegrees(scoped);
        else {
            targetState.angle = Rotation2d.fromDegrees(flipped);
            targetState.speedMetersPerSecond *= -1;
        }
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

    public void setMotorsToStates(boolean isOpenLoop ,double targetAngle ,double targetVelocity) {
        setTargetAngle(targetAngle);
        setTargetDriveVelocity(isOpenLoop ,targetVelocity);
    }

    public void setTargetAngle(double targetAngle) {
        double targetAnglePosition = Conversions.degreesToMagTicks(targetAngle);
        angleMotor.set(ControlMode.Position, targetAnglePosition);
    }

    public void setTargetDriveVelocity(boolean isOpenLoop , double targetVelocity){
        if(isOpenLoop)
            setTargetDriveMotorPower(targetVelocity);
        else setTargetDriveMotorVelocity(targetVelocity);

    }
    public void setTargetDriveMotorPower(double targetPower){
        double Power = targetPower / SwerveConstants.MAX_SPEED_METERS_PER_SECOND;
        driveMotor.set(ControlMode.PercentOutput , Power);
    }
    public void setTargetDriveMotorVelocity(double targetVelocity) {
        double driveMotorVelocity = Conversions.systemRevolutionsToFalconTicks(
                targetVelocity,
                SwerveModuleConstants.WHEEL_CIRCUMFERENCE_METER,
                SwerveModuleConstants.DRIVE_GEAR_RATIO
        );
        driveMotor.set(ControlMode.Velocity, driveMotorVelocity);
    }

    private double getVelocity() {
        double driveMotorVelocity = driveMotor.getSelectedSensorVelocity();
        return Conversions.revolutionsToMeters(
                Conversions.falconTicksToRevolutions(driveMotorVelocity),
                SwerveModuleConstants.WHEEL_CIRCUMFERENCE_METER,
                SwerveModuleConstants.DRIVE_GEAR_RATIO
        );
    }

    private Rotation2d getModuleAngle() {
        double getAngleDegrees = Conversions.magTicksToDegrees(angleMotor.getSelectedSensorPosition());
        return Rotation2d.fromDegrees(getAngleDegrees);
    }

    private double getDegrees() {
        double getDegreesTicks = angleMotor.getSelectedSensorPosition();
        return Conversions.magTicksToDegrees(getDegreesTicks);
    }

    private void configRemoteSensor() {
        angleMotor.configRemoteFeedbackFilter(angleEncoder, 0);
        angleMotor.configSelectedFeedbackSensor(FeedbackDevice.RemoteSensor0);
    }

    public void stopModule() {
        driveMotor.disable();
        angleMotor.disable();
    }
}

