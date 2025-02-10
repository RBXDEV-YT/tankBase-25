package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveSubsystem;

public class AutoDriveStopCmd extends Command {
    
    private final DriveSubsystem driveSubsystem; 

    private boolean isStoppedNow;

    public AutoDriveStopCmd(DriveSubsystem driveSubsystem) { 
        this.driveSubsystem = driveSubsystem;
        addRequirements(driveSubsystem);
        isStoppedNow = false;
    }

    @Override
  public void initialize() {

  }

  @Override
  public void execute() {
  driveSubsystem.setMotors(0, 0);
  SmartDashboard.putBoolean("Auto Drive", false);
  SmartDashboard.putString("Auto Direction", "Stopped");
  isStoppedNow = true;
  System.out.println();
  }

  @Override
  public void end(boolean interrupted) {
    if (interrupted) {
      driveSubsystem.resetMotors(); 
      System.out.println("Finished autoStop"); 
        SmartDashboard.putString("Auto Direction", "Reset");
    }
  }

  @Override
  public boolean isFinished() {
    return (isStoppedNow);
  }
}
