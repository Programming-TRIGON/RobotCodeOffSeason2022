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

    public static double velocityPerSecTo100Ms(double velocity) {
        return velocity / HUNDRED_MS_PER_SEC;
    }

    public static double velocityPerMinToSec(double velocity) {
        return velocityPerSecTo100Ms(velocity) / SEC_PER_MIN;
    }

    public static double RpmToFalconTicksPer100Ms(double rpm) {
        return velocityPerMinToSec(rpm) * 2048;
    }

    public static double calculatePolynomial(double a, double b, double c, double x) {
        return (a * Math.pow(x, 2)) + (b * x) + c;
    }
}
