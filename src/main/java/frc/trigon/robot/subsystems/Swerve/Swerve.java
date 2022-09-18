package frc.trigon.robot.subsystems.Swerve;

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
        SwerveDriveKinematics kinematics = new SwerveDriveKinematics(SwerveConstants.LOCATIONS);

        swerveModules = new SwerveModule[4];
        swerveModules[SwerveConstants.SwerveModules.FRONT_LEFT.id] = new SwerveModule(
                SwerveConstants.SwerveModules.FRONT_LEFT.swerveModuleConstants);
        swerveModules[SwerveConstants.SwerveModules.FRONT_RIGHT.id] = new SwerveModule(
                SwerveConstants.SwerveModules.REAR_RIGHT.swerveModuleConstants);
        swerveModules[SwerveConstants.SwerveModules.REAR_LEFT.id] = new SwerveModule(
                SwerveConstants.SwerveModules.REAR_LEFT.swerveModuleConstants);
        swerveModules[SwerveConstants.SwerveModules.REAR_RIGHT.id] = new SwerveModule(
                SwerveConstants.SwerveModules.REAR_RIGHT.swerveModuleConstants);

        gyroSetZero();
    }

    public void selfRelativeDrive(Translation2d translation, double radiant) {
        SwerveModuleState[] swerveModuleStates = kinematics.toSwerveModuleStates(new ChassisSpeeds(
                translation.getX(),
                translation.getY(),
                radiant
        ));
        setSwerveModules(swerveModuleStates);
    }

    public void fieldRelativeDrive(Translation2d translation, double radiant) {
        SwerveModuleState[] swerveModuleStates = kinematics.toSwerveModuleStates(ChassisSpeeds.fromFieldRelativeSpeeds(
                translation.getX(),
                translation.getY(),
                radiant,
                getGyroAngle()
        ));
        setSwerveModules(swerveModuleStates);
    }

    public void stop() {
        SwerveModuleState[] swerveModuleStates = kinematics.toSwerveModuleStates(
                new ChassisSpeeds(0, 0, 0));
        setSwerveModules(swerveModuleStates);
    }

    private void setSwerveModules(SwerveModuleState[] swerveModuleStates) {
        for(int i = 0; i < 4; i++) {
            swerveModules[i].setTargetState(swerveModuleStates[i]);
        }
    }

    private Rotation2d getGyroAngle() {
        return Rotation2d.fromDegrees(gyro.getYaw());
    }

    public double[] getsModulesVelociteys() {
        double[] velociteys = new double[12];
        for(int i = 0; i < velociteys.length; i++) {
            velociteys[i] = getModuleVelocity(i / 4)[i % 3];
        }
        return velociteys;
    }

    public double[] getModuleVelocity(int swerveModuleNumber) {
        double[] velocity = {
                swerveModules[swerveModuleNumber].getRawAngleVelocity(),
                swerveModules[swerveModuleNumber].getRawDriverVelocity(),
                swerveModules[swerveModuleNumber].getRawAngleEncoderVelocity()
        };

        return velocity;
    }

    private void gyroSetZero() {
        gyro.setYaw(0);
    }
}

