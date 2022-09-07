package frc.trigon.robot.subsystems.climber;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.trigon.robot.utilities.JsonHandler;

public class ClimberEncoderCalibration extends CommandBase {
    private final Climber climber = Climber.getInstance();

    public ClimberEncoderCalibration() {
        addRequirements(climber);
    }

    @Override
    public void initialize() {
        climber.setPower(-ClimberConstants.CLIMBER_CALIBRATION_POWER);
    }

    @Override
    public void execute() {
        if(climber.atBottom()){
            climber.setSelectedSensorPosition(0);
            climber.setPower(ClimberConstants.CLIMBER_CALIBRATION_POWER);
        }
        if(climber.atTop()){
            JsonHandler.saveToJsonFile(climber.getSelectedSensorPosition(), "ClimberConstants.json");
            climber.setSelectedSensorPosition(climber.getSelectedSensorPosition()/2);
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
