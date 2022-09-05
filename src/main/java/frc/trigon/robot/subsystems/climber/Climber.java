package frc.trigon.robot.subsystems.climber;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.trigon.robot.subsystems.climber.ClimberConstants.ClimberPosition;

public class Climber extends SubsystemBase {
    private final WPI_TalonFX master = ClimberConstants.MASTER;

    private final static Climber INSTANCE = new Climber();

    @SuppressWarnings("WeakerAccess")
    public static Climber getInstance() {
        return INSTANCE;
    }

    private Climber() {
    }

    public void setTargetPosition(ClimberPosition position) {
        master.set(ControlMode.Position, position.getTicks(), DemandType.AuxPID, 0);
    }

    public boolean inTargetPosition() {
        if(!master.getControlMode().equals(ControlMode.Position))
            return false;
        return master.getClosedLoopError() <= ClimberConstants.ALLOWABLE_ERROR;
    }

    public double getCurrentPosition() {
        return master.getSelectedSensorPosition() / ClimberConstants.MAX_TICKS;
    }

    public void stop() {
        master.set(ControlMode.Disabled, 0);
    }
}


