package frc.trigon.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.trigon.robot.components.HubLimelight;
import frc.trigon.robot.subsystems.pitcher.Pitcher;
import frc.trigon.robot.subsystems.shooter.Shooter;
import frc.trigon.robot.utilities.ShootingCalculations;

/**
 * A command that sets the shooter's speed and angle to the calculated values from ShootingCalculations.
 */
public class SetupShootingByLimelight extends ParallelCommandGroup {
    private static final double
            DEFAULT_VELOCITY = 2500,
            DEFAULT_ANGLE = 5;
    HubLimelight limelight;

    public SetupShootingByLimelight(HubLimelight limelight) {
        super();
        this.limelight = limelight;
        addCommands(
                Shooter.getInstance().getPrimeShooterCommand(this::getShootingVelocityFromDistance),
                Pitcher.getInstance().getPitchingCommand(this::getShootingAngleFromDistance)
        );
    }

    private double getShootingVelocityFromDistance() {
        return limelight.hasTarget() ?
               ShootingCalculations.getShootingVelocityFromDistance(limelight.getDistanceFromHub()) :
               DEFAULT_VELOCITY;
    }

    private double getShootingAngleFromDistance() {
        return limelight.hasTarget() ?
               ShootingCalculations.getShootingAngleFromDistance(limelight.getDistanceFromHub()) :
               DEFAULT_ANGLE;
    }
}
