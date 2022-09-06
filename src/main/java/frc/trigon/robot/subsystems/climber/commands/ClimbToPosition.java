package frc.trigon.robot.subsystems.climber.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.trigon.robot.subsystems.climber.Climber;
import frc.trigon.robot.subsystems.climber.ClimberConstants;

/**
 * This is a command for climbing to a position on the gear
 */

public class ClimbToPosition extends CommandBase {
    private final Climber climber = Climber.getInstance();
    private final ClimberConstants.ClimberPosition position;

    /**
     * Constructs a new ClimbToPosition command.
     * @param position the position on the gear
     */

    public ClimbToPosition(ClimberConstants.ClimberPosition position) {
        this.position = position;

        addRequirements(climber);
    }

    @Override
    public void initialize() {
        climber.setTargetPosition(this.position);
    }

    @Override
    public boolean isFinished() {
        return climber.inTargetPosition();
    }

    @Override
    public void end(boolean interrupted) {
        climber.stop();
    }
}
