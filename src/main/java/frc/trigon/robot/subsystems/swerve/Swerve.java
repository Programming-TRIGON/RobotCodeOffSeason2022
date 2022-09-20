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
    private SwerveModule[] swerveModules;
    private Pigeon2 gyro;

    public Swerve() {
        gyro = SwerveConstants.gyro;
        SwerveDriveKinematics kinematics = SwerveConstants.kinematics;

        getModules();
        // gyroSetZero();
    }

    void selfRelativeDrive(Translation2d translation, Rotation2d radiant) {
        selfRelitive(selfRelitiveChassisSpeeds(translation,radiant));
    }

    void fieldRelativeDrive(Translation2d translation, Rotation2d radiant) {
        selfRelitive(fieldRelitiveChassisSpeeds(translation, radiant ));
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

    private Rotation2d getGyroAngle() {
        return Rotation2d.fromDegrees(gyro.getYaw());
    }

    public double[] getsModulesVelociteys() {
        double[] velociteys = new double[12];
        for(int i = 0; i < velociteys.length; i++)
            velociteys[i] = getModuleVelocity(i / 4)[i % 3];
        return velociteys;
    }

    public double[] getModuleVelocity(int swerveModuleNumber) {
        double[] velocity = {
                swerveModules[swerveModuleNumber].getRawAngleVelocity(),
                swerveModules[swerveModuleNumber].getRawDriveVelocity(),
                swerveModules[swerveModuleNumber].getRawAngleEncoderVelocity()
        };

        return velocity;
    }

    private void gyroSetZero() {
        gyro.setYaw(0);
    }

    private void getModules() {
        swerveModules = new SwerveModule[4];
        for(int i = 0; i < swerveModules.length; i++)
            swerveModules[i] = SwerveConstants.swerveModules[i];
    }

    private ChassisSpeeds fieldRelitiveChassisSpeeds(Translation2d translation, Rotation2d radiant) {
        return  ChassisSpeeds.fromFieldRelativeSpeeds(
                translation.getX(),
                translation.getY(),
                radiant.getRadians(),
                getGyroAngle());
    }

    private ChassisSpeeds selfRelitiveChassisSpeeds(Translation2d translation, Rotation2d radiant) {
        return new ChassisSpeeds(
                translation.getX(),
                translation.getY(),
                radiant.getRadians()
        );

    }

    private void selfRelitive(ChassisSpeeds chassisSpeeds) {
        SwerveModuleState[] swerveModuleStates = kinematics.toSwerveModuleStates(chassisSpeeds);
        setTargetModuleStates(swerveModuleStates);
    }
}

