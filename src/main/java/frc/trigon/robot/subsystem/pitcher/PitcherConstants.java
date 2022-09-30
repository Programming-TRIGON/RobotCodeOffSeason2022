package frc.trigon.robot.subsystem.pitcher;

import com.ctre.phoenix.motorcontrol.TalonSRXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class PitcherConstants {
    static final int
            MIN_TICKS = -3284,
            MAX_TICKS = -1563;
    static final double GEAR_RATIO = 10;
    private static final int MOTOR_ID = 3;
    final static WPI_TalonSRX MOTOR = new WPI_TalonSRX(MOTOR_ID);
    private static final double
            P = 3,
            I = 0,
            D = 0,
            PEAK_OUTPUT = 0.2,
            allowableError = 3;
    private static final boolean
            INVERTED = true,
            SENSOR_PHASE = false;

    static {
        MOTOR.configFactoryDefault();
        MOTOR.configSelectedFeedbackSensor(TalonSRXFeedbackDevice.CTRE_MagEncoder_Absolute.toFeedbackDevice());
        MOTOR.setInverted(INVERTED);
        MOTOR.setSensorPhase(SENSOR_PHASE);

        MOTOR.configForwardSoftLimitEnable(true);
        MOTOR.configForwardSoftLimitThreshold(MAX_TICKS);
        MOTOR.configReverseSoftLimitEnable(true);
        MOTOR.configReverseSoftLimitThreshold(MIN_TICKS);

        MOTOR.config_kP(0, P);
        MOTOR.config_kI(0, I);
        MOTOR.config_kD(0, D);
        MOTOR.configClosedLoopPeakOutput(0, PEAK_OUTPUT);
        MOTOR.configAllowableClosedloopError(0, allowableError);
    }
}
