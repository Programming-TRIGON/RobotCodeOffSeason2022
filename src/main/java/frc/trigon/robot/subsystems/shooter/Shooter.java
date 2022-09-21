package frc.trigon.robot.subsystems.shooter;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.trigon.robot.utilities.Conversions;

public class Shooter extends SubsystemBase {
    private final static Shooter INSTANCE = new Shooter();

    WPI_TalonFX masterMotor = ShooterConstants.MASTER_MOTOR;
    private int ballCount = 0;

    public void setTargetVelocity(double velocity) {
        masterMotor.set(ControlMode.Velocity, velocity);
    }

    public double getTargetVelocity() {
        if(masterMotor.getControlMode().equals(ControlMode.Velocity)) {
            return masterMotor.getClosedLoopTarget();
        }
        return 0;
    }

    /**
     * @return the current velocity as ticks per minute
     */
    public double getCurrentVelocity() {
        return Conversions.falconTicksPer100MsToRpm(masterMotor.getSelectedSensorVelocity());
    }

    /**
     * @return the count of balls currently inside the robot
     */
    public int getBallCount() {
        return ballCount;
    }

    /**
     * Resets the ball count
     */
    public void resetBallCount() {
        ballCount = 0;
    }

    public static Shooter getInstance() {
        return INSTANCE;
    }

    private Shooter() {
    }
}

