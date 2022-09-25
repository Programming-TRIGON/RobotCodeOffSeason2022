package frc.trigon.robot.utilities;

public class Conversions {
    private static final double MAG_TICKS = 4096;
    private static final double FALCON_TICKS = 2048;

    public static double magTicksToDegrees(double magTicks) {
        return magTicksToRevolutions(magTicks) * 360;
    }

    public static double degreesToMagTicks(double degrees) {
        return degreesToRevolutions(degrees) * MAG_TICKS;
    }

    public static double degreesToRevolutions(double degrees) {
        return degrees / 360;
    }

    public static double revolutionsToDegrees(double rotations) {
        return rotations * 360;
    }

    public static double revolutionsToMag(double rotations) {
        return rotations * MAG_TICKS;
    }

    public static double magTicksToRevolutions(double MagTicks) {
        return MagTicks / MAG_TICKS;
    }

    public static double mpmsToMps(double MagTicks) {
        return MagTicks * 10;
    }

    public static double falconTicksToRevolutions(double ticks) {
        return mpmsToMps(ticks) / FALCON_TICKS;
    }

    public static double motorRevolutionsToSystemRevolutions(double revolutions, double gearRatio) {
        return revolutions / gearRatio;
    }

    public static double systemRevolutionsToMotorRevolutions(double rotations, double gearRatio) {
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
        return systemRevolutionsToMotorRevolutions(rotations, gearRatio) / circumference;
    }

    public static double degreesToFalcon(double degrees) {
        return degreesToRevolutions(degrees) * FALCON_TICKS;
    }
}

