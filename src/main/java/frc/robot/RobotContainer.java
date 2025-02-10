// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
//import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
//import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.DriveControls;
import frc.robot.Constants.DriveTrain;
import frc.robot.commands.ArcadeDriveCmd;
import frc.robot.commands.AutoDriveCmd;
import frc.robot.commands.AutoDriveStopCmd;
import frc.robot.commands.AutoIntakeCmd;
import frc.robot.commands.IntakeSetCmd;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ElbowSubsystem;
import frc.robot.subsystems.IntakeSubsystem; 



public class RobotContainer {
  
  private final DriveSubsystem driveSubsystem = new DriveSubsystem();
  private final IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
  private final ElbowSubsystem elbowSubsystem = new ElbowSubsystem();  

  private final XboxController controller1 = new XboxController(DriveTrain.kJoystickPort); 

  private final Command m_driveOnly = new SequentialCommandGroup( // 
        
    new AutoDriveCmd(driveSubsystem, "fw", DriveTrain.aSpeedFactor/2, 2),
    
    new AutoDriveCmd(driveSubsystem, "bw", DriveTrain.aSpeedFactor/2, 2),

    new AutoDriveStopCmd(driveSubsystem)
    );
  private final Command m_driveIntake = new SequentialCommandGroup( //

  new ParallelCommandGroup( //

  new AutoDriveCmd(driveSubsystem, "fw", DriveTrain.aSpeedFactor/2, 2),
  new AutoIntakeCmd(intakeSubsystem, false, 1, 3, false)
  ),
  
  new AutoDriveCmd(driveSubsystem, "bw", DriveTrain.aSpeedFactor, 1),
  new AutoDriveStopCmd(driveSubsystem), 
  new AutoIntakeCmd(intakeSubsystem, true, 1, 0.5, false), 
  new AutoIntakeCmd(intakeSubsystem, false, 0, 1, true)

  );

  private final Command m_driveTurn = new SequentialCommandGroup( // 
        
    new AutoDriveCmd(driveSubsystem, "fw", 0.4, 1.5), 
    new AutoDriveCmd(driveSubsystem, "right", 0.6, 1),
    new AutoDriveStopCmd(driveSubsystem)
    );


  public RobotContainer() {

    driveSubsystem.setDefaultCommand(
    new ArcadeDriveCmd(driveSubsystem, () -> controller1.getLeftY() , () -> controller1.getRightX(), () -> controller1.getLeftTriggerAxis(), () -> controller1.getRightTriggerAxis())
    );
    
    configureBindings();
  }

  private void configureBindings() {
    //INTAKE BUTTONS

    //Intake
    new JoystickButton(controller1, DriveControls.kIntakeButton).whileTrue(new IntakeSetCmd(intakeSubsystem, false, DriveTrain.kIntakeSpeed));
    new JoystickButton(controller1, DriveControls.kIntakeButtonOUT).whileTrue(new IntakeSetCmd(intakeSubsystem, true, DriveTrain.kIntakeSpeed));
    //new JoystickButton(controller1, 1).whileTrue(new AutoDriveCmd(driveSubsystem, "fw", 1, 5));

    //Arm
    /*
    new JoystickButton(controller1, 2).onTrue(new ElbowPosCmd(elbowSubsystem, () -> PIDTest.getPosition(), "HOLD", false));
    new JoystickButton(controller1, 1).onTrue(new ElbowPosCmd(elbowSubsystem, () -> PIDTest.getPosition(), "INTAKE", false));
    new JoystickButton(controller1, 4).onTrue(new ElbowPosCmd(elbowSubsystem, () -> PIDTest.getPosition(), "SHOOT", false));
    new JoystickButton(controller1, 3).onTrue(new ElbowPosCmd(elbowSubsystem, () -> PIDTest.getPosition(), "DEFAULT", false)); */
  }

  public Command getAutonomousCommand() {
    switch (Robot.m_chooser.getSelected()) {
      case "DriveIntake":
      return m_driveIntake;
      case "DriveTurn":
      return m_driveTurn;
      case "DriveForward":
        default:
      return m_driveOnly;
  }
}
}
