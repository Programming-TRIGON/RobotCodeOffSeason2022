package frc.trigon.robot.subsystems.swerve;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.CommandBase;

import java.util.function.DoubleSupplier;

public class SelfRelativeSupplierDrive extends CommandBase {
    private final DoubleSupplier xPower;
    private final DoubleSupplier yPower;
    private final DoubleSupplier rotPower;

    /**
     * drives the swerve relative to the field.
     *
     * @param xPower   the forwards velocity in meters per second.
     * @param yPower   the leftwards velocity in meters per second.
     * @param rotPower the rotational velocity in radians per second.
     */
    public SelfRelativeSupplierDrive(
            DoubleSupplier xPower, DoubleSupplier yPower, DoubleSupplier rotPower) {
        this.xPower = xPower;
        this.yPower = yPower;
        this.rotPower = rotPower;

        addRequirements(Swerve.getInstance());
    }

    @Override
    public void execute() {
        Swerve.getInstance().selfRelativeDrive(
                new Translation2d(
                        xPower.getAsDouble() * SwerveConstants.MAX_SPEED_METERS_PER_SECOND,
                        yPower.getAsDouble() * SwerveConstants.MAX_SPEED_METERS_PER_SECOND),
                new Rotation2d(rotPower.getAsDouble() * SwerveConstants.MAX_SPEED_METERS_PER_SECOND));
    }

    @Override
    public void end(boolean interrupted) {
        Swerve.getInstance().stop();
    }
}
