package frc.trigon.robot.subsystems.loader;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Loader extends SubsystemBase {
    private WPI_TalonSRX motor;

    private LoaderConstants.LoaderState currentState;

    private final static Loader INSTANCE = new Loader();

    public static Loader getInstance() {
        return INSTANCE;
    }

    private Loader() {
        motor = LoaderConstants.MOTOR;
    }

    public void setState(LoaderConstants.LoaderState state) {
        motor.set(ControlMode.PercentOutput, state.voltage / LoaderConstants.VOLTAGE_COMPENSATION);
    }

    public LoaderConstants.LoaderState getState() {
        return currentState;
    }
}

