package frc.trigon.robot.utilities;

public class Conversions {
    public static double hundredMsToSec(double ms)
    {
        return ms*10;
    }
    public static double hundredMsToMin(double ms)
    {
        return hundredMsToSec(ms)*60;
    }
    public static double hundredMsToTickPerMin(double ms)
    {
        return hundredMsToMin(ms)*2048;
    }
}
