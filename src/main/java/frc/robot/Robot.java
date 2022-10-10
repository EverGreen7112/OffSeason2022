// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import org.ejml.ops.ConvertMatrixData;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.Vision;
import frc.robot.Commands.Collect;
import frc.robot.Commands.DistancePlusAngle;
import frc.robot.Commands.DriveBySuplliers;
import frc.robot.Commands.EncoderPID;
import frc.robot.Commands.PIDDriveBySuppliers;
import frc.robot.Commands.Shoot;
import frc.robot.Commands.TurnToAngle;
import frc.robot.Commands.VectorDrive;
import frc.robot.Constants.Speeds;
import frc.robot.SubSystems.Chassis;
import frc.robot.SubSystems.Collector;

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
  // private NetworkTableInstance m_instance;
  // private NetworkTable m_table;
  // private NetworkTableEntry angle,distance,canSee;
  
  

  @Override
  public void robotInit() {
  }

  @Override
  public void robotPeriodic() {
  }
  public static Vision ball_Vision = new Vision(5801);
  public static Vision hub_Vision = new Vision(5800);
  @Override
  public void autonomousInit() {
    CommandBase driveToBall = new PIDDriveBySuppliers(ball_Vision::getZ,0, 0.1).andThen
    (new RunCommand(()->Chassis.getInstance().driveStraight(Constants.Speeds.extraSpeed), Chassis.getInstance()).withTimeout(0.2));
    
    new SequentialCommandGroup(new ParallelDeadlineGroup(driveToBall,
    new Collect()), new TurnToAngle(0),new PIDDriveBySuppliers(hub_Vision::getZ, 1, 0.1),new Shoot()).schedule(); 
    
   
  // 1.drive and collect ball
  // 2.turn around until target's degree is equal to 0
  // 3.drive to the target
  // 4.shoot
    
  }

  @Override
  public void autonomousPeriodic() {
    CommandScheduler.getInstance().run();
  }



  @Override
  public void teleopInit() {
    Chassis.getInstance().setDefaultCommand(new VectorDrive(() ->Controls.m_leftJoystick.getY() * Speeds.speedFactor, () ->Controls.m_rightJoystick.getY() * Speeds.speedFactor));
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


  PIDDriveBySuppliers PIDDBS = new PIDDriveBySuppliers(ball_Vision::getZ, 1, 0);
  EncoderPID ePID = new EncoderPID();
  TurnToAngle TTA = new TurnToAngle(0);
  DistancePlusAngle DPA = new DistancePlusAngle(1);

  @Override
  public void testInit() {
   Chassis.getInstance();
   SmartDashboard.putNumber("motorSpeed", 0.4);
   Controls.m_b0.toggleWhenActive(new Collect());
    // Collector.getInstance().liftCollector();
    Collector.getInstance().lowerCollector();

  }

  @Override
  public void testPeriodic() {
    // TTA.execute();
    // DPA.execute();
    // PIDDBS.execute();
    Chassis.getInstance().driveTank(0, 0.5);
  }

  @Override
  public void testExit() {
  }

  @Override
  public void simulationInit() {
  }

  @Override
  public void simulationPeriodic() {
  }
}
