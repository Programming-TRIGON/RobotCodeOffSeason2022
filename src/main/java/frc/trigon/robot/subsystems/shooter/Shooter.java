package frc.trigon.robot.subsystems.shooter;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.trigon.robot.utilities.Conversions;

public class Shooter extends SubsystemBase {
    private final static Shooter INSTANCE = new Shooter();

    private WPI_TalonFX masterMotor = ShooterConstants.MASTER_MOTOR;
    private int ballCount = 0;

    private Shooter() {
    }

    public static Shooter getInstance() {
        return INSTANCE;
    }

    private double getTargetVelocity() {
        if(!masterMotor.getControlMode().equals(ControlMode.Velocity)) {
            return 0;
        }
        return masterMotor.getClosedLoopTarget();
    }

    private void setTargetVelocity(double velocity) {
        masterMotor.set(ControlMode.Velocity, velocity);
    }

    /**
     * @return the current velocity as RPM
     */
    private double getCurrentVelocity() {
        return Conversions.falconTicksPer100MsToRpm(masterMotor.getSelectedSensorVelocity());
    }

    /**
     * @return the count of balls currently inside the robot
     */
    private int getBallCount() {
        return ballCount;
    }

    /**
     * Resets the ball count
     */
    private void resetBallCount() {
        ballCount = 0;
    }

    private boolean atTarget() {
        return getTargetVelocity() == getCurrentVelocity();
    }
}

