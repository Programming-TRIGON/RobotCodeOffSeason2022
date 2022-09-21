package frc.trigon.robot.subsystems.swerve;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.sensors.Pigeon2;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;

public class SwerveConstants {

    public static final double MAX_SPEED_M = 3;
    public static final Translation2d[] LOCATIONS = {
            SwerveModules.fromId(0).Location,
            SwerveModules.fromId(1).Location,
            SwerveModules.fromId(2).Location,
            SwerveModules.fromId(3).Location
    };
    private static final double SIDE_LENGTH_METERS = 0.5;
    private static final double HALF_SIDE_LENGTH_METERS = 0.25;

    private static final Translation2d FRONT_LEFT_MODULE_LOCATION = new Translation2d(
            HALF_SIDE_LENGTH_METERS,
            HALF_SIDE_LENGTH_METERS);
    private static final Translation2d FRONT_RIGHT_MODULE_LOCATION = new Translation2d(
            HALF_SIDE_LENGTH_METERS,
            -HALF_SIDE_LENGTH_METERS);
    private static final Translation2d REAR_LEFT_MODULE_LOCATION = new Translation2d(
            -HALF_SIDE_LENGTH_METERS,
            HALF_SIDE_LENGTH_METERS);
    private static final Translation2d REAR_RIGHT_MODULE_LOCATION = new Translation2d(
            -HALF_SIDE_LENGTH_METERS,
            -HALF_SIDE_LENGTH_METERS);
    private static final int PIGEON_ID = 12;
    public static final Pigeon2 gyro = new Pigeon2(PIGEON_ID);
    private static final WPI_TalonSRX FRONT_LEFT_ANGLE_ENCODER = new WPI_TalonSRX(0);
    private static final WPI_TalonFX FRONT_LEFT_DRIVE_MOTOR = new WPI_TalonFX(1);
    private static final WPI_TalonFX FRONT_LEFT_ANGLE_MOTOR = new WPI_TalonFX(2);
    private static final double FRONT_LEFT_ENCODER_OFFSET = 0;
    private static final WPI_TalonSRX FRONT_RIGHT_ANGLE_ENCODER = new WPI_TalonSRX(3);
    private static final WPI_TalonFX FRONT_RIGHT_DRIVE_MOTOR = new WPI_TalonFX(4);
    private static final WPI_TalonFX FRONT_RIGHT_ANGLE_MOTOR = new WPI_TalonFX(5);
    private static final double FRONT_RIGHT_ENCODER_OFFSET = 0;
    private static final WPI_TalonSRX REAR_LEFT_ANGLE_ENCODER = new WPI_TalonSRX(6);
    private static final WPI_TalonFX REAR_LEFT_DRIVE_MOTOR = new WPI_TalonFX(7);
    private static final WPI_TalonFX REAR_LEFT_ANGLE_MOTOR = new WPI_TalonFX(8);
    private static final double RIGHT_LEFT_ENCODER_OFFSET = 0;
    private static final WPI_TalonSRX REAR_RIGHT_ANGLE_ENCODER = new WPI_TalonSRX(9);
    private static final WPI_TalonFX REAR_RIGHT_DRIVE_MOTOR = new WPI_TalonFX(10);
    private static final WPI_TalonFX REAR_RIGHT_ANGLE_MOTOR = new WPI_TalonFX(11);
    private static final double REAR_RIGHT_ENCODER_OFFSET = 0;
    private static final int FRONT_LEFT_ID = 0;
    private static final int FRONT_RIGHT_ID = 1;
    private static final int REAR_LEFT_ID = 2;
    private static final int REAR_RIGHT_ID = 3;
    private static final SwerveModuleConstants FRONT_LEFT_SWERVE_MODULE_CONSTANTS = new SwerveModuleConstants(
            FRONT_LEFT_ANGLE_ENCODER, FRONT_LEFT_DRIVE_MOTOR, FRONT_LEFT_ANGLE_MOTOR, FRONT_LEFT_ENCODER_OFFSET);
    private static final SwerveModuleConstants FRONT_RIGHT_SWERVE_MODULE_CONSTANTS = new SwerveModuleConstants(
            FRONT_RIGHT_ANGLE_ENCODER, FRONT_RIGHT_DRIVE_MOTOR, FRONT_RIGHT_ANGLE_MOTOR, FRONT_RIGHT_ENCODER_OFFSET);
    private static final SwerveModuleConstants REAR_LEFT_SWERVE_MODULE_CONSTANTS = new SwerveModuleConstants(
            REAR_LEFT_ANGLE_ENCODER, REAR_LEFT_DRIVE_MOTOR, REAR_LEFT_ANGLE_MOTOR, RIGHT_LEFT_ENCODER_OFFSET);
    private static final SwerveModuleConstants REAR_RIGHT_SWERVE_MODULE_CONSTANTS = new SwerveModuleConstants(
            REAR_RIGHT_ANGLE_ENCODER, REAR_RIGHT_DRIVE_MOTOR, REAR_RIGHT_ANGLE_MOTOR, REAR_RIGHT_ENCODER_OFFSET);
    public static SwerveModule[] SWERVEMODULES = {
            new SwerveModule(SwerveConstants.SwerveModules.fromId(0).swerveModuleConstants),
            new SwerveModule(SwerveConstants.SwerveModules.fromId(1).swerveModuleConstants),
            new SwerveModule(SwerveConstants.SwerveModules.fromId(2).swerveModuleConstants),
            new SwerveModule(SwerveConstants.SwerveModules.fromId(3).swerveModuleConstants)
    };
    static SwerveDriveKinematics KINEMATICS = new SwerveDriveKinematics(SwerveConstants.LOCATIONS);

    public enum SwerveModules {
        FRONT_LEFT(FRONT_LEFT_ID, FRONT_LEFT_SWERVE_MODULE_CONSTANTS, REAR_LEFT_MODULE_LOCATION),
        FRONT_RIGHT(FRONT_RIGHT_ID, FRONT_RIGHT_SWERVE_MODULE_CONSTANTS, FRONT_RIGHT_MODULE_LOCATION),
        REAR_LEFT(REAR_LEFT_ID, REAR_LEFT_SWERVE_MODULE_CONSTANTS, REAR_LEFT_MODULE_LOCATION),
        REAR_RIGHT(REAR_RIGHT_ID, REAR_RIGHT_SWERVE_MODULE_CONSTANTS, REAR_RIGHT_MODULE_LOCATION),
        ;

        int id;
        SwerveModuleConstants swerveModuleConstants;
        Translation2d Location;

        SwerveModules(int id, SwerveModuleConstants swerveModuleConstants, Translation2d location) {
            this.id = id;
            this.swerveModuleConstants = swerveModuleConstants;
            this.Location = location;
        }

        public static SwerveModules fromId(int id) {
            if(id == SwerveModules.FRONT_LEFT.id)
                return SwerveModules.FRONT_LEFT;
            if(id == SwerveModules.FRONT_RIGHT.id)
                return SwerveModules.FRONT_RIGHT;
            if(id == SwerveModules.REAR_LEFT.id)
                return SwerveModules.REAR_LEFT;
            if(id == SwerveModules.REAR_RIGHT.id)
                return SwerveModules.REAR_RIGHT;
            return null;
        }
    }
}
