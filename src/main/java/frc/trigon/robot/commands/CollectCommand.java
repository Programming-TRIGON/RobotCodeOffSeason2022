package frc.trigon.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.trigon.robot.subsystems.collector.Collector;
import frc.trigon.robot.subsystems.transporter.Transporter;

/**
 * Collects cargo using the transporter and collector.
 */
public class CollectCommand extends ParallelCommandGroup {
    public CollectCommand() {
        super(Transporter.getInstance().getLoadCommand(), Collector.getInstance().getCollectCommand());
    }
}