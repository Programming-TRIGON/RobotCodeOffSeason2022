package frc.trigon.robot.subsystems.climber;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.trigon.robot.subsystems.climber.ClimberConstants.ClimberPosition;

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
