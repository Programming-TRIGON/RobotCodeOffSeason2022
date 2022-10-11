package frc.trigon.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.button.Button;
import frc.trigon.robot.subsystems.loader.Loader;
import frc.trigon.robot.subsystems.shooter.Shooter;
import frc.trigon.robot.subsystems.transporter.Transporter;

public class EjectCommand extends ParallelCommandGroup {
    Button canEjectButton;
    final double TOLERANCE = 200;

    public EjectCommand() {
        super();
        canEjectButton = new Button(() -> canEject() && isScheduled());
        addCommands(Commands.getShooterEjectCommand());
        canEjectButton.whileHeld(
                Loader.getInstance().getLoadCommand().alongWith(Transporter.getInstance().getLoadCommand()));
        canEjectButton.whenReleased(
                Loader.getInstance().getLoadCommand().withInterrupt(() -> !isScheduled()).withTimeout(0.5));
    }

    boolean canEject() {
        return Shooter.getInstance().getError() < TOLERANCE;
    }
}
