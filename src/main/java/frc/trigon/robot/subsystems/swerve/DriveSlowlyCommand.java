package frc.trigon.robot.subsystems.swerve;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

public class DriveSlowlyCommand extends ParallelCommandGroup {
    private final Swerve swerve = Swerve.getInstance();

    public DriveSlowlyCommand() {
        super(swerve.fieldRelativeDrive());
        addRequirements(this.swerve);
    }
}
