package frc.trigon.robot.subsystems.collector;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Collector extends SubsystemBase {

    private static final WPI_TalonFX
            openerMotor = CollectorConstants.OPENER_MOTOR,
            spinnerMotor = CollectorConstants.SPINNER_MOTOR;

    private final static Collector INSTANCE = new Collector();

    public static Collector getInstance() {
        return INSTANCE;
    }

    private Collector() {

    }

    protected void stop(){
        spinnerMotor.set(ControlMode.Disabled, 0);
        openerMotor.set(ControlMode.Disabled, 0);
    }
    protected void collect() {
        spinnerMotor.set(ControlMode.PercentOutput, CollectorConstants.MOTOR_SPINNER_VOLTAGE);
    }
    protected void close(){

    }
    protected void eject(){

    }
}

