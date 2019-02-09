package frc.team4909.robot;

import frc.team4909.robot.operator.generic.BionicAxis;

public class RobotConstants {
  /* Operator Input */
  public static final int driverGamepadPort = 0;
  public static final double driverGamepadSensitivity = 0.6; // Value selected from 2018 code
  public static final int driverGamepadDeadzone = 0; // Value selected from 2018 code

  /* Drivetrain */
  public static final double speedMultiplier = .7;  

  /* Intake */
  public static final double cargoIntakeInSpeed = 1.0;
  public static final double cargoIntakeHoldSpeed = 0;
  public static final double cargoIntakeOutSpeed = -1.0;

  public static final double cargoIntakeCurrentLimit = 20;

  /* LIDAR */
  public static final int calibrationOffset = -2;

  /*
   * Sensor Threshold derived by testing the minimum voltage readouts when the
   * ball is placed LEFT, RIGHT, and CENTER. This value should then be compared to
   * when there is no cargo to ensure that the values do not overlap. The
   * distinguishing value is then denoted as the treshold.
   */
  /* Sensors */
  public static final double irSensorThreshold = 1.7;


  /* Elevator */
  public static final int elevatorSRXID = 7;
  public static final int elevatorSPX1ID = 4;
  public static final int elevatorSPX2ID = 5;
  public static final int elevatorSPX3ID = 6;

  public static final double elevatorSpeedMultiplier = 1.0;

  public static final double lidarLimit = 20; // cm

  /* Linefollow */
  public static final double fastVelocity = 0.7;
  public static final double mediumVelocity = 0.5; // constant velocity given to both motors
  public static final double slowVelocity = 0.2; // value given to motor when trying to turn

}