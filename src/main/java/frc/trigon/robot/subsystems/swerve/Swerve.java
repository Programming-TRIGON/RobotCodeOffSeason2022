package frc.trigon.robot.subsystems.swerve;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Swerve extends SubsystemBase {
    private final static Swerve INSTANCE = new Swerve();

    private Swerve() {
        zeroHeading();
        putOnDashboard();
    }

    public static Swerve getInstance() {
        return INSTANCE;
    }

    /**
     * Drives the swerve with the given velocities, relative to the robot.
     *
     * @param translation the target x and y velocities in mps
     * @param rotation    the target theta velocity in radians per second
     */
    void selfRelativeDrive(Translation2d translation, Rotation2d rotation) {
        ChassisSpeeds chassisSpeeds = new ChassisSpeeds(
                translation.getX(),
                translation.getY(),
                rotation.getRadians()
        );

        selfRelativeDrive(chassisSpeeds);
    }

    /**
     * Drives the swerve with the given velocities, relative to the field.
     *
     * @param translation the target x and y velocities in mps
     * @param rotation    the target theta velocity in radians per second
     */
    void fieldRelativeDrive(Translation2d translation, Rotation2d rotation) {
        ChassisSpeeds chassisSpeeds = ChassisSpeeds.fromFieldRelativeSpeeds(
                translation.getX(),
                translation.getY(),
                rotation.getRadians(),
                getHeading()
        );

        selfRelativeDrive(chassisSpeeds);
    }

    private void selfRelativeDrive(ChassisSpeeds chassisSpeeds) {
        SwerveModuleState[] swerveModuleStates = SwerveConstants.KINEMATICS.toSwerveModuleStates(chassisSpeeds);
        if(isStill(chassisSpeeds))
            return;
        setTargetModuleStates(swerveModuleStates);
    }

    /**
     * Stops the swerve's motors.
     */
    public void stop() {
        for(SwerveModule module : SwerveConstants.SWERVE_MODULES)
            module.stop();
    }

    private void setTargetModuleStates(SwerveModuleState[] swerveModuleStates) {
        for(int i = 0; i < 4; i++)
            SwerveConstants.SWERVE_MODULES[i].setTargetState(swerveModuleStates[i]);
    }

    void zeroHeading() {
        setHeading(0);
    }

    Rotation2d getHeading() {
        return Rotation2d.fromDegrees(SwerveConstants.gyro.getYaw());
    }

    void setHeading(double yaw) {
        SwerveConstants.gyro.setYaw(yaw);
    }

    private boolean isStill(ChassisSpeeds chassisSpeeds) {
        return
                Math.abs(chassisSpeeds.vxMetersPerSecond) < SwerveConstants.DEAD_BAND_DRIVE_DEADBAND &&
                        Math.abs(chassisSpeeds.vyMetersPerSecond) < SwerveConstants.DEAD_BAND_DRIVE_DEADBAND &&
                        Math.abs(chassisSpeeds.omegaRadiansPerSecond) < SwerveConstants.DEAD_BAND_DRIVE_DEADBAND;
    }

    private void putOnDashboard() {
        for(int i = 0; i < SwerveConstants.SWERVE_MODULES.length; i++) {
            SmartDashboard.putData(
                    "Swerve/" + SwerveModuleConstants.SwerveModules.fromId(i).name(),
                    SwerveConstants.SWERVE_MODULES[i]
            );
        }
        SmartDashboard.putData("Swerve", this);
    }
}

