/*package frc.robot.commands;

import java.util.function.Supplier;

import com.revrobotics.spark.SparkAbsoluteEncoder;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.ArmSystems;
import frc.robot.Constants.DriveTrain;
import frc.robot.subsystems.ElbowSubsystem;

public class ElbowPosCmd extends Command {

  public static SparkMax SparkController = new SparkMax(DriveTrain.kSparkMaxID, MotorType.kBrushless);
  public static SparkAbsoluteEncoder PIDTest = SparkController.getAbsoluteEncoder();
  
  private static double kP = 0.001;
  private static double kI = 0;
  private static double kD = 0;
  
  public static PIDController ElbowPID = new PIDController(kP, kI, kD);
    
    private final ElbowSubsystem elbowSubsystem; 
    private final Supplier<Double> elbowPos;
    private final boolean isAuto;
    private final String elbowSetPosition;
    private double passedAngle;

    public ElbowPosCmd(ElbowSubsystem elbowSubsystem, Supplier<Double> elbowPos, String elbowSetPosition, boolean isAuto) { 
        this.elbowSubsystem = elbowSubsystem;
        this.elbowPos = elbowPos;
        this.elbowSetPosition = elbowSetPosition;
        this.isAuto = isAuto;
        addRequirements(elbowSubsystem);
    }

  @Override
  public void initialize() {
     double realTimeElbowPos = elbowPos.get();

      if (isAuto) {
        //Random auto logic here
      } else {
          if (elbowSetPosition.toUpperCase() == "INTAKE") {
            passedAngle = ArmSystems.kIntakeAngle;
            double ElbowSetPointRotationValue = passedAngle/360;
            double PIDCalculation = ElbowPID.calculate(PIDTest.getPosition(), ElbowSetPointRotationValue);
            SparkController.set(PIDCalculation);
        } else if (elbowSetPosition.toUpperCase() == "SHOOT") {
          passedAngle = ArmSystems.kShootAngle;
            double ElbowSetPointRotationValue = passedAngle/360;
            double PIDCalculation = ElbowPID.calculate(PIDTest.getPosition(), ElbowSetPointRotationValue);
            SparkController.set(PIDCalculation);
        } else if (elbowSetPosition.toUpperCase() == "DEFAULT") {
          passedAngle = ArmSystems.kDefaultAngle; 
            double ElbowSetPointRotationValue = passedAngle/360;
            double PIDCalculation = ElbowPID.calculate(PIDTest.getPosition(), ElbowSetPointRotationValue);
            SparkController.set(PIDCalculation);
        }
      }

      
  }

  @Override
  public void execute() {
        if (ElbowPID.atSetpoint()) {
      SmartDashboard.putBoolean("AtDesiredSetpoint", true);
    } else {
      SmartDashboard.putBoolean("AtDesiredSetpoint", false);
    }
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}*/
