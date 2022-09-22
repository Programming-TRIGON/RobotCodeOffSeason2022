package frc.trigon.robot.utilities;

public class Conversions {
    private static final int
            HUNDRED_MS_PER_SEC = 10,
            SEC_PER_MIN = 60,
            FALCON_TICKS = 2048;

    public static double hundredMsToSec(double ms) {
        return ms * HUNDRED_MS_PER_SEC;
    }

    public static double hundredMsToMin(double ms) {
        return hundredMsToSec(ms) * SEC_PER_MIN;
    }

    public static double falconTicksToRevolutions(double ticks) {
        return ticks / FALCON_TICKS;
    }

    public static double falconTicksPer100MsToRpm(double ticksPer100Ms) {
        return hundredMsToMin(ticksPer100Ms) / FALCON_TICKS;
    }
}
