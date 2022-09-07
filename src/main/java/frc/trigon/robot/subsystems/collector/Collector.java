package frc.trigon.robot.subsystems.collector;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.trigon.robot.Robot;

public class Collector extends SubsystemBase {

    private static final WPI_TalonSRX
            openerMotor = CollectorConstants.OPENER_MOTOR,
            collector = CollectorConstants.COLLECTOR_MOTOR;

    private final static Collector INSTANCE = new Collector();

    public static Collector getInstance() {
        return INSTANCE;
    }

    private Collector() {

    }

    protected void stop(){
        collector.set(ControlMode.Disabled, 0);
        openerMotor.set(ControlMode.Disabled, 0);
    }
    protected void collect() {
        openerMotor.set(CollectorConstants.MOTOR_OPENER_VOLTAGE);
        collector.set(ControlMode.PercentOutput, CollectorConstants.MOTOR_COLLECTOR_VOLTAGE);
    }
    protected void close(){

    }
    protected void eject(){

    }
}

