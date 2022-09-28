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

    /****
     * @param translation of the wanted robot postions of x and y.
     * @param rotation the wanted postion angle of the robot given in pie.
     *
     * robot drives relitive to itself.
     ****/
    void selfRelativeDrive(Translation2d translation, Rotation2d rotation) {
        ChassisSpeeds chassisSpeeds = new ChassisSpeeds(
                translation.getX(),
                translation.getY(),
                rotation.getRadians()
        );
        relativeDrive(chassisSpeeds);
    }

    /****
     * @param translation of the wanted robot postions of x and y.
     * @param rotation the wanted postion angle of the robot given in pie.
     *
     * robot drives reltive to the driver.
     ****/
    void fieldRelativeDrive(Translation2d translation, Rotation2d rotation) {
        ChassisSpeeds chassisSpeeds = ChassisSpeeds.fromFieldRelativeSpeeds(
                translation.getX(),
                translation.getY(),
                rotation.getRadians(),
                getHeading()
        );
        relativeDrive(chassisSpeeds);
    }
    //stops the robot from moving.
    public void stop() {
        for(int id = 0; id < SwerveConstants.SWERVE_MODULES.length; id++)
            SwerveConstants.SWERVE_MODULES[id].stopModule();
    }

    private void setTargetModuleStates(SwerveModuleState[] swerveModuleStates) {
        for(int i = 0; i < 4; i++)
            SwerveConstants.SWERVE_MODULES[i].setTargetState(swerveModuleStates[i]);
    }

    private void relativeDrive(ChassisSpeeds chassisSpeeds) {
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

