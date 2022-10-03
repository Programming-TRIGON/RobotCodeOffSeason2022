package frc.trigon.robot.utilities;

public class Conversions {
    private static final int
            HUNDRED_MS_PER_SEC = 10,
            SEC_PER_MIN = 60,
            FALCON_TICKS = 2048;

    public static double velocityPerHundred100MsToSec(double velocity) {
        return velocity * HUNDRED_MS_PER_SEC;
    }

    public static double velocityPer100MsToMin(double velocity) {
        return velocityPerHundred100MsToSec(velocity) * SEC_PER_MIN;
    }

    public static double falconTicksPer100MsToRpm(double ticksPer100Ms) {
        return velocityPer100MsToMin(ticksPer100Ms) / FALCON_TICKS;
    }
}
