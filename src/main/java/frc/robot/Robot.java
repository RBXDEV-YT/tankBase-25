// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.spark.SparkAbsoluteEncoder;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.Constants.DriveTrain;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private XboxController joy1 = new XboxController(DriveTrain.kJoystickPort);
  private Command m_autonomousCommand; 

  public static SendableChooser<String> m_chooser = new SendableChooser<>();

  private static final String kDefaultAuto = "DriveForward";
  private static final String kAutonomus1 = "DriveIntake"; 
  private static final String kAutonomus2 = "DriveTurn"; 

  public static SparkMax SparkController = new SparkMax(DriveTrain.kSparkMaxID, MotorType.kBrushless);
  public static SparkAbsoluteEncoder PIDTest = SparkController.getAbsoluteEncoder();

  private RobotContainer m_robotContainer; 

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    
    SmartDashboard.setDefaultBoolean("TurboActivation", false);
    SmartDashboard.setDefaultBoolean("PercisionActivation", false);

    SmartDashboard.setDefaultNumber("SpeedFactor", DriveTrain.kSpeedFactor);
    SmartDashboard.setDefaultNumber("TurnFactor", DriveTrain.kTurnFactor);
    

    SmartDashboard.setDefaultString("Intake Pos", "N/A"); 
    
    m_chooser.setDefaultOption("DriveForward", kDefaultAuto);
    m_chooser.addOption("DriveIntake", kAutonomus1);
    m_chooser.addOption("DriveTurn", kAutonomus2);
    SmartDashboard.putData("Auto choices", m_chooser);
    
    m_robotContainer = new RobotContainer();

  }

  @Override
  public void robotPeriodic() {
    SmartDashboard.putNumber("Drive Axis", joy1.getLeftY());
    SmartDashboard.putNumber("Turn Axis", joy1.getRightX());
    CommandScheduler.getInstance().run(); 
    SmartDashboard.putNumber("ElbowPos", PIDTest.getPosition());
  }

  @Override
  public void disabledInit() {
      SmartDashboard.putString("Intake Pos", "N/A");
  }

  @Override
  public void disabledPeriodic() {
  }

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() { 

    System.out.println("Selected Auto: " + m_chooser.getSelected());
    
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {}

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {
  }

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {}

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {}
}
