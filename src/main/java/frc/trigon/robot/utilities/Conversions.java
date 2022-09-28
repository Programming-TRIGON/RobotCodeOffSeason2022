package frc.trigon.robot.utilities;

public class Conversions {
    public static double voltageToCompensatedPower(double voltage, double voltageCompensationSaturation) {
        return voltage/voltageCompensationSaturation;
    }
}
