package frc.trigon.robot.utilities;

public class Conversions {
    private static final int
                hundredMsPerSec = 10,
            hundredMsPerMin = 60,
            falconTicks = 2048;

    public static double hundredMsToSec(double ms) {
        return ms * hundredMsPerSec;
    }

    public static double hundredMsToMin(double ms) {
        return hundredMsToSec(ms) * hundredMsPerMin;
    }

    public static double falconTicksToRevolutions(double ticks) {
        return ticks / falconTicks;
    }

    public static double ticksPer100Ms(double velocityInHundredMs) {
        return hundredMsToMin(velocityInHundredMs) / falconTicksToRevolutions(velocityInHundredMs);
    }
}
