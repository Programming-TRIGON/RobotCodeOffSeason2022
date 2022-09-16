package frc.trigon.robot.subsystems.transporter;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
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
     *  get will return currentState
     *  and currentState = to state
     * @return
     */
    public TransporterState getState() {
        return currentState;
    }

    /**
     * motor.set = to state.power
     * @param state
     */
    public void setState(TransporterState state) {
        motor.set(state.power);
        currentState = state;
    }
}

