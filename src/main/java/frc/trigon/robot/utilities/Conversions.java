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

    public static double systemPositionToMotorPosition(double position, double gearRatio) {
        return position * gearRatio;
    }

    /**
     * The offset will be added to the target position,
     * in order to compensate for the fact that the position is not 0 ware we want it to be.
     *
     * @param position the target position of the motor.
     * @param offset   the encoder value when the system is on zero position
     * @return the offsetted position
     */
    public static double offsetWrite(double position, double offset) {
        return position + offset;
    }

    /**
     * @param position
     * @param offset
     * @return
     */
    public static double offsetRead(double position, double offset) {
        return position - offset;
    }
}
