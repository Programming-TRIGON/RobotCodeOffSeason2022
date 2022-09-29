package frc.trigon.robot.utilities;

public class Conversions {
    public static final int MAG_TICKS = 4096;

    public double ticksToDegrees(double ticks, double gearRatio) {
        double rotations = ticks / 4096;
        double degrees = rotations * 360;
        return degrees / gearRatio;
    }

    public double degreesToTicks(double degrees, double gearRatio) {
        double rotations = degrees / 360;
        double ticks = rotations * 4096;
        return ticks / gearRatio;
    }
}
