package frc.trigon.robot.subsystems.shooter;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.trigon.robot.utilities.Conversions;

import java.util.function.DoubleSupplier;

public class Shooter extends SubsystemBase {
    private final static Shooter INSTANCE = new Shooter();

    private WPI_TalonFX masterMotor = ShooterConstants.MASTER_MOTOR;
    private int ballCount = 0;

    private Shooter() {
    }

    public static Shooter getInstance() {
        return INSTANCE;
    }

    /**
     *
     * @return the targeted velocity if the motor control mode is velocity, else return 0
     */
    private double getTargetVelocity() {
        if(!masterMotor.getControlMode().equals(ControlMode.Velocity)) {
            return 0;
        }
        return masterMotor.getClosedLoopTarget();
    }

    /**
     *
     * @param velocity the velocity the motors need to get to
     */
    private void setTargetVelocity(double velocity) {
        masterMotor.set(ControlMode.Velocity, velocity, DemandType.ArbitraryFeedForward, ShooterConstants.S);
    }

    /**
     * stop the motors
     */
    private void stopMotor()
    {
        masterMotor.stopMotor();
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

    /**
     *
     * @return the current error from the target velocity
     */
    private double getCurrentError() {
        return getTargetVelocity() - getCurrentVelocity();
    }

    /**
     *
     * @param desiredVelocity the velocity the motors need to get to.
     * @return an execute command that calls the setTargetVelocity function to set the motors to the targeted velocity
     */
    public Command getPrimeShooterCommand(DoubleSupplier desiredVelocity) {
        return new RunCommand(() -> setTargetVelocity(desiredVelocity.getAsDouble()), this)
                .andThen(this::stopMotor);
    }

}

