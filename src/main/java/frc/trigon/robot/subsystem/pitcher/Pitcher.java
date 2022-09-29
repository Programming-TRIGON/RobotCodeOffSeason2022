package frc.trigon.robot.subsystem.pitcher;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Pitcher extends SubsystemBase {
    private final static Pitcher INSTANCE = new Pitcher();
    private final WPI_TalonSRX motor = PitcherConstants.MOTOR;

    public static Pitcher getInstance() {
        return INSTANCE;
    }

    public void setPosition() {

    }

    public double getDegrees() {
        return 0;
    }
}

