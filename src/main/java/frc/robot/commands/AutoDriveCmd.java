package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveSubsystem;

public class AutoDriveCmd extends Command {
    
    private final DriveSubsystem driveSubsystem; 
    private double driveSpeed;
    private String direction;
    private double driveTime;

    private final Timer timer;

    public AutoDriveCmd(DriveSubsystem driveSubsystem, String direction, double driveSpeed, double driveTime) { 
        this.driveSpeed = driveSpeed;
        this.direction = direction;
        this.driveTime = driveTime;
        this.driveSubsystem = driveSubsystem;
        addRequirements(driveSubsystem);
        timer = new Timer();
    }

    @Override
  public void initialize() {
    timer.reset();
    timer.start();

    System.out.println("AutoDriving");
  }

  @Override
  public void execute() {

  SmartDashboard.putBoolean("Auto Drive", true);

    if (direction == "fw") {
      double left = driveSpeed; 
      double right = driveSpeed * -1;
    
    driveSubsystem.setMotors(left, right); 
    SmartDashboard.putString("Auto Direction", "Forwards");

    } else if (direction.toLowerCase() == "bw") {
      double left = driveSpeed * -1 ;  
      double right = driveSpeed;
    
    driveSubsystem.setMotors(left, right); 

    SmartDashboard.putString("Auto Direction", "Backwards"); 
    
    } else if (direction.toLowerCase() == "left") {
      double left = driveSpeed * -1;  
      double right = driveSpeed * -1;
    
    driveSubsystem.setMotors(left, right);  

    SmartDashboard.putString("Auto Direction", "left");

    } else if (direction.toLowerCase() == "right") {
      double left = driveSpeed;  
      double right = driveSpeed;
    
    driveSubsystem.setMotors(left, right);  

    SmartDashboard.putString("Auto Direction", "right");
    }
    //double left = factoredSpeed + factoredTurn; 
    //double right = factoredSpeed - factoredTurn; 
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return (timer.get() >= driveTime);
  }
}
