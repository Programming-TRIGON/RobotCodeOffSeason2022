package frc.trigon.robot.utilities;

public class Conversions {
    public static final double VOLTAGE_COMPENSATION_SATURATION = 10.5;

    /**
     * Converts voltage to compensated power
     * @param voltage the voltage
     * @return Compensated power
     */
    public static double voltageToCompensatedPower(double voltage) {
        return voltage/ VOLTAGE_COMPENSATION_SATURATION;
    }
}
