package frc.trigon.robot.subsystems.shooter;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.trigon.robot.utilities.Conversions;

public class Shooter extends SubsystemBase {

    // With eager singleton initialization, any static variables/fields used in the 
    // constructor must appear before the "INSTANCE" variable so that they are initialized 
    // before the constructor is called when the "INSTANCE" variable initializes.
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
     * @return current velocity as ticks per minute
     */
    public double getCurrentVelocity() {
        return Conversions.ticksPerHundredMsToRPM(masterMotor.getSelectedSensorVelocity());
    }

    /**
     * @return count of balls currently inside the robot
     */
    public int getBallCount() {
        return ballCount;
    }

    /**
     * resetting the ball count
     */
    public void resetBallCount() {
        ballCount = 0;
    }

    private final static Shooter INSTANCE = new Shooter();

    @SuppressWarnings("WeakerAccess")
    public static Shooter getInstance() {
        return INSTANCE;
    }

    private Shooter() {
        // TODO: Set the default command, if any, for this subsystem by calling setDefaultCommand(command)
        //       in the constructor or in the robot coordination class, such as RobotContainer.
        //       Also, you can call addChild(name, sendableChild) to associate sendables with the subsystem
        //       such as SpeedControllers, Encoders, DigitalInputs, etc.
    }
}

