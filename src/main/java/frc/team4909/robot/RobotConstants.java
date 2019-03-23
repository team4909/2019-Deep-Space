package frc.team4909.robot;

public class RobotConstants {
  /* Operator Input */
  public static final double driverGamepadSensitivity = 0.6; // Value selected from 2018 code
  public static final double driverGamepadDeadzone = 0.1
  ; // Value selected from 2018 code

  public static final double manipulatorGamepadSensitivity = 0.7; // Value selected from 2018 code
  public static final double manipulatorGamepadDeadzone = 0.15; // Value selected from 2018 code

  public static final double climberGamepadSensitivity = 0.6; // Value selected from 2018 code
  public static final double climberGamepadDeadzone = 0.15; // Value selected from 2018 code


  /* Drivetrain */
  //TODO: Tune values

  public static double speedMultiplier = 0.75; 
  //0.7 is the desired multiplier speed at bottom; 0.1 is desired multiplier speed at top; 5000 is total height in encoder ticks
  public static double speedTurnMultiplier = 0.7; // As height increases, decrease speed ; calculation: 0.5 - (0.5-0.1)/5000

  public static final double maxDriveSpeed = 0.7; // desired values for when elevator is at bottom
  public static final double minDriveSpeed = 0.3; // Min velocity when elevator is at top

  public static final double maxTurnSpeed = 0.5; // desired values for when elevator is at bottom
  public static final double minTurnSpeed = 0.3; // desired values for when elevator is at bottom
  
  public static final double elevatorEncoderTicks = 51000; // total height of elevator

  public static final double turnPreciseMultiplier = .475; // value when precise mode is activated; slows the turn; doesn't affect speed
  public static final double drivePreciseMultiplier = .5;

  /* Intake */
  public static final double cargoIntakeInSpeed = 1.0;
  public static final double cargoIntakeHoldSpeed = 0.3;
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
  public static final double irSensorThreshold = 2.25;

  /* Elevator */
  public static final double elevatorSpeedMultiplier = 0.75; // Multiplier for elevator speed
  

  public static final double elevatorMaxSpeedUp = .8;
  public static final double elevatorMaxSpeedDown = -.4;

  /* Wrist */
  public static final double elevatorArmSpeedMultiplier = 0.3; // Multiplier for elevator arm speed

  public static final double elevatorArmSpeed = 0.2;
  public static final double wristMaxSpeedUp = .5;
  public static final double wristMaxSpeedDown = -.3;

  /* Elevator Setpoints */
  public static final int elevatorSetpointHatchLow = 0; // Bottom hatch position
  public static final int elevatorSetpointHatchMiddle = -25300; // Middle hatch position
  public static final int elevatorSetpointHatchHigh = -51300; // Top hatch position

  public static final int elevatorSetpointCargoLow = -10582;
  public static final int elevatorSetpointCargoMiddle = -30638;
  public static final int elevatorSetpointCargoHigh = -20000;
  public static final int elevatorSetpointCargoShip = -25300;

  public static final int wristSetpointUpright = 3713; // makes arm upright
  public static final int wristSetpointCargoScore = wristSetpointUpright - 393 ; // 45 degrees up
  public static final int wristSetpointHatch = wristSetpointUpright - 1020;  // horizontal arm
  public static final int wristSetpointCargoIn = wristSetpointUpright - 1205; // 45 degrees down
  
  /* Linefollow */
  public static final double fastVelocity = 0.7;
  public static final double mediumVelocity = 0.5; // constant velocity given to both motors
  public static final double slowVelocity = 0.2; // value given to motor when trying to turn

  /* Climber */
  public static final double climberStiltSpeed = 0.5;
  public static final double climberDriveSpeedAuto = 0.2;
  public static final double climberDriveSpeedManual = 0.5;
  public static final double climbSpeedMultiplier = 0.5;
  public static final double climbBothSpeedMultiplier = 0.3;
  public static final double climbVelocityMultiplier = 800;
  public static final double liftClimbSpeed = 0.3;
  public static final double hookMoveSpeed = 1.0;

  /* Timeout */
  public static final int timeoutMs = 30; // milliseconds

}