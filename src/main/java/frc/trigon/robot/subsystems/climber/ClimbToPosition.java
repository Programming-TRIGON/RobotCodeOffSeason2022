package frc.trigon.robot.subsystems.climber;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class ClimbToPosition extends CommandBase {
    private final Climber climber = Climber.getInstance();
    private final ClimberConstants.ClimberPosition position;

    public ClimbToPosition(ClimberConstants.ClimberPosition position) {
        this.position = position;
        // each subsystem used by the command must be passed into the
        // addRequirements() method (which takes a vararg of Subsystem)
        addRequirements(climber);
    }

    @Override
    public void initialize() {
        climber.setTargetPosition(this.position);
    }

    @Override
    public void execute() {

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
