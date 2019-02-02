/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team4909.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  /* Drivetrain  */
  public static final int driveFrontLeftSparkMaxCAN = 0;
  public static final int driveRearLeftSparkMaxCAN = 1;
  public static final int driveFrontRightSparkMaxCAN = 2;
  public static final int driveRearRightSparkMaxCAN = 3;

  /* Intake */
  public static final int intakeForwardChannel = 1;
  public static final int intakeReverseChannel = 2;
  public static final int intakeMotor = 1;
  public static final int leftIRSensor = 0;
  public static final int rightIRSensor = 1;
  
  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  
}