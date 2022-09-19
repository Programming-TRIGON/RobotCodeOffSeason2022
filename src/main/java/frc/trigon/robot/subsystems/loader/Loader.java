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
     * Gets the state of the Loader
     *
     * @return the current LoaderState
     */
    public LoaderState getState() {
        return currentState;
    }

    /**
     * Sets the state of the Loader
     *
     * @param state the wanted LoaderState
     */
    public void setState(LoaderState state) {
        motor.set(Conversions.voltageToCompensatedPower(state.voltage));
        currentState = state;
    }
}

