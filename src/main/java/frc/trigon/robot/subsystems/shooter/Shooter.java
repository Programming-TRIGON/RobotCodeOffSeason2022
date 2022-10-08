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

    private Shooter() {
    }

    public static Shooter getInstance() {
        return INSTANCE;
    }

    /**
     * @return the target velocity in RPM
     */
    private double getTargetVelocity() {
        if(!masterMotor.getControlMode().equals(ControlMode.Velocity)) {
            return 0;
        }
        return Conversions.falconTicksPer100MsToRpm(masterMotor.getClosedLoopTarget());
    }

    /**
     * @param velocity target shooter velocity
     */
    private void setTargetVelocity(double velocity) {
        masterMotor.set(
                ControlMode.Velocity,
                Conversions.RpmToFalconTicksPer100Ms(velocity),
                DemandType.ArbitraryFeedForward,
                ShooterConstants.S
        );
    }

    /**
     * Stops the shooter
     */
    private void stop() {
        masterMotor.stopMotor();
    }

    /**
     * @return the current velocity of the shooter in RPM
     */
    private double getCurrentVelocity() {
        return Conversions.falconTicksPer100MsToRpm(masterMotor.getSelectedSensorVelocity());
    }

    /**
     * @return the current closed loop error value in RPM
     */
    double getError() {
        return Conversions.falconTicksPer100MsToRpm(masterMotor.getClosedLoopError());
    }

    boolean atTargetVelocity()
    {
        return Math.abs(Shooter.getInstance().getError()) <= ShooterConstants.ERROR_RANGE_STABLE_ALLOWANCE;
    }

    /**
     * @param targetVelocity the target velocity of the shooter
     * @return a command that sets the velocity of the shooter according to the given supplier
     */
    public Command getPrimeShooterCommand(DoubleSupplier targetVelocity) {
        return new RunCommand(() -> setTargetVelocity(targetVelocity.getAsDouble()), this)
                .andThen(this::stop);
    }
}

