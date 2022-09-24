package frc.trigon.robot.subsystems.swerve;

import com.ctre.phoenix.sensors.Pigeon2;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Swerve extends SubsystemBase {
    private final static Swerve INSTANCE = new Swerve();
    private SwerveDriveKinematics kinematics;
    private SwerveModule[] swerveModules = SwerveConstants.SWERVE_MODULES;
    private Pigeon2 gyro;
    private ChassisSpeeds chassisSpeeds;

    public Swerve() {
        gyro = SwerveConstants.gyro;
        SwerveDriveKinematics kinematics = SwerveConstants.KINEMATICS;

        zeroHeading();
    }

    public static Swerve getInstance() {
        return INSTANCE;
    }

    void selfRelativeDrive(Translation2d translation, Rotation2d rotation) {
        chassisSpeeds = new ChassisSpeeds(
                translation.getX(),
                translation.getY(),
                rotation.getRadians());
        relitiveDrive(chassisSpeeds);
    }

    void fieldRelativeDrive(Translation2d translation, Rotation2d rotation) {
        chassisSpeeds = ChassisSpeeds.fromFieldRelativeSpeeds(
                translation.getX(),
                translation.getY(),
                rotation.getRadians(),
                getHeading());
        relitiveDrive(chassisSpeeds);
    }

    public void stop() {
        for(int i = 0; i < swerveModules.length; i++) {
            stopModule(i);
        }
    }

    public void stopModule(int id) {
        swerveModules[id].stopModule();
    }

    private void setTargetModuleStates(SwerveModuleState[] swerveModuleStates) {
        for(int i = 0; i < 4; i++)
            swerveModules[i].setTargetState(swerveModuleStates[i]);
    }

    private void relitiveDrive(ChassisSpeeds chassisSpeeds) {
        SwerveModuleState[] swerveModuleStates = kinematics.toSwerveModuleStates(chassisSpeeds);
        setTargetModuleStates(swerveModuleStates);
    }

    private void zeroHeading() {
        gyro.setYaw(0);
    }

    private Rotation2d getHeading() {
        return Rotation2d.fromDegrees(gyro.getYaw());
    }
}

