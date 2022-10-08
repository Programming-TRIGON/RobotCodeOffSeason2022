package frc.trigon.robot.subsystems.shooter;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.*;
import frc.trigon.robot.commands.runswhendisabled.RunsWhenDisabledInstantCommand;
import frc.trigon.robot.utilities.Conversions;

import java.util.function.DoubleSupplier;

public class Shooter extends SubsystemBase {
    private final static Shooter INSTANCE = new Shooter();

    private final WPI_TalonFX masterMotor = ShooterConstants.MASTER_MOTOR;
    private boolean shotBall = false;

    private Shooter() {
        SmartDashboard.putData("shoot", new RunsWhenDisabledInstantCommand(() -> shotBall = true));
        new ShotsCounter().schedule();
    }

    static class RWDC extends InstantCommand {
        @Override
        public boolean runsWhenDisabled() {
            return true;
        }

        public RWDC(Runnable toRun) {
            super(toRun);
        }
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
     * @return the number of balls that have been shot
     */
    public boolean hasShotBall() {
        return shotBall;
    }

    /**
     * Resets the ball count
     */
    public void resetBallFlag() {
        shotBall = false;
    }

    /**
     * @return the current closed loop error value in RPM
     */
    private double getError() {
        return Conversions.falconTicksPer100MsToRpm(masterMotor.getClosedLoopError());
    }

    /**
     * @param targetVelocity the target velocity of the shooter
     * @return a command that sets the velocity of the shooter according to the given supplier
     */
    public Command getPrimeShooterCommand(DoubleSupplier targetVelocity) {
        return new RunCommand(() -> setTargetVelocity(targetVelocity.getAsDouble()), this)
                .andThen(this::stop);
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        super.initSendable(builder);
        builder.addDoubleProperty("Target Velocity", this::getTargetVelocity, this::setTargetVelocity);
        builder.addDoubleProperty("Current Velocity", this::getCurrentVelocity, null);
        builder.addDoubleProperty("Error", this::getError, null);
        builder.addBooleanProperty("Has Shot Ball", this::hasShotBall, null);
    }

    static class ShotsCounter extends CommandBase {
        private static final int
                IN_DIP_THRESHOLD = 300,
                OUT_DIP_THRESHOLD = 80;
        boolean alreadyInDip;

        public ShotsCounter() {

        }

        boolean inDip() {
            return Shooter.getInstance().getError() > IN_DIP_THRESHOLD;
        }

        boolean outDip() {
            return Shooter.getInstance().getError() < OUT_DIP_THRESHOLD;
        }

        @Override
        public void initialize() {
            alreadyInDip = false;
        }

        @Override
        public void execute() {
            if(!alreadyInDip) {
                if(inDip()) {
                    alreadyInDip = true;
                    Shooter.getInstance().shotBall = true;
                }
            } else {
                if(outDip()) {
                    alreadyInDip = false;
                }
            }
        }

        @Override
        public boolean runsWhenDisabled() {
            return true;
        }
    }
}

