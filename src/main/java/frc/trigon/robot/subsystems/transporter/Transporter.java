package frc.trigon.robot.subsystems.transporter;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.trigon.robot.subsystems.transporter.TransporterConstants.TransporterState;

public class Transporter extends SubsystemBase {
    private final static Transporter INSTANCE = new Transporter();
    private final WPI_TalonSRX motor = TransporterConstants.MOTOR;
    private TransporterState currentState;

    private Transporter() {
    }

    public static Transporter getInstance() {
        return INSTANCE;
    }

    /**
     * @return the current state of the transporter
     */
    private TransporterState getState() {
        return currentState;
    }

    /**
     * Sets the state of the transporter.
     *
     * @param state the wanted state for the transporter
     */
    private void setState(TransporterState state) {
        motor.set(state.power);
        currentState = state;
    }

    public Command getLoadCommand() {
        return new StartEndCommand(
                () -> setState(TransporterState.LOAD),
                () -> setState(TransporterState.OFF),
                this
        );
    }

    public Command getEjectCommand() {
        return new StartEndCommand(
                () -> setState(TransporterState.EJECT),
                () -> setState(TransporterState.OFF),
                this
        );
    }
}

