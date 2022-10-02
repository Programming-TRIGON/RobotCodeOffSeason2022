package frc.trigon.robot.subsystems.shooter;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.trigon.robot.utilities.Conversions;

import java.util.function.DoubleSupplier;

public class Shooter extends SubsystemBase {
    private final static Shooter INSTANCE = new Shooter();

    private final WPI_TalonFX masterMotor = ShooterConstants.MASTER_MOTOR;
    private int ballCount = 0;

    private Shooter() {
    }

    public static Shooter getInstance() {
        return INSTANCE;
    }

    /**
     * @return the target velocity as RPM
     */
    private double getTargetVelocity() {
        if(!masterMotor.getControlMode().equals(ControlMode.Velocity)) {
            return 0;
        }
        return Conversions.falconTicksPer100MsToRpm(masterMotor.getClosedLoopTarget());
    }

    /**
     * @param velocity target motor velocity
     */
    private void setTargetVelocity(double velocity) {
        masterMotor.set(ControlMode.Velocity, velocity, DemandType.ArbitraryFeedForward, ShooterConstants.S);
    }

    /**
     * Stops the motors
     */
    private void stop() {
        masterMotor.stopMotor();
    }

    /**
     * @return the current velocity in RPM
     */
    private double getCurrentVelocity() {
        return Conversions.falconTicksPer100MsToRpm(masterMotor.getSelectedSensorVelocity());
    }

    /**
     * @return the number of balls that have been shot
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
     * @return the current closed loop error value as RPM
     */
    private double getError() {
        return Conversions.falconTicksPer100MsToRpm(masterMotor.getClosedLoopError());
    }

    /**
     * @param targetVelocity the target velocity.
     * @return an execute command that get the shooter to the targetVelocity
     */
    public Command getPrimeShooterCommand(DoubleSupplier targetVelocity) {
        return new RunCommand(() -> setTargetVelocity(targetVelocity.getAsDouble()), this)
                .andThen(this::stop);
    }
}

