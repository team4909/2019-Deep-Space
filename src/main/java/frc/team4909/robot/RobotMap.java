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
  /*
   * The following variables are PWM slots, with the exception of CAN: CAN Device
   * IDs, need to be configured through respective tool PDP: PDP Slot Number,
   * corresponds to hardware IR: Analog Sensor Input Channel: PCM Solenoid Output
   */

  /* Gamepad */
  public static final int driverGamepadPort = 0;
  public static final int manipulatorGamepadPort = 1;
  public static final int climberGamepadPort = 2;

  /* Drivetrain */
  public static final int driveFrontLeftSparkMaxCAN = 3;
  public static final int driveRearLeftSparkMaxCAN = 4;
  public static final int driveFrontRightSparkMaxCAN = 1;
  public static final int driveRearRightSparkMaxCAN = 2;

  /* Intake */
  public static final int intakePCMChannelL = 0;
  public static final int intakePCMChannelR= 1;
  public static final int intakeMotorCAN = 9;
  public static final int intakeMotorPDP = 4;
  public static final int leftIRSensor = 0;
  public static final int rightIRSensor = 1;

  /* Elevator */
  public static final int elevatorSRXID = 7; // Master SRX ID Back left
  public static final int elevatorSPX1ID = 4; // Slave 1 ID FrontLeft ELevator Left
  public static final int elevatorSPX2ID = 5; // Slave 2 ID Bottom `Elevator Right
  public static final int elevatorSPX3ID = 6; // Slave 3 ID Back right Elevator right

  public static final int elevatorArmSRXID = 13; // Front
  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:

  /* Climber */
  public static final int climberMasterSRXID = 10;
  public static final int climberSlaveSPXID = 12; // @todo not on robot yet
  public static final int climberDriveSPXID = 11;

  /* Sensors */
  public static final int lidarPort = 4;


}