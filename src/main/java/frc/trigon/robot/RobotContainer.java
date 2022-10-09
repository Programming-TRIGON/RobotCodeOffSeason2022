// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.trigon.robot;

import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.trigon.robot.commands.CollectCommand;
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
    HubLimelight limelight;

    CollectCommand collectCommand;
    FieldRelativeSupplierDrive swerveCmd;

    CountBallsCommand countBallsCommand;
    ShotsDetectorCommand shotsDetectorCommand;

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
        limelight = new HubLimelight("limelight");
    }

    private void initCommands() {
        swerveCmd = new FieldRelativeSupplierDrive(
                () -> -controller.getLeftY(),
                () -> controller.getLeftX(),
                () -> -controller.getRightX()
        );
        collectCommand = new CollectCommand();

        countBallsCommand = new CountBallsCommand();
        shotsDetectorCommand = new ShotsDetectorCommand();
    }

    private void bindCommands() {
        Swerve.getInstance().setDefaultCommand(swerveCmd);
        controller.getABtn().whileHeld(collectCommand);
        controller.getYBtn().whenPressed(Swerve.getInstance()::zeroHeading);
        controller.getBBtn().whileHeld(Loader.getInstance().getLoadCommand());

        countBallsCommand.schedule();
        shotsDetectorCommand.schedule();
    }

    private void putSendablesOnSmartDashboard() {
        SmartDashboard.putData(Shooter.getInstance());
        SmartDashboard.putData(Pitcher.getInstance());
        SmartDashboard.putData(limelight);
        SmartDashboard.putData(BallsCounter.getInstance());
        SmartDashboard.putData(BallsCounter.getInstance().countBallsCommand);
        SmartDashboard.putData(Shooter.getInstance());
        SmartDashboard.putData(Shooter.getInstance().shotsDetectorCommand);
        SmartDashboard.putData(Swerve.getInstance());
    }
}
