// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.trigon.robot;

import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.trigon.robot.commands.AutoShootCommand;
import frc.trigon.robot.commands.CollectCommand;
import frc.trigon.robot.commands.Commands;
import frc.trigon.robot.components.HubLimelight;
import frc.trigon.robot.controllers.XboxController;
import frc.trigon.robot.subsystems.ballscounter.BallsCounter;
import frc.trigon.robot.subsystems.ballscounter.CountBallsCommand;
import frc.trigon.robot.subsystems.loader.Loader;
import frc.trigon.robot.subsystems.pitcher.Pitcher;
import frc.trigon.robot.subsystems.shooter.Shooter;
import frc.trigon.robot.subsystems.shooter.ShotsDetectorCommand;
import frc.trigon.robot.subsystems.swerve.FieldRelativeSupplierDrive;
import frc.trigon.robot.subsystems.swerve.Swerve;

public class RobotContainer {
    XboxController controller;
    PowerDistribution powerDistribution;
    public static HubLimelight hubLimelight = new HubLimelight("limelight");

    CollectCommand collectCommand;
    FieldRelativeSupplierDrive swerveCmd;
    Command primeShooterCmd;
    Command pitchCmd;

    CountBallsCommand countBallsCommand;
    ShotsDetectorCommand shotsDetectorCommand;

    CommandBase turnToHubCmd;
    AutoShootCommand autoShootCommand;

    public RobotContainer() {
        initComponents();
        initCommands();
        bindCommands();

        putSendablesOnSmartDashboard();

        powerDistribution.clearStickyFaults();
    }

    private void initComponents() {
        controller = new XboxController(0, true, 0.05);
        powerDistribution = new PowerDistribution(43, PowerDistribution.ModuleType.kRev);
    }

    private void initCommands() {
        swerveCmd = new FieldRelativeSupplierDrive(
                () -> controller.getLeftY(),
                () -> -controller.getLeftX(),
                () -> -controller.getRightX()
        );
        collectCommand = new CollectCommand();

        countBallsCommand = new CountBallsCommand();
        shotsDetectorCommand = new ShotsDetectorCommand();

        turnToHubCmd = Commands.getTurnToLimelight0Command();

        primeShooterCmd = Commands.getPrimeShooterByLimelightCommand();
        pitchCmd = Commands.getPitchByLimelightCommand();
        autoShootCommand = new AutoShootCommand();
    }

    private void bindCommands() {
        Swerve.getInstance().setDefaultCommand(swerveCmd);
        Pitcher.getInstance().setDefaultCommand(pitchCmd);
        Shooter.getInstance().setDefaultCommand(primeShooterCmd);

        controller.getYBtn().whenPressed(Swerve.getInstance()::zeroHeading);
        controller.getLeftBumperBtn().whileHeld(collectCommand);
        controller.getBBtn().whileHeld(autoShootCommand);
        controller.getABtn().whileHeld(Loader.getInstance().getLoadCommand());
        controller.getXBtn().whileHeld(turnToHubCmd);

        countBallsCommand.schedule();
        shotsDetectorCommand.schedule();
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
    }
}
