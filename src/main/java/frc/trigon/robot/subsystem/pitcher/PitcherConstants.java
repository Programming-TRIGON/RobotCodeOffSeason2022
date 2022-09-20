package frc.trigon.robot.subsystem.pitcher;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class PitcherConstants {
    static final int MIN_TICKS = 300, MUX_TICKS =4000;
    static final double GEAR_RATIO = 10;
    private static final int MOTOR_ID = 0;
    final static WPI_TalonSRX  MOTOR = new WPI_TalonSRX(MOTOR_ID);
    static final double P = 0.01, I = 0, D = 0;

    static {
        MOTOR.config_kP(0,P);
        MOTOR.config_kI(0,I);
        MOTOR.config_kD(0,D);
    }
}
