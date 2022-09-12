package frc.trigon.robot.subsystems.climber;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.FollowerType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.trigon.robot.subsystems.climber.ClimberConstants.ClimberPosition;

public class Climber extends SubsystemBase {
    private static final WPI_TalonFX
            masterMotor = ClimberConstants.MASTER_MOTOR,
            followerMotor = ClimberConstants.FOLLOWER_MOTOR;

    private final static Climber INSTANCE = new Climber();

    private Climber() {

    }

    static Climber getInstance() {
        return INSTANCE;
    }

    /**
     * Sets the target position for the climber.
     *
     * @param position the target position
     */
    void setTargetPosition(ClimberPosition position) {
        followerMotor.follow(masterMotor, FollowerType.AuxOutput1);
        masterMotor.set(ControlMode.Position, position.ticks, DemandType.AuxPID, 0);
    }

    /**
     * Returns whether the climber is in the target position within the allowable error.
     *
     * @return true if the climber is in the target position
     */
    boolean inTargetPosition() {
        if(!masterMotor.getControlMode().equals(ControlMode.Position))
            return false;
        return masterMotor.getClosedLoopError() <= ClimberConstants.ALLOWABLE_ERROR;
    }

    /**
     * @return the current position from -1 (lowest) to 1 (highest)
     */
    double getCurrentPosition() {
        return masterMotor.getSelectedSensorPosition() / ClimberConstants.MAX_TICKS.getAsDouble();
    }

    /**
     * Stops the climber motors.
     */
    void stop() {
        masterMotor.set(ControlMode.Disabled, 0);
    }

    /**
     * Sets the power for the climber motors with voltage.
     *
     * @param power the amount of power (voltage)
     */
    void setPower(double power) {
        followerMotor.follow(masterMotor, FollowerType.PercentOutput);
        masterMotor.set(ControlMode.PercentOutput, power);
    }

    /**
     * get the current position of the climber.
     *
     * @return the current position of the climber in ticks
     */
    double getSelectedSensorPosition() {
        return masterMotor.getSelectedSensorPosition();
    }

    /**
     * set the current position of the climber.
     *
     * @param position the current position of the climber in ticks
     */
    void setSelectedSensorPosition(double position) {
        masterMotor.setSelectedSensorPosition(position);
    }

    /**
     * @return true if the climber is at the top
     */
    boolean atTop() {
        return masterMotor.isFwdLimitSwitchClosed() + followerMotor.isFwdLimitSwitchClosed() == 2;
    }

    /**
     * @return true if the climber is at the bottom
     */
    boolean atBottom() {
        return masterMotor.isRevLimitSwitchClosed() + followerMotor.isRevLimitSwitchClosed() == 2;
    }
}


