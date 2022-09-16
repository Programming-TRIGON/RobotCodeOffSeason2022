package frc.trigon.robot.subsystems.transporter;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.trigon.robot.utilities.Conversions;

import static frc.trigon.robot.subsystems.transporter.TransporterConstants.*;

public class Transporter extends SubsystemBase {
    private final static Transporter INSTANCE = new Transporter();
    private final WPI_TalonSRX motor = TransporterConstants.MOTOR;
    private TransporterState currentState;

    private Transporter() {

    }

    public static Transporter getInstance() {
        return INSTANCE;
    }

    public TransporterState getState() {
        return currentState;
    }

    public void setState(TransporterState state) {
        double compensatedPower = Conversions.voltageToCompensatedPower(state.voltage, VOLTAGE_COMPENSATION_SATURATION);
        motor.set(ControlMode.PercentOutput, compensatedPower);
        currentState = state;
    }
}

