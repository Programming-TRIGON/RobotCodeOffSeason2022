package frc.trigon.robot.subsystems.Swerve;

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
    swerve.selfRelativeDrive(new Translation2d(xPower.getAsDouble(), yPower.getAsDouble()), zPower.getAsDouble());
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        swerve.stop();
    }
}
