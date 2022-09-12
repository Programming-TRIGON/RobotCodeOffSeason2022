package frc.trigon.robot.subsystems.loader;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.trigon.robot.subsystems.loader.LoaderConstants.LoaderState;

public class Loader extends SubsystemBase {
    private WPI_TalonSRX motor = LoaderConstants.MOTOR;

    private LoaderState currentState;

    private final static Loader INSTANCE = new Loader();

    public static Loader getInstance() {
        return INSTANCE;
    }

    private Loader() {

    }

    public void setState(LoaderState state) {
        motor.set(state.power / LoaderConstants.VOLTAGE_COMPENSATION);
        currentState = state;
    }

    public LoaderState getState() {
        return currentState;
    }
}

