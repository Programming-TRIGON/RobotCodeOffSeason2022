package frc.trigon.robot.utilities;

public class Conversions {

    /**
     * Converts voltage to compensated power
     *
     * Talon FX/SRX and Victor SPX can be configured to adjust their outputs in response to the battery
     * voltage measurement (in all control modes). Use the voltage compensation saturation config to
     * determine what voltage represents 100% output.
     * The compensated power is the voltage divided by the saturation of the compensation
     *
     * @param voltage the voltage
     * @param voltageCompensationSaturation the saturation of the compensation
     * @return the compensated power
     */
    public static double voltageToCompensatedPower(double voltage, double voltageCompensationSaturation) {
        return voltage/voltageCompensationSaturation;
    }
}
