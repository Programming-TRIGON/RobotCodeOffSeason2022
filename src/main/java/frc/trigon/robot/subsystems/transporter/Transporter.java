package frc.trigon.robot.subsystems.transporter;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.trigon.robot.subsystems.transporter.TransporterConstants.TransporterState;


public class Transporter extends SubsystemBase {
    private final static Transporter INSTANCE = new Transporter();
    private final WPI_TalonSRX motor;
    private TransporterState currentState;

    private Transporter() {
        motor = TransporterConstants.MOTOR;
    }
    public static Transporter getInstance() {
        return INSTANCE;
    }
    public TransporterState getState() {
        return currentState;
    }
    public void setState(TransporterState state) {
        motor.set(ControlMode.PercentOutput, state.voltage / TransporterConstants.VOLTAGE_COMPENSATION);
        currentState = state;
    }
}

