package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;

public class AutoIntakeCmd extends Command {
    
  private final IntakeSubsystem intakeSubsystem;
  private boolean open;
  private double speed; 
  private double timeSet;
  private boolean resetMotors;

    private final Timer timer;

    public AutoIntakeCmd(IntakeSubsystem intakeSubsystem, boolean open, double speed, double timeSet, boolean resetMotors) { 
      this.open = open;
      this.speed = speed;
      this.intakeSubsystem = intakeSubsystem;
      this.timeSet = timeSet;
      this.resetMotors = resetMotors;
      addRequirements(intakeSubsystem);
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
    if (resetMotors) {
      intakeSubsystem.resetIntake();
    } else {
      intakeSubsystem.setPosition(open, speed);
    }

  }

  @Override
  public void end(boolean interrupted) {
    if (interrupted) {
      //IntakeSubsystem.leftIntake.set(0);
      //IntakeSubsystem.rightIntake.set(0);
      SmartDashboard.putString("Intake Pos", "OFF"); 
  }
}

  @Override
  public boolean isFinished() {
    return (timer.get() >= timeSet);
  }
}
