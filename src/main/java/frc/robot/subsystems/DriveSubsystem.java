// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveTrain;

public class DriveSubsystem extends SubsystemBase {

  private final VictorSPX leftMaster = new VictorSPX(DriveTrain.kLeftMasterMotorID); //wasn't static before
  private final VictorSPX leftSlave = new VictorSPX(DriveTrain.kLeftSlaveMotorID); 
  private final VictorSPX rightMaster = new VictorSPX(DriveTrain.kRightMasterMotorID); //wasn't static before
  private final VictorSPX rightSlave = new VictorSPX(DriveTrain.kRightSlaveMotorID);


  public DriveSubsystem() {
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
   SmartDashboard.putNumber("Left speed", leftMaster.getMotorOutputPercent()); 
   SmartDashboard.putNumber("Right speed", rightMaster.getMotorOutputPercent());
  }

public void setMotors(double left, double right) {
  leftMaster.set(ControlMode.PercentOutput, left);
  leftSlave.set(ControlMode.PercentOutput, left);
  rightMaster.set(ControlMode.PercentOutput, right);
  rightSlave.set(ControlMode.PercentOutput, right);

  ElbowSubsystem.SparkController.set(left);

  //SmartDashboard.putNumber("Left speed", left); 
  //SmartDashboard.putNumber("Right speed", right);
}

public void resetMotors() {
  leftMaster.set(ControlMode.Disabled, 0);
  leftSlave.set(ControlMode.Disabled, 0);
  rightMaster.set(ControlMode.Disabled, 0);
  rightSlave.set(ControlMode.Disabled, 0);
  ElbowSubsystem.SparkController.stopMotor();
}
}
