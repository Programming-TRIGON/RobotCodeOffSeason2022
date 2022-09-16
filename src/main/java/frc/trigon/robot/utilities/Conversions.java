package frc.trigon.robot.utilities;

public class Conversions {
    public static double voltageToCompensatedPower(double currentVoltage, double voltageCompensationSaturation) {
        return currentVoltage / voltageCompensationSaturation;
    }
}
