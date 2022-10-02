package frc.trigon.robot.subsystem.pitcher;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.trigon.robot.utilities.Conversions;

import java.util.function.DoubleSupplier;

public class Pitcher extends SubsystemBase {
    private final static Pitcher INSTANCE = new Pitcher();
    private final WPI_TalonSRX motor = PitcherConstants.MOTOR;

    public static Pitcher getInstance() {
        return INSTANCE;
    }

    public void setTargetAngle(double degrees) {
        double ticks = Conversions.degreesToMagTicks(degrees);
        motor.set(Conversions.offsetWrite(ticks, PitcherConstants.MIN_TICKS));
    }

    public double getError() {
        double error = motor.getClosedLoopError();
        return Conversions.magTicksToDegrees(error);
    }

    public double getAngle() {
        double offsettedRead = Conversions.offsetRead(motor.getSelectedSensorPosition(), PitcherConstants.MIN_TICKS);
        double motorAngle = Conversions.magTicksToDegrees(offsettedRead);
        return Conversions.motorPositionToSystemPosition(motorAngle, PitcherConstants.GEAR_RATIO);
    }

    public void stop() {
        motor.disable();
    }

    /**
     * @param targetAngle the command will continuously set the target angle to this
     * @return a command that sets the angle of the pitcher according to the given supplier.
     */
    public Command getPitchingCommand(DoubleSupplier targetAngle) {
        return new RunCommand(() -> setTargetAngle(targetAngle.getAsDouble()), this)
                .andThen(this::stop, this);
    }
}

