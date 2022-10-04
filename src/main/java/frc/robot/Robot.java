// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Commands.Collect;
import frc.robot.Commands.DriveBySuplliers;
import frc.robot.Commands.DriveForward;
import frc.robot.Commands.DriveToCenter;
import frc.robot.Commands.Shoot;
import frc.robot.Commands.VectorDrive;
import frc.robot.SubSystems.Chassis;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the
 * name of this class or
 * the package after creating this project, you must also update the
 * build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {


  /**
   * This function is run when the robot is first started up and should be used
   * for any
   * initialization code.
   */
  @Override
  public void robotInit() {
  }

  @Override
  public void robotPeriodic() {
  }

  @Override
  public void autonomousInit() {
    CommandScheduler.getInstance().schedule(DriveForward.getInstance().withTimeout(0.5));
  }

  @Override
  public void autonomousPeriodic() {
    CommandScheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    Chassis.getInstance().setDefaultCommand(DriveBySuplliers.getInstance());
  }

  @Override
  public void teleopPeriodic() {
    CommandScheduler.getInstance().run();

  }

  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  @Override
  public void testInit() {
    Chassis.getInstance();
   SmartDashboard.putNumber("motorSpeed", 0.4);
   Controls.m_b0.whileHeld(new Collect());
   Controls.m_b1.whileHeld(new Shoot());
   
  //  Chassis.getInstance();
  //  if (Chassis.getInstance().getDefaultCommand() == null)
      //Chassis.getInstance().setDefaultCommand(new VectorDrive(Controls.m_leftJoystick::getY, Controls.m_rightJoystick::getY));
      // Chassis.getInstance().setDefaultCommand(new PrintCommand("Hello1"));

    //  Chassis.getInstance().setDefaultCommand(new VectorDrive(Controls.m_leftJoystick::getY, Controls.m_rightJoystick::getY));
  }

  @Override
  public void testPeriodic() {
    //  VectorDrive.getInstance().execute();
    DriveToCenter.getInstance().execute();
    // CommandScheduler.getInstance().schedule(new VectorDrive(Controls.m_leftJoystick::getY, Controls.m_rightJoystick::getY));
    // CommandScheduler.getInstance().run();
    // SmartDashboard.putString("defCom", Chassis.getInstance().getDefaultCommand() != null?Chassis.getInstance().getDefaultCommand().toString(): "null");
    
  }
  // @Override
  // public void testExit() {
  //   CommandBase s = new WaitCommand(0.1);
  //   s.addRequirements(PrintCommand);
  //   Chassis.getInstance().setDefaultCommand(s);
  //   SmartDashboard.putString("defCom", Chassis.getInstance().getDefaultCommand() != null?Chassis.getInstance().getDefaultCommand().toString(): "null");
  // }

  @Override
  public void testExit() {
    Chassis.getInstance().setDefaultCommand(new VectorDrive(Controls.m_leftJoystick::getY, Controls.m_rightJoystick::getY));
  }

  @Override
  public void simulationInit() {
  }

  @Override
  public void simulationPeriodic() {
  }
}
