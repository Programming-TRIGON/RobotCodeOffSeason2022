// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.trigon.robot;

import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.button.Button;
import frc.trigon.robot.commands.BallsCounter;
import frc.trigon.robot.commands.CollectCommand;
import frc.trigon.robot.subsystems.loader.Loader;
import frc.trigon.robot.subsystems.swerve.FieldRelativeSupplierDrive;
import frc.trigon.robot.subsystems.swerve.Swerve;

/**
 * The VM is configured to automatically run this class, and to call the methods corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
    private RobotContainer robotContainer;
    BallsCounter counter;
    xbox controller = new XboxController(0);
    PWM pwm = new PWM(0);

    @Override
    public void robotInit() {
        new PowerDistribution(43, PowerDistribution.ModuleType.kRev).clearStickyFaults();
        robotContainer = new RobotContainer();
        counter = new BallsCounter();
        counter.schedule();
        SmartDashboard.putData("Balls Counter", counter);

        new Button(controller::getAButton).whileHeld(new CollectCommand());
        new Button(controller::getXButton).whileHeld(Loader.getInstance().getLoadCommand());

        FieldRelativeSupplierDrive fieldRelativeSupplierDrive = new FieldRelativeSupplierDrive(
                () -> -controller.getLeftY(),
                () -> -controller.getLeftX(),
                () -> -controller.getRightX()
        );
        Swerve.getInstance().setDefaultCommand(fieldRelativeSupplierDrive);
        controller.gety.whenPressed(Swerve.getInstance()::zeroHeading);
    }

    @Override
    public void robotPeriodic() {
        CommandScheduler.getInstance().run();
        if(!counter.isScheduled())
            counter.schedule();
        SmartDashboard.putNumber("pwm0", pwm.getRaw());
    }
}
