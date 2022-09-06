package frc.trigon.robot.subsystems.climber;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.trigon.robot.subsystems.climber.ClimberConstants.ClimberPosition;

public class Climber extends SubsystemBase {
    private static final WPI_TalonFX masterMotor = ClimberConstants.MASTER_MOTOR;

    private final static Climber INSTANCE = new Climber();

    public static Climber getInstance() {
        return INSTANCE;
    }

    private Climber() {
    }

    /**
     * Sets the target position for the climber.
     * @param position the target position
     */
    public void setTargetPosition(ClimberPosition position) {
        masterMotor.set(ControlMode.Position, position.ticks, DemandType.AuxPID, 0);
    }

    /**
     * Returns whether the climber is in the target position within the allowable error.
     * @return true if the climber is in the target position
     */
    public boolean inTargetPosition() {
        if(!masterMotor.getControlMode().equals(ControlMode.Position))
            return false;
        return masterMotor.getClosedLoopError() <= ClimberConstants.ALLOWABLE_ERROR;
    }

    /**
     * @return the current position from -1 (lowest) to 1 (highest)
     */
    public double getCurrentPosition() {
        return masterMotor.getSelectedSensorPosition() / ClimberConstants.MAX_TICKS;
    }

    /**
     * Stops the climber motors.
     */
    public void stop() {
        masterMotor.set(ControlMode.Disabled, 0);
    }
}


