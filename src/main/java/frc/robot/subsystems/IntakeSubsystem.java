package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase{

  //public static final Spark leftIntake = new Spark(DriveTrain.kLeftIntakeID); 
  //public static final Spark rightIntake = new Spark(DriveTrain.kRightIntakeID); 

  public IntakeSubsystem() {
    //SmartDashboard.putNumber("Left Intake speed", leftIntake.get());
    //SmartDashboard.putNumber("Right Intake speed", rightIntake.get());
  }

  public void setPosition(boolean open, double speed) {
    if (open/* .toLowerCase() == "open"*/) {
    //leftIntake.set(speed);
    //rightIntake.set(-speed);
    SmartDashboard.putString("Intake Pos", "OPEN");
    } else /*if (open.toLowerCase() == "close")*/ {
    //leftIntake.set(-speed);
    //rightIntake.set(speed); 
    SmartDashboard.putString("Intake Pos", "CLOSED");
    } /*else {
    leftIntake.set(0);
    rightIntake.set(0); 
    SmartDashboard.putString("Intake Pos", "OFF"); 
    }*/
  }

  public void resetIntake() {
      //leftIntake.set(0);
      //rightIntake.set(0);
      SmartDashboard.putString("Intake Pos", "OFF"); 
      //leftIntake.stopMotor();
      //rightIntake.stopMotor(); 
  }
}
