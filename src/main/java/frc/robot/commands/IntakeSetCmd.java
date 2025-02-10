package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;

public class IntakeSetCmd extends Command {
    
    private final IntakeSubsystem intakeSubsystem;
    private boolean open;
    private double speed; 

    public IntakeSetCmd(IntakeSubsystem intakeSubsystem, boolean open, double speed) {
        this.open = open;
        this.speed = speed;
        this.intakeSubsystem = intakeSubsystem;
        addRequirements(intakeSubsystem);
    }

    @Override
  public void initialize() {}

  @Override
  public void execute() {

    intakeSubsystem.setPosition(open, speed);
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
    return false;
  }
}
