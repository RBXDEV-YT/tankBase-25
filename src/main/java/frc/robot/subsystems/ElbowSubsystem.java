// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import com.revrobotics.spark.SparkAbsoluteEncoder;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveTrain;

public class ElbowSubsystem extends SubsystemBase {
  
  public static SparkMax SparkController = new SparkMax(DriveTrain.kSparkMaxID, MotorType.kBrushless);
  public static SparkAbsoluteEncoder PIDTest = SparkController.getAbsoluteEncoder();

  private static double kP = 0.001;
  private static double kI = 0;
  private static double kD = 0;

  private final Timer timer;
  
  public static PIDController ElbowPID = new PIDController(kP, kI, kD);


  

  public ElbowSubsystem() {
    timer = new Timer();
    timer.reset();
    timer.start();
  }

  /**
   * 
   *
   * @return 
   */
  public Command exampleMethodCommand() {
    return runOnce(
        () -> {
        });
  }

  /**
   *
   *
   * @return 
   */
  public boolean exampleCondition() {
    return false;
  }

  @Override
  public void periodic() {
    if (ElbowPID.atSetpoint()) {
      SmartDashboard.putBoolean("AtDesiredSetpoint", true);
    } else {
      SmartDashboard.putBoolean("AtDesiredSetpoint", false);
    }
  }

public void setArmPos(double SetAngle) {
  double ElbowSetPointRotationValue = SetAngle/360;
  double PositionConversion = PIDTest.getPosition() * (2 * Math.PI);
  double PIDCalculation = ElbowPID.calculate(PositionConversion, ElbowSetPointRotationValue);
 SparkController.set(PIDCalculation);
}

public void resetMotors() {
}
}