/*package frc.robot.commands;

import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants.DriveTrain;

public final class Autos {

    private final DriveSubsystem driveSubsystem = new DriveSubsystem();
    private final IntakeSubsystem intakeSubsystem = new IntakeSubsystem(); 

    public void DriveForwardAuto() {
        new SequentialCommandGroup( // 
        
        new AutoDriveCmd(driveSubsystem, "fw", DriveTrain.aSpeedFactor/2, 2),
        
        new AutoDriveCmd(driveSubsystem, "bw", DriveTrain.aSpeedFactor/2, 2),

        new AutoDriveStopCmd(driveSubsystem)
    ); 
    }

    public void DriveTurnAuto() {
        new SequentialCommandGroup( // 
        
        new AutoDriveCmd(driveSubsystem, "fw", 0.4, 1.5), 

        new AutoDriveCmd(driveSubsystem, "right", 0.6, 1),

        new AutoDriveStopCmd(driveSubsystem)
        );
    }

    public void DriveIntakeAuto() {
        new SequentialCommandGroup( //
        
        new ParallelCommandGroup( //
        
        new AutoDriveCmd(driveSubsystem, "fw", DriveTrain.aSpeedFactor, 1), 

       new IntakeSetCmd(intakeSubsystem, false, 1)
        ),
  

        new ParallelCommandGroup(new AutoDriveStopCmd(driveSubsystem), new IntakeSetCmd(intakeSubsystem, false, 0)),


        new AutoDriveCmd(driveSubsystem, "bw", DriveTrain.aSpeedFactor, 1),


        new AutoDriveStopCmd(driveSubsystem),

        new IntakeSetCmd(intakeSubsystem, true, 1)
        );
    }
    
}
*/
