package frc.team4909.robot;

import frc.team4909.robot.operator.generic.BionicAxis;

public class RobotConstants {
  /* Operator Input */
  public static final double driverGamepadSensitivity = 0.6; // Value selected from 2018 code
  public static final double driverGamepadDeadzone = 0.05; // Value selected from 2018 code

  public static final double manipulatorGamepadSensitivity = 0.6; // Value selected from 2018 code
  public static final double manipulatorGamepadDeadzone = 0.05; // Value selected from 2018 code

  /* Drivetrain */
  public static final double speedMultiplier = .7;
  public static final double speedTurnMultiplier = .4;
  public static final double speedTurnPreciseMultiplier = .2;

  /* Intake */
  public static final double cargoIntakeInSpeed = 1.0;
  public static final double cargoIntakeHoldSpeed = 0.0625;
  public static final double cargoIntakeOutSpeed = -1.0;

 // public static final double cargoIntakeCurrentLimit = 0;

  /* LIDAR */
  public static final int calibrationOffset = -9; // calibration constant used for Lidar
  public static final double lidarLimit = 30; // cm

  /*
   * Sensor Threshold derived by testing the minimum voltage readouts when the
   * ball is placed LEFT, RIGHT, and CENTER. This value should then be compared to
   * when there is no cargo to ensure that the values do not overlap. The
   * distinguishing value is then denoted as the treshold.
   */

  /* Sensors */
  public static final double irSensorThreshold = 1.7;

  /* Elevator */
  public static final double elevatorSpeedMultiplier = 0.5; // Multiplier for elevator speed
  public static final double elevatorArmSpeedMultiplier = 0.75; // Multiplier for elevator arm speed

  /* Linefollow */
  public static final double fastVelocity = 0.7;
  public static final double mediumVelocity = 0.5; // constant velocity given to both motors
  public static final double slowVelocity = 0.2; // value given to motor when trying to turn

  /* Climber */
  public static final double climberStiltSpeed = 0.3;
  public static final double climberDriveSpeed = 0.3;
  public static final double elevatorClimbSpeed = 0.3;

  /* Timeout */
  public static final int timeoutMs = 30; // milliseconds
}