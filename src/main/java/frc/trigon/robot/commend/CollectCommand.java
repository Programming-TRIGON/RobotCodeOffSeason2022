package frc.trigon.robot.commend;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.trigon.robot.subsystems.collector.Collector;
import frc.trigon.robot.subsystems.transporter.Transporter;

/**
 * Collects cargo using the transporter and collector.
 */
public class CollectCommand extends SequentialCommandGroup {
    public CollectCommand() {
        super(Transporter.getInstance().getLoadCommand(), Collector.getInstance().getCollectCommand());
    }
}