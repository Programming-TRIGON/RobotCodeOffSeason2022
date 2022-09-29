package frc.trigon.robot.subsystems.swerve;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Swerve extends SubsystemBase {
    private final static Swerve INSTANCE = new Swerve();

    public Swerve() {
        zeroHeading();
    }

    public static Swerve getInstance() {
        return INSTANCE;
    }

    /**
     * Drives the swerve with the given velocities, relative to the robot.
     *
     *  @param translation the target x and y velocity.
     *  @param rotation desired angle of the robot in radioants.
     * @param isOpenLoop determine if it uses velocities or percent output for the drive motor.
     **/
    void selfRelativeDrive(Translation2d translation, Rotation2d rotation, boolean isOpenLoop) {
        ChassisSpeeds chassisSpeeds = new ChassisSpeeds(
                translation.getX(),
                translation.getY(),
                rotation.getRadians()
        );
        selfRelativeDrive(chassisSpeeds, isOpenLoop);
    }

    /**
     * Drives the swerve with the given velocities, relative to the field.
     *
     * @param translation the target x and y velocity.
     * @param rotation desired angle of the robot in radioants.
     * @param isOpenLoop determine if it uses velocities or percent output for the drive motor.
     **/
    void fieldRelativeDrive(Translation2d translation, Rotation2d rotation, boolean isOpenLoop) {
        ChassisSpeeds chassisSpeeds = ChassisSpeeds.fromFieldRelativeSpeeds(
                translation.getX(),
                translation.getY(),
                rotation.getRadians(),
                getHeading()
        );
        selfRelativeDrive(chassisSpeeds ,  isOpenLoop);
    }

    /**
     * Stops the swerve motors from moving.
     **/
    public void stop() {
        for(int id = 0; id < SwerveConstants.SWERVE_MODULES.length; id++)
            SwerveConstants.SWERVE_MODULES[id].stopModule();
    }

    private void setTargetModuleStates(SwerveModuleState[] swerveModuleStates , boolean isOpenLoop){
        for(int i = 0; i < 4; i++)
            SwerveConstants.SWERVE_MODULES[i].setTargetState(swerveModuleStates[i],isOpenLoop);
    }

    private void selfRelativeDrive(ChassisSpeeds chassisSpeeds ,boolean isOpenLoop) {
        SwerveModuleState[] swerveModuleStates = SwerveConstants.KINEMATICS.toSwerveModuleStates(chassisSpeeds);
        setTargetModuleStates(swerveModuleStates, isOpenLoop );
    }

    void zeroHeading() {
        SwerveConstants.gyro.setYaw(0);
    }

    private Rotation2d getHeading() {
        return Rotation2d.fromDegrees(SwerveConstants.gyro.getYaw());
    }

    void setHeading(double yaw) {
        SwerveConstants.gyro.setYaw(yaw);
    }
}

