package frc.trigon.robot.subsystems.climber;

import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * This is a command for calibrating the climber encoder.
 */
public class ClimberEncoderCalibration extends CommandBase {
    private final Climber climber = Climber.getInstance();

    /**
     * Constructs a new ClimberEncoderCalibration command.
     */
    public ClimberEncoderCalibration() {
        addRequirements(climber);
    }

    @Override
    public void initialize() {
        climber.setPower(-ClimberConstants.CLIMBER_CALIBRATION_POWER);
    }

    @Override
    public void execute() {
        if(climber.atBottom()) {
            climber.setSelectedSensorPosition(0);
            climber.setPower(ClimberConstants.CLIMBER_CALIBRATION_POWER);
        }
        if(climber.atTop()) {
            ClimberConstants.LocalClimberConstants climberConstants = new ClimberConstants.LocalClimberConstants();
            climberConstants.maxTicks = climber.getSelectedSensorPosition() / 2;
            try {
                // JsonHandler.saveToJsonFile(climberConstants, "ClimberConstants.json");
                ClimberConstants.maxTicks = climber.getCurrentPosition() / 2;
            } catch(Exception e) {
                e.printStackTrace();
            }
            climber.setSelectedSensorPosition(climber.getSelectedSensorPosition() / 2);
        }
    }

    @Override
    public boolean isFinished() {
        return climber.atTop();
    }

    @Override
    public void end(boolean interrupted) {
        climber.stop();
    }
}
