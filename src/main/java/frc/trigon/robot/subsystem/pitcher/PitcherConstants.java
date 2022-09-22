package frc.trigon.robot.subsystem.pitcher;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.sensors.CANCoder;

import java.util.Set;

public class PitcherConstants {
    static final int FORWARD_SWITCH_ID = 0, REVERSE_SWITCH_ID = 0;
    static final int MIN_TICKS = 0, MAX_TICKS = 4000,TICKS_OFFSET = 0;
    static final double GEAR_RATIO = 10;
    private static final int MOTOR_ID = 0;
    final static WPI_TalonSRX  MOTOR = new WPI_TalonSRX(MOTOR_ID);
   private static final double P = 0.01, I = 0, D = 0;
    private static final boolean INVERTED = false;
    static {
        MOTOR.config_kP(0,P);
        MOTOR.config_kI(0,I);
        MOTOR.config_kD(0,D);

        MOTOR.setInverted(INVERTED);
        MOTOR.setSelectedSensorPosition(MOTOR.getSelectedSensorPosition() - TICKS_OFFSET );



    }
}
