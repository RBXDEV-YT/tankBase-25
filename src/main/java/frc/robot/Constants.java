// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

public final class Constants {   
    public static class DriveTrain {
        public static final int kLeftMasterMotorID = 18;
        public static final int kLeftSlaveMotorID = 19;
        public static final int kRightMasterMotorID = 2;
        public static final int kRightSlaveMotorID = 1;
        public static final int kLeftIntakeID = 9;
        public static final int kRightIntakeID = 7;
        public static final int kJoystickPort = 0; 
        
        public static final double kSpeedFactor = 0.6; 
        public static final int kTurboSpeed = 1; 
        public static final double kPercisionSpeed = 0.2;
        public static final double kPercisionTurn = 0.4;  
        public static final double kTurnFactor = 0.6;
        public static final double kTestSpeed = 0.3; 
        public static final double kIntakeSpeed = 1;
        
        public static final double kDeadband = 0.1;

        public static final double aSpeedFactor = 0.5;
        public static final double aTurnFactor = 0.5;
        public static final double aIntakeSpeed = 1;

        public static final int kSparkMaxID = 3;
    }

    public static class DriveControls { 

 //XBOX
 public static final int kDriveAxis = 1;
 public static final int kTurnAxis = 4; 
 public static final int kTurboAxis = 2; 
 public static final int kPercisionAxis = 3;
 public static final int kIntakeButton = 5;
 public static final int kIntakeButtonOUT = 6;

 public static final int kTurboButton = 7; 
 public static final int kPercisionButton = 8;
    }

    public static class ArmSystems {

        public static final double kDefaultAngle = 0.31 * 360;
        public static final double kIntakeAngle = 0.48 * 360;
        public static final double kShootAngle = 0.34 * 360;
}
}
