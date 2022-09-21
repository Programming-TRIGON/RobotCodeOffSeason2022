package frc.trigon.robot.subsystems.swerve;

import com.ctre.phoenix.sensors.Pigeon2;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveDriveOdometry;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Swerve extends SubsystemBase {
    private final static Swerve INSTANCE = new Swerve();

    public static Swerve getInstance() {
        return INSTANCE;
    }

    private SwerveDriveOdometry swerveOdometry;
    private SwerveDriveKinematics kinematics;
    private SwerveModule[] swerveModules = SwerveConstants.SWERVEMODULES;
    private Pigeon2 gyro;

    public Swerve() {
        SwerveDriveKinematics kinematics = SwerveConstants.KINEMATICS;
        gyro = SwerveConstants.gyro;

        zeroHeading();
    }

    void selfRelativeDrive(Translation2d translation, Rotation2d radiant) {
        selfRelitiveDrive(new ChassisSpeeds(
                translation.getX(),
                translation.getY(),
                radiant.getRadians()
        ));
    }

    void fieldRelativeDrive(Translation2d translation, Rotation2d radiant) {
        selfRelitiveDrive(
                ChassisSpeeds.fromFieldRelativeSpeeds(translation.getX(), translation.getY(), radiant.getRadians(),
                        getHeading()));
    }

    public void stop() {
        SwerveModuleState[] swerveModuleStates = kinematics.toSwerveModuleStates(
                new ChassisSpeeds(0, 0, 0));
        setTargetModuleStates(swerveModuleStates);
    }

    private void setTargetModuleStates(SwerveModuleState[] swerveModuleStates) {
        for(int i = 0; i < 4; i++)
            swerveModules[i].setTargetState(swerveModuleStates[i]);
    }

    private void selfRelitiveDrive(ChassisSpeeds chassisSpeeds) {
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

