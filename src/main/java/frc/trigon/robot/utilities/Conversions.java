package frc.trigon.robot.utilities;

public class Conversions {
    public static double voltageToCompensatedPower(double currentVoltage, double voltageCompensation) {
        return currentVoltage / voltageCompensation;
    }
}
