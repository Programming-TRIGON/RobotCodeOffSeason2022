package frc.trigon.robot.subsystem.pitcher;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.Button;
import frc.trigon.robot.utilities.Conversions;

public class Pitcher extends SubsystemBase {
    private final WPI_TalonSRX motor = PitcherConstants.MOTOR;
    private final Button forwardSwitch, reverseSwitch;
    private final static Pitcher INSTANCE = new Pitcher();

    public static Pitcher getInstance() {
        return INSTANCE;
    }

    private Pitcher() {
        forwardSwitch = new Button(() -> (getCorrectedTicks() >= PitcherConstants.MAX_TICKS - PitcherConstants.MIN_TICKS));
        reverseSwitch = new Button(()-> (getCorrectedTicks() <= 0));
    }
   private void configLimits(){
       Command forwardLimitCommand = new StartEndCommand(
               ()-> motor.configPeakOutputForward(0),
               ()->motor.configPeakOutputForward(1)
       );
       forwardSwitch.whileHeld(forwardLimitCommand);

       Command reverseLimitCommand = new StartEndCommand(
               ()-> motor.configPeakOutputReverse(0),
                ()-> motor.configPeakOutputReverse(-1)
       );
       reverseSwitch.whileHeld(reverseLimitCommand);
   }
    public void setPosition() {

    }
        private double getCorrectedTicks() {
            if(motor.getSelectedSensorPosition() < PitcherConstants.MIN_TICKS) {
                return motor.getSelectedSensorPosition() + Conversions.MAG_TICKS - PitcherConstants.MIN_TICKS;
            }
            return motor.getSelectedSensorPosition() - PitcherConstants.MIN_TICKS;
        }
    public double ticksToDegrees(double ticks) {
        double rotations = ticks / 4096;
        double degrees = rotations * 360;
        return degrees / PitcherConstants.GEAR_RATIO;
    }

    public double degreesToTicks(double degrees) {
        double rotations = degrees / 360;
        double ticks = rotations * 4096;
        return ticks / PitcherConstants.GEAR_RATIO;
    }
    @Override
    public void periodic() {
        SmartDashboard.putNumber("Pitcher/angle", ticksToDegrees(getCorrectedTicks()));
        SmartDashboard.putBoolean("Pitcher/forwardSwitch",forwardSwitch.getAsBoolean());
        SmartDashboard.putBoolean("Pitcher/reverseSwitch",reverseSwitch.getAsBoolean());
    }
}

