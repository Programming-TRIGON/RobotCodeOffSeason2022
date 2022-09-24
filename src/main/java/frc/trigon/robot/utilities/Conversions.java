package frc.trigon.robot.utilities;

public class Conversions {
    private static final double magTicks = 4096;
    private static final double falconTicks = 2048;

    public static double magTicksToDegrees(double magTicks) {
        return magTicksToRotations(magTicks) * 360;
    }

    public static double degreesToMagTicks(double degrees) {
        return degreesToRotations(degrees) * magTicks;
    }

    public static double degreesToRotations(double degrees) {
        return degrees / 360;
    }

    public static double revolutionsToDegrees(double rotations) {
        return rotations * 360;
    }

    public static double revolutionsToMag(double rotations) {
        return rotations * magTicks;
    }

    public static double magTicksToRotations(double MagTicks) {
        return MagTicks / magTicks;
    }

    public static double falconTicksToSeconds(double MagTicks) {
        return MagTicks * 10;
    }

    public static double falconTicksToRevolutions(double velocity) {
        return falconTicksToSeconds(velocity) / falconTicks;
    }

    public static double motorRevolutionsToSystemRevolutions(double revolutions, double gearRatio) {
        return revolutions / gearRatio;
    }

    public static double systemRotationsToMotorRotations(double rotations, double gearRatio) {
        return rotations * gearRatio;
    }

    /**
     * @param rotations     the rotations of the motor per seconds
     * @param circumference of the wheel
     * @return returns the meters per seconds
     **/
    public static double falconRevolutionsToMps(double rotations, double circumference, double gearRatio) {
        return motorRevolutionsToSystemRevolutions(rotations, gearRatio) * circumference;
    }

    public static double mpsToFalconTicks(Double rotations, double circumference, double gearRatio) {
        return systemRotationsToMotorRotations(rotations, gearRatio) / circumference;
    }

    public static double degreesToFalcon(double degrees) {
        return degreesToRotations(degrees) * falconTicks;
    }
}

