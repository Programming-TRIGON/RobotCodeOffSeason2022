// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.trigon.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.Button;
import frc.trigon.robot.commands.PlaybackSimulatedControllerCommand;
import frc.trigon.robot.commands.RecordControllerCommand;
import frc.trigon.robot.commands.AutoShootCommand;
import frc.trigon.robot.commands.CollectCommand;
import frc.trigon.robot.commands.Commands;
import frc.trigon.robot.components.HubLimelight;
import frc.trigon.robot.controllers.simulation.SimulateableController;
import frc.trigon.robot.subsystems.ballscounter.BallsCounter;
import frc.trigon.robot.subsystems.ballscounter.CountBallsCommand;
import frc.trigon.robot.subsystems.collector.Collector;
import frc.trigon.robot.subsystems.loader.Loader;
import frc.trigon.robot.subsystems.pitcher.Pitcher;
import frc.trigon.robot.subsystems.shooter.Shooter;
import frc.trigon.robot.subsystems.shooter.ShotsDetectorCommand;
import frc.trigon.robot.subsystems.swerve.FieldRelativeSupplierDrive;
import frc.trigon.robot.subsystems.swerve.Swerve;
import frc.trigon.robot.subsystems.swerve.TurnToTargetCommand;
import frc.trigon.robot.subsystems.transporter.Transporter;


public class RobotContainer {
    SimulateableController driverController;
    SimulateableController operatorController;

    public static HubLimelight hubLimelight = new HubLimelight("limelight");
    PowerDistribution powerDistribution;

    FieldRelativeSupplierDrive swerveCommand;
    PlaybackSimulatedControllerCommand playbackSimulatedControllerCommand;
    RecordControllerCommand recordControllerCommand;
    CollectCommand collectCommand;
    Command primeShooterCommand;
    Command pitchCommand;
    Command ejectCommand;
    CountBallsCommand countBallsCommand;
    ShotsDetectorCommand shotsDetectorCommand;
    AutoShootCommand autoShootCommand;
    TurnToTargetCommand turnToHubCommand;

    Button foreignBallButton;

    public RobotContainer() {
        initComponents();
        initCommands();
        bindDefaultCommands();
        bindDriverCommands();
        bindOperatorCommands();

        putSendablesOnSmartDashboard();
        LiveWindow.disableAllTelemetry();

        powerDistribution.clearStickyFaults();
    }

    private void initComponents() {
        final int DRIVER_CONTROLLER_PORT = 0;
        final boolean SQUARE_DRIVER_INPUTS = true;
        final double DRIVER_DEADBAND = 0.05;

        final int OPERATOR_CONTROLLER_PORT = 1;
        final boolean SQUARE_OPERATOR_INPUTS = true;
        final double OPERATOR_DEADBAND = 0.05;

        final int POWER_DISTRIBUTION_MODULE = 43;

        driverController = new SimulateableController(
                DRIVER_CONTROLLER_PORT, SQUARE_DRIVER_INPUTS, DRIVER_DEADBAND);
        operatorController = new SimulateableController(OPERATOR_CONTROLLER_PORT, SQUARE_OPERATOR_INPUTS,
                OPERATOR_DEADBAND);
        powerDistribution = new PowerDistribution(POWER_DISTRIBUTION_MODULE, PowerDistribution.ModuleType.kRev);
        hubLimelight = new HubLimelight("limelight");

        foreignBallButton = new Button(() ->
                !BallsCounter.getInstance().getFirstBall().equals("") && !BallsCounter.getInstance().getFirstBall()
                        .equals(DriverStation.getAlliance().name().toLowerCase())
        );
    }

    private void initCommands() {
        swerveCommand = new FieldRelativeSupplierDrive(
                () -> driverController.getLeftY(),
                () -> -driverController.getLeftX(),
                () -> -driverController.getRightX()
        );
        collectCommand = new CollectCommand();

        countBallsCommand = new CountBallsCommand();
        shotsDetectorCommand = new ShotsDetectorCommand();

        primeShooterCommand = Commands.getPrimeShooterByLimelightCommand();
        pitchCommand = Commands.getPitchByLimelightCommand();
        turnToHubCommand = Commands.getTurnToLimelight0Command();
        ejectCommand = Commands.getShooterEjectCommand();
        autoShootCommand = new AutoShootCommand();

        playbackSimulatedControllerCommand = new PlaybackSimulatedControllerCommand(driverController);
        recordControllerCommand = new RecordControllerCommand(driverController);
    }

    private void bindDefaultCommands() {
        Swerve.getInstance().setDefaultCommand(swerveCommand);

        foreignBallButton.whileHeld(ejectCommand);

        countBallsCommand.schedule();
        shotsDetectorCommand.schedule();
    }

    private void bindDriverCommands() {
        driverController.getLeftBumperBtn().whileHeld(collectCommand);
        driverController.getYBtn().whenPressed(Swerve.getInstance()::zeroHeading);
        driverController.getBBtn().whileHeld(autoShootCommand);
        driverController.getXBtn().whileHeld(turnToHubCommand);
    }

    private void bindOperatorCommands() {
        operatorController.getLeftBumperBtn().whileHeld(Transporter.getInstance().getEjectCommand());
        operatorController.getRightBumperBtn().whileHeld(Transporter.getInstance().getLoadCommand());
        operatorController.getBBtn().whileHeld(Loader.getInstance().getLoadCommand());
        operatorController.getXBtn().whileHeld(Loader.getInstance().getEjectCommand());
        operatorController.getYBtn().whileHeld(Shooter.getInstance().getPrimeShooterCommand(() -> 3000));
        operatorController.getABtn().whileHeld(Collector.getInstance().getCollectCommand());
    }

    private void putSendablesOnSmartDashboard() {
        SmartDashboard.putData(Shooter.getInstance());
        SmartDashboard.putData(Pitcher.getInstance());
        SmartDashboard.putData("Hub Limelight", hubLimelight);
        SmartDashboard.putData(BallsCounter.getInstance());
        SmartDashboard.putData(BallsCounter.getInstance().countBallsCommand);
        SmartDashboard.putData(Shooter.getInstance());
        SmartDashboard.putData(Shooter.getInstance().shotsDetectorCommand);
        SmartDashboard.putData(Swerve.getInstance());
        SmartDashboard.putData(recordControllerCommand);
        SmartDashboard.putData(playbackSimulatedControllerCommand);
    }
}
