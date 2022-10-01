package frc.trigon.robot.subsystems.loader;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.trigon.robot.utilities.Conversions;

import static frc.trigon.robot.subsystems.loader.LoaderConstants.LoaderState;

public class Loader extends SubsystemBase {
    private final static Loader INSTANCE = new Loader();
    private final WPI_TalonSRX motor = LoaderConstants.MOTOR;
    private LoaderState currentState;

    private Loader() {
    }

    public static Loader getInstance() {
        return INSTANCE;
    }

    /**
     * @return the current state of the loader
     */
    public LoaderState getState() {
        return currentState;
    }

    /**
     * Sets the state of the loader.
     *
     * @param state the wanted state for the loader
     */

    public void setState(LoaderState state) {
        motor.set(Conversions.voltageToCompensatedPower(state.voltage, LoaderConstants.VOLTAGE_COMPENSATION_SATURATION));
        currentState = state;
    }
}

