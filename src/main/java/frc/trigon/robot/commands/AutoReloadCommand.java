package frc.trigon.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.button.Button;
import frc.trigon.robot.subsystems.ballscounter.BallsCounter;
import frc.trigon.robot.subsystems.loader.Loader;
import frc.trigon.robot.subsystems.transporter.Transporter;

import java.util.function.BooleanSupplier;

public class AutoReloadCommand extends ParallelCommandGroup {
    public AutoReloadCommand() {
        super();
        runCommandWhile(
                () -> BallsCounter.getInstance().getSecondBall().equals("") ||
                        !BallsCounter.getInstance().isTouchingBall(),
                Transporter.getInstance().getLoadCommand());
        runCommandWhile(
                () -> !BallsCounter.getInstance().isLoaderSwitchHeld(),
                Loader.getInstance().getLoadCommand()
        );
    }

    private void runCommandWhile(BooleanSupplier condition, Command command) {
        Button button = new Button(() -> condition.getAsBoolean() && isScheduled());
        button.whileHeld(command);
    }
}