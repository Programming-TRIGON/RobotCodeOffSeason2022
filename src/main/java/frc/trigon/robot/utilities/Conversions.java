package frc.trigon.robot.utilities;

public class Conversions {
    private static final int
            HUNDRED_MS_PER_SEC = 10,
            SEC_PER_MIN = 60,
            FALCON_TICKS = 2048;

    public static double hundredMsToSec(double hundredMs) {
        return hundredMs / HUNDRED_MS_PER_SEC;
    }

    public static double hundredMsToMin(double hundredMs) {
        return hundredMsToSec(hundredMs) / SEC_PER_MIN;
    }

    public static double falconTicksPer100MsToRpm(double ticksPer100Ms) {
        return hundredMsToMin(ticksPer100Ms) / FALCON_TICKS;
    }
}
