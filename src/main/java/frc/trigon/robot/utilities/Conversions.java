package frc.trigon.robot.utilities;

public class Conversions {

    /**
     * Converts voltage to compensated power
     *
     * @param voltage the voltage
     * @param voltageCompensationSaturation the saturation of the compensation
     * @return the compensated power
     */
    public static double voltageToCompensatedPower(double voltage, double voltageCompensationSaturation) {
        return voltage/voltageCompensationSaturation;
    }
}
