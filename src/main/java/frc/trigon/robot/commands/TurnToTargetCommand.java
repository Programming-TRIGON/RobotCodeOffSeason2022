package frc.trigon.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.trigon.robot.components.Limelight;
import frc.trigon.robot.subsystems.swerve.SelfRelativeSupplierDrive;

import java.util.function.DoubleSupplier;

public class TurnToTargetCommand extends CommandBase {
    private static final double
            P = 1,
            I = 1,
            D = 1;
    private final PIDController pidController = new PIDController(P, I, D);
    private final double target;
    private final DoubleSupplier CurrentTx;

    public TurnToTargetCommand(double target, Limelight limelight) {
        this.target = target;
        CurrentTx = limelight::getTx;
    }

    @Override
    public void initialize() {
        pidController.setSetpoint(target);
    }

    @Override
    public void execute() {
        new SelfRelativeSupplierDrive(
                () -> 0,
                () -> 0,
                () -> pidController.calculate(CurrentTx.getAsDouble())
        );
    }

    @Override
    public boolean isFinished() {
        return pidController.atSetpoint();
    }
}
