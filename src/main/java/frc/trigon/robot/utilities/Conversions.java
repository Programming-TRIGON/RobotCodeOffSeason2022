package frc.trigon.robot.utilities;

public class Conversions {
    public static final int MAG_TICKS = 4096;
    public static final int DEGREES_PER_REVOLUTIONS = 360;

    public static double magTicksToDegrees(double magTicks) {
        return magTicksToRevolutions(magTicks) * DEGREES_PER_REVOLUTIONS;
    }

    public static double magTicksToRevolutions(double magTicks) {
        return magTicks / MAG_TICKS;
    }

    public static double degreesToMagTicks(double degrees) {
        return degreesToRevolutions(degrees) * MAG_TICKS;
    }

    public static double degreesToRevolutions(double degrees) {
        return degrees / DEGREES_PER_REVOLUTIONS;
    }

    public static double motorPositionToSystemPosition(double position, double gearRatio) {
        return position / gearRatio;
    }

    public static double offsetWrite() {
        return position + offset;
    }

    public static double offsetRead(double position, double offset) {
        return position - offset;
    }
}
