package frc.trigon.robot.commend;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.trigon.robot.subsystems.collector.Collector;
import frc.trigon.robot.subsystems.transporter.Transporter;

public class EjectCommand extends ParallelCommandGroup {
    public EjectCommand() {
        super(Transporter.getInstance().getEjectCommand(), Collector.getInstance().getEjectCommand());
    }
}