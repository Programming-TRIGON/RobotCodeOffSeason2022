package frc.trigon.robot.subsystems.swerve;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.trigon.robot.components.Limelight;

import java.util.function.DoubleSupplier;

public class TurnToTargetCommand extends CommandBase {
    private static final double
            P = 1,
            I = 1,
            D = 1,
            TOLERANCE = 10;

    private final Swerve swerve;
    private final Limelight limelight;
    private final PIDController pidController;
    private final double target;
    private final DoubleSupplier CurrentTx;

    public TurnToTargetCommand(Limelight limelight, double target) {
        pidController = new PIDController(P, I, D);
        CurrentTx = limelight::getTx;
        this.target = target;
        swerve = Swerve.getInstance();
        this.limelight = limelight;
        addRequirements(swerve);
    }

    @Override
    public void initialize() {
        pidController.setTolerance(TOLERANCE);
        pidController.setSetpoint(target);
    }

    @Override
    public void execute() {
        if(limelight.hasTarget()) {
            swerve.selfRelativeDrive(
                    new Translation2d(), new Rotation2d(pidController.calculate(CurrentTx.getAsDouble())));
        } else {
            swerve.selfRelativeDrive(
                    new Translation2d(), new Rotation2d(5));
        }
    }

    @Override
    public boolean isFinished() {
        return pidController.atSetpoint();
    }

    @Override
    public void end(boolean interrupted) {
        swerve.stop();
    }
}
