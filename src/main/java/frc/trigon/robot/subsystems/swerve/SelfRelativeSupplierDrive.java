package frc.trigon.robot.subsystems.swerve;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.CommandBase;

import java.util.function.DoubleSupplier;

public class SelfRelativeSupplierDrive extends CommandBase {
    private final DoubleSupplier xPower;
    private final DoubleSupplier yPower;
    private final DoubleSupplier zPower;
    private final Swerve swerve;

    public SelfRelativeSupplierDrive(
            Swerve swerve, DoubleSupplier xPower, DoubleSupplier yPower, DoubleSupplier zPower) {
        this.swerve = swerve;
        this.xPower = xPower;
        this.yPower = yPower;
        this.zPower = zPower;

        addRequirements(swerve);
    }

    @Override
    public void execute() {
        swerve.selfRelativeDrive(
                new Translation2d(
                        xPower.getAsDouble() * SwerveConstants.MAX_SPEED_METERS_PER_SECOND,
                        yPower.getAsDouble() * SwerveConstants.MAX_SPEED_METERS_PER_SECOND),
                new Rotation2d(zPower.getAsDouble() * SwerveConstants.MAX_SPEED_METERS_PER_SECOND));
    }

    @Override
    public void end(boolean interrupted) {
        swerve.stop();
    }
}
