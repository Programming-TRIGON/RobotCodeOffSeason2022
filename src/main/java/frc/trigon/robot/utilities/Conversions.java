package frc.trigon.robot.utilities;

public class Conversions {

    private static final double maxMagTicks = 4096;
    private static final double maxFalconTicks = 2048;

    public static double magToDegrees(double magTicks) {
        return magToRotations(magTicks) * 360;
    }

    public static double degreesToMag(double degrees) {
        return degreesToRotations(degrees) *maxMagTicks;
    }

    public static double degreesToRotations(double degrees) {
        return degrees / 360;
    }

    public static double rotationToDegrees(double rotations) {
        return rotations * 360;
    }

    public static double rotationsToMag(double rotations) {
        return rotations * maxMagTicks;
    }

    public static double magToRotations(double magTicks) {
        return magTicks / maxMagTicks;
    }

    public static double falconToSeconds(double velocity) {
        return velocity * 10;
    }

    public static double falconToRotations(double velocity) {
        return falconToSeconds(velocity) / maxFalconTicks;
    }

    public static double motorRevolutionsToSystemRotations(double rotations, double gearRatio) {
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
        return motorRevolutionsToSystemRotations(rotations, gearRatio) * circumference;
    }

    public static double mpsToFalcon(Double rotations, double circumference, double gearRatio) {
        return systemRotationsToMotorRotations(rotations, gearRatio) / circumference;
    }

    public static double degreesToFalcon(double degrees, double gearRatio) {
        return degrees / 360 * (gearRatio * maxFalconTicks);
    }
}

