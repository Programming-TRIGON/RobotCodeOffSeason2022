package frc.trigon.robot.subsystems.climber.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.trigon.robot.subsystems.climber.ClimberConstants.ClimberPosition;

/**
 * This command does the full climb.
 */
public class FullClimb extends SequentialCommandGroup {

    public FullClimb() {
        super(
                new ClimbToPosition(ClimberPosition.HIGH),
                new ClimbToPosition(ClimberPosition.LOW),
                new ClimbToPosition(ClimberPosition.HIGH),
                new ClimbToPosition(ClimberPosition.LOW)
        );
    }
}
