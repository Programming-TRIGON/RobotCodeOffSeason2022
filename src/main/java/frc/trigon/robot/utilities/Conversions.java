package frc.trigon.robot.utilities;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Conversions {
    public static double magToDegrees(double magTicks) {
        return magTicks / 4096f * 360;
    }
    public static double degreesToMag(double degrees){return  degrees / 360 * 406f;}

    // cm radicus 30
    ///public  static double degreesToMag(double degrees) {return degrees * 4096f / 4096f;}
    public static double degreesToRotations(double degrees) {
        return degrees / 360f;
    }

    public static double rotationToDegrees(double rotations) {
        return rotations * 360;
    }

    public static double rotationsToMag(double rotations) {
        return rotations * 4096f;
    }

    public static double falconToSeconds(double velocity) {
        return velocity * 10;
    }

    public static double falconToRotations(double velocity) {
        return (velocity * 10) / 2048f;
    }

    public static double motorRotationsToSystemRotations(double rotations, double gearRatio) {
        return rotations / gearRatio;
    }

    public static double systemRotationsToMotorRotations(double rotations, double gearRatio) {
        return rotations * gearRatio;
    }

    /**
     * @param rotations     the rotations of the motor per seconds
     * @param circumference of the wheel
     * @return returns the meters per sec in seconds
     **/
    public static double falconToMps(double rotations, double circumference, double gearRatio) {
        return motorRotationsToSystemRotations(rotations, gearRatio) * circumference;
    }

    public static double mpsToFalcon(Double rotations , double circumference ,double gearRatio){
        return systemRotationsToMotorRotations(rotations ,gearRatio) / circumference;
    }



    public static double degreesToFalcon(double degrees, double gearRatio) {
        return degrees / 360 * (gearRatio * 2048.0);
    }
}

