package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.DriveTrain;
import frc.robot.subsystems.DriveSubsystem;

public class ArcadeDriveCmd extends Command {
    
    private final DriveSubsystem driveSubsystem; 
    private final Supplier<Double> speedFunction, turnFunction, percisionTrig, turboTrig;
    private static double speedFactor = DriveTrain.kSpeedFactor; 
    private static double turnFactor = DriveTrain.kTurnFactor; 

    public ArcadeDriveCmd(DriveSubsystem driveSubsystem, //
    Supplier<Double> speedFunction, Supplier<Double> turnFunction, Supplier<Double> percisionTrig, Supplier<Double> turboTrig) { 
        this.driveSubsystem = driveSubsystem;
        this.speedFunction = speedFunction;
        this.turnFunction = turnFunction;
        this.percisionTrig = percisionTrig;
        this.turboTrig = turboTrig;
        addRequirements(driveSubsystem);
    }

  @Override
  public void initialize() {

  }

  @Override
  public void execute() {
    double realTimeSpeed = speedFunction.get();
    double realTimeTurn = turnFunction.get();
    double realTimePercision = percisionTrig.get();
    double realTimeTurbo = turboTrig.get();

    // Deadband
    realTimeSpeed = Math.abs(realTimeSpeed) > DriveTrain.kDeadband ? realTimeSpeed : 0.0;
    realTimeTurn = Math.abs(realTimeTurn) > DriveTrain.kDeadband ? realTimeTurn : 0.0; 




    if (realTimeTurbo > 0.2 && realTimePercision < 0.2) {
      speedFactor = DriveTrain.kTurboSpeed;
      double speedScaleFactor = 1/DriveTrain.kSpeedFactor; 
      turnFactor = DriveTrain.kSpeedFactor * speedScaleFactor; 
      SmartDashboard.putBoolean("TurboActivation", true);
      SmartDashboard.putBoolean("PercisionActivation", false);
    } else if (realTimePercision > 0.2 && realTimeTurbo < 0.2)  {
      double turnScaleFactor = 1/DriveTrain.kTurnFactor;
      double turnFactor2 = DriveTrain.kTurnFactor * turnScaleFactor;
      SmartDashboard.putBoolean("TurboActivation", false);
      SmartDashboard.putBoolean("PercisionActivation", true);
    if (realTimeSpeed > 0) { 
      speedFactor = DriveTrain.kPercisionSpeed/2;
      turnFactor = turnFactor2; 
      SmartDashboard.putString("DriveMotorDirection", "Backwards");
    } else {
      speedFactor = DriveTrain.kPercisionSpeed; 
      turnFactor = DriveTrain.kPercisionTurn;
      SmartDashboard.putString("DriveMotorDirection", "Forwards"); 
    }
  } else {
      speedFactor = DriveTrain.kSpeedFactor;  
      turnFactor = DriveTrain.kTurnFactor; 
      SmartDashboard.putBoolean("TurboActivation", false);
      SmartDashboard.putBoolean("PercisionActivation", false);
      SmartDashboard.putString("DriveMotorDirection", "N/A");
    }
    
    double factoredSpeed = realTimeSpeed * speedFactor;
    double factoredTurn = realTimeTurn * turnFactor; 

    double left = factoredTurn - factoredSpeed;
    double right = factoredTurn + factoredSpeed;
    driveSubsystem.setMotors(left, right);  
    
    SmartDashboard.putNumber("SpeedFactor", speedFactor);
    SmartDashboard.putNumber("TurnFactor", turnFactor);
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
