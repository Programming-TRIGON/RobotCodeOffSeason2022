package frc.trigon.robot.subsystems.transporter;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Transporter extends SubsystemBase {


    private TransporterConstants.TransporterState currentState;
    private final static Transporter INSTANCE = new Transporter();
    private final WPI_TalonSRX motor;
    public static Transporter getInstance() {
        return INSTANCE;
    }
    private Transporter() {
        motor = TransporterConstants.MOTOR;
    }
    public void setState (TransporterConstants.TransporterState state){
        motor.set(ControlMode.PercentOutput,state.voltage/TransporterConstants.VOLTAGE_COMPENSATION);
        currentState = state;
    }
    public TransporterConstants.TransporterState getState(){
        return currentState;
    }




}

