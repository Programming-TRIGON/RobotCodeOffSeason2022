package frc.trigon.robot.subsystems.swerve;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Swerve extends SubsystemBase {
    private final static Swerve INSTANCE = new Swerve();

    public Swerve() {
        SwerveModule[] swerveModules = SwerveConstants.SWERVE_MODULES;
        zeroHeading();
    }

    public static Swerve getInstance() {
        return INSTANCE;
    }

    /****
     * @param translation the x and y
     * @param rotation the rotation of the robot
     * robot drives in first person
     * ****/
    void selfRelativeDrive(Translation2d translation, Rotation2d rotation) {
        ChassisSpeeds chassisSpeeds = new ChassisSpeeds(
                translation.getX(),
                translation.getY(),
                rotation.getRadians()
        );
        relitive(chassisSpeeds);
    }

    /****
     * @param translation the x and y
     * @param rotation the rotation of the robot
     * robot drives in 3d person
     * ****/
    void fieldRelativeDrive(Translation2d translation, Rotation2d rotation) {
        ChassisSpeeds chassisSpeeds = ChassisSpeeds.fromFieldRelativeSpeeds(
                translation.getX(),
                translation.getY(),
                rotation.getRadians(),
                getHeading()
        );
        relitive(chassisSpeeds);
    }

    public void stop() {
        for(int id = 0; id < SwerveConstants.SWERVE_MODULES.length; id++)
            SwerveConstants.SWERVE_MODULES[id].stopModule();
    }

    private void setTargetModuleStates(SwerveModuleState[] swerveModuleStates) {
        for(int i = 0; i < 4; i++)
            SwerveConstants.SWERVE_MODULES[i].setTargetState(swerveModuleStates[i]);
    }

    private void relitive(ChassisSpeeds chassisSpeeds) {
        SwerveModuleState[] swerveModuleStates = SwerveConstants.KINEMATICS.toSwerveModuleStates(chassisSpeeds);
        setTargetModuleStates(swerveModuleStates);
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

