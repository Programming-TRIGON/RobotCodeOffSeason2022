package frc.trigon.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.button.Button;
import frc.trigon.robot.subsystems.ballscounter.BallsCounter;
import frc.trigon.robot.subsystems.loader.Loader;
import frc.trigon.robot.subsystems.transporter.Transporter;

import java.util.function.BooleanSupplier;

public class DefaultLoaderCommand extends ParallelCommandGroup {
    public DefaultLoaderCommand() {
        super();
        Commands.runCommandWhile(
                this,
                () -> !BallsCounter.getInstance().isLoaderSwitchHeld(),
                Loader.getInstance().getDefaultCommand()
        );
    }
}