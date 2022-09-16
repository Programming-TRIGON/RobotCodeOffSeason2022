package frc.trigon.robot.utilities;

public class Conversions {
    public static final double VOLTAGE_SATURATION = 10.5;

    public static double voltageToCompensatedVoltage(double voltage) {
        return voltage/ VOLTAGE_SATURATION;
    }
}
