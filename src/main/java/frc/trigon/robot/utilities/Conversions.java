package frc.trigon.robot.utilities;

public class Conversions {
    private static final double MAG_TICKS = 4096;
    private static final double FALCON_TICKS = 2048;

    public static double ticksToDegrees(double magTicks) {
        return ticksToRevolutions(magTicks) * 360;
    }

    public static double degreesToMagTicks(double degrees) {
        return degreesToRevolutions(degrees) * MAG_TICKS;
    }

    public static double degreesToRevolutions(double degrees) {
        return degrees / 360;
    }

    public static double revolutionsToDegrees(double revolutions) {
        return revolutions * 360;
    }

    public static double revolutionsToMagTicks(double revolutions) {
        return revolutions * MAG_TICKS;
    }

    public static double ticksToRevolutions(double magTicks) {
        return magTicks / MAG_TICKS;
    }

    public static double hundredMsToSeconds(double hundredMs) {
        return hundredMs * 10;
    }

    public static double falconTicksToRevolutions(double ticks) {
        return ticks / FALCON_TICKS;
    }

    public static double motorRevolutionsToSystemRevolutions(double revolutions, double gearRatio) {
        return revolutions / gearRatio;
    }

    public static double systemRevolutionsToMotorRevolutions(double revolutions, double gearRatio) {
        return revolutions * gearRatio;
    }

    /**
     * @param revolutions   of the motor per second.
     * @param circumference of the Scope of the wheel.
     * @return returns the meters per seconds.
     **/
    public static double revolutionsToMeters(double revolutions, double circumference, double gearRatio) {
        return motorRevolutionsToSystemRevolutions(revolutions, gearRatio) * circumference;
    }

    public static double systemRevolutionsToFalconTicks(double revolutions, double circumference, double gearRatio) {
        return systemRevolutionsToMotorRevolutions(revolutions, gearRatio) / circumference;
    }

    public static double degreesToFalconTicks(double degrees) {
        return degreesToRevolutions(degrees) * FALCON_TICKS;
    }
}

