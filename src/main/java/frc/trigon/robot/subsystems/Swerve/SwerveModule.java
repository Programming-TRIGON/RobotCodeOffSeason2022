package frc.trigon.robot.subsystems.Swerve;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import frc.trigon.robot.utilities.Conversions;

public class SwerveModule {

    private WPI_TalonSRX angleEncoder;
    private WPI_TalonFX angleMotor;
    private WPI_TalonFX driveMotor;

    private double encoderOffset;
    private SwerveModuleState targetState;

    public SwerveModule(SwerveModuleConstants ModuleConstants) {
        this.angleEncoder = ModuleConstants.angleEncoder;
        this.angleMotor = ModuleConstants.angleMotor;
        this.driveMotor = ModuleConstants.driveMotor;

        this.encoderOffset = ModuleConstants.encoderOffset;

        angleMotor.configRemoteFeedbackFilter(angleEncoder, 0);
        angleMotor.configSelectedFeedbackSensor(FeedbackDevice.RemoteSensor0);

        driveMotor.setSelectedSensorPosition(0);
    }

    public void setTargetState(SwerveModuleState targetState) {
        this.targetState = targetState;
        optimizeTargetState();
        angleMotor.set(ControlMode.Position, Conversions.degreesToMag(targetState.angle.getDegrees()));
        driveMotor.set(
                ControlMode.Velocity,
                Conversions.mpsToFalcon(
                        targetState.speedMetersPerSecond,
                        SwerveModuleConstants.WHEEL_CIRCUMFERENCE,
                        SwerveModuleConstants.DRIVE_GEAR_RATIO
                )
        );

        double velocity = Conversions.mpsToFalcon(
                targetState.speedMetersPerSecond,
                SwerveModuleConstants.WHEEL_CIRCUMFERENCE,
                SwerveModuleConstants.DRIVE_GEAR_RATIO);
        // Sets the drive motor velocity with the driveFeedforward.
        double angle = Conversions.degreesToMag(targetState.angle.getDegrees());
        driveMotor.set(ControlMode.Velocity, velocity);
        angleMotor.set(ControlMode.Position, angle);
    }

    public SwerveModuleState getCurrentState() {

        return new SwerveModuleState(
                Conversions.falconToMps(
                        Conversions.falconToRotations(driveMotor.getSelectedSensorVelocity()),
                        SwerveModuleConstants.WHEEL_CIRCUMFERENCE, SwerveModuleConstants.DRIVE_GEAR_RATIO),
                Rotation2d.fromDegrees(Conversions.magToDegrees(angleMotor.getSelectedSensorPosition())));
    }

    private void optimizeTargetState() {
        double scoped = scope(targetState.angle.getDegrees());// -179
        double flipped = scope(180 + targetState.angle.getDegrees()); //1
        double scopeDiff = Math.abs(scoped - getDegrees());
        double flippedDiff = Math.abs(flipped - getDegrees());
        if(scopeDiff < flippedDiff) {
            targetState.angle = Rotation2d.fromDegrees(scoped);
        } else {
            targetState.angle = Rotation2d.fromDegrees(flipped);
            targetState.speedMetersPerSecond *= -1;
        }
    }

    public double scope(double targetAngle) {// 360
        double rawCurrentAngle = getDegrees() % 360;// 0
        double rawTargetAngle = targetAngle % 360;// 0
        double difference = rawTargetAngle - rawCurrentAngle;// -0
        if(difference < -180) {
            difference += 360;
        }//
        else if(difference > 180) {
            difference -= 360;
        }//
        return difference + getDegrees();//0+0
    }

    public double getRawAngleVelocity() {
        return angleMotor.getSelectedSensorVelocity();
    }

    public double getRawDriverVelocity() {
        return driveMotor.getSelectedSensorVelocity();
    }

    public double getRawAngleEncoderVelocity(){
        return angleEncoder.getSelectedSensorVelocity();
    }

    public double getDegrees() {
        return Conversions.magToDegrees(angleMotor.getSelectedSensorPosition());
    }
}

