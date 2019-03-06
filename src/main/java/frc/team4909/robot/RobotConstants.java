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
  public static final double speedTurnMultiplier = .5;
  public static final double speedTurnPreciseMultiplier = .4;

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
  public static final double elevatorArmSpeedMultiplier = 0.5; // Multiplier for elevator arm speed

  public static final double initialp = 0.1;
  public static final double initiali = 0;
  public static final double initiald = 0;

  public static final double newp = 0;
  public static final double newi = 0;
  public static final double newd = 0;

  /* Elevator Setpoints */
  public static final int elevatorSetpointHatchLow = 0; // Bottom hatch position
  public static final int elevatorSetpointHatchMiddle = -25300; // Middle hatch position
  public static final int elevatorSetpointHatchHigh = -51300; // Top hatch position

  public static final int elevatorSetpointCargoLow = -10582;
  public static final int elevatorSetpointCargoMiddle = -30638;
  public static final int elevatorSetpointCargoHigh = -20000;
  public static final int elevatorSetpointCargoShip = -25300;

  public static final int elevatorArmSetpoint90 = 90;
  public static final int elevatorArmSetpoint45 = 45;
  /* Linefollow */
  public static final double fastVelocity = 0.7;
  public static final double mediumVelocity = 0.5; // constant velocity given to both motors
  public static final double slowVelocity = 0.2; // value given to motor when trying to turn

  /* Climber */
  public static final double climberStiltSpeed = 0.5;
  public static final double climberDriveSpeedAuto = 0.2;
  public static final double climberDriveSpeedManual = 0.5;
  public static final double elevatorClimbSpeed = 0.3;
  public static final double climbSpeedMultiplier = 0.5;
  public static final double climbVelocityMultiplier = 800;


  /* Timeout */
  public static final int timeoutMs = 30; // milliseconds

}