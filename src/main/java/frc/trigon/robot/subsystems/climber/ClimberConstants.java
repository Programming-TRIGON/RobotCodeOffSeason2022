package frc.trigon.robot.subsystems.climber;

import com.ctre.phoenix.motorcontrol.FollowerType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

public class ClimberConstants {
    protected static final int MAX_TICKS = 10000;
    protected static final double ALLOWABLE_ERROR = 100;
    private static final int
            LEFT_MOTOR_ID = 0,
            RIGHT_MOTOR_ID = 1;
    private static final boolean
            LEFT_MOTOR_INVERTED = false,
            RIGHT_MOTOR_INVERTED = false;
    protected static final WPI_TalonFX
            LEFT_MOTOR = new WPI_TalonFX(LEFT_MOTOR_ID),
            RIGHT_MOTOR = new WPI_TalonFX(RIGHT_MOTOR_ID),
            MASTER = RIGHT_MOTOR;


    private static final double
                            P = 1,
                            I = 1,
                            D = 1,
                            AUX_P = 1,
                            AUX_I = 1,
                            AUX_D = 1;

    static {
        LEFT_MOTOR.setInverted(LEFT_MOTOR_INVERTED);
        RIGHT_MOTOR.setInverted(RIGHT_MOTOR_INVERTED);
        LEFT_MOTOR.follow(MASTER, FollowerType.AuxOutput1);
        RIGHT_MOTOR.follow(MASTER, FollowerType.AuxOutput1);
        MASTER.config_kP(0, P);
        MASTER.config_kI(0, I);
        MASTER.config_kD(0, D);
        MASTER.config_kP(1, AUX_P);
        MASTER.config_kI(1, AUX_I);
        MASTER.config_kD(1, AUX_D);
    }

    public enum ClimberPosition {
        HIGH(MAX_TICKS),
        LOW(-MAX_TICKS),
        MIDDLE(0);

        final int ticks;

        ClimberPosition(int ticks) {
            this.ticks = ticks;
        }

        public int getTicks() {
            return ticks;
        }
    }
}
