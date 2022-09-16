package frc.trigon.robot.subsystems.collector;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Collector extends SubsystemBase {
    private final static Collector INSTANCE = new Collector();
    private final WPI_TalonFX
            openingMotor = CollectorConstants.OPENER_MOTOR,
            collectingMotor = CollectorConstants.COLLECTOR_MOTOR;

    private Collector() {
    }

    public static Collector getInstance() {
        return INSTANCE;
    }

    private void collect() {
        openingMotor.set(CollectorConstants.OPENING_POWER);
        collectingMotor.set(ControlMode.PercentOutput, CollectorConstants.COLLECTING_POWER);
    }

    private void close() {
        openingMotor.set(CollectorConstants.CLOSING_POWER);
        collectingMotor.disable();
    }

    private void eject() {
        collectingMotor.set(ControlMode.PercentOutput, CollectorConstants.EJECTING_POWER);
    }

    /**
     * @return whether the collector is open.
     */
    public boolean isOpen() {
        return openingMotor.get() > 0;
    }

    private void stop() {
        collectingMotor.disable();
        openingMotor.disable();
    }
}

