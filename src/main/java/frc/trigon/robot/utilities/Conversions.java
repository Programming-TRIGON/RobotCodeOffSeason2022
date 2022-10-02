package frc.trigon.robot.utilities;

public class Conversions {

        /**
        * Converts voltage to compensated power
        *
        * The voltage compensation saturation will determine what voltage represents 100% output.
        *
        * The compensated power is the power resulting from turning the voltage off and on without stopping.
        * In order to find the compensated power we have to divide the voltage by the voltage compensation
        * saturation.
        *
        * @param voltage the voltage of the loader
        * @param voltageCompensationSaturation the saturation of the compensation
        * @return the compensated power
        */
        public static double voltageToCompensatedPower(double voltage, double voltageCompensationSaturation) {
            return voltage/voltageCompensationSaturation;
    }
}
