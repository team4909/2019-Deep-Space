package frc.team4909.robot;

public class RobotConstants {
  /* Gamepad */
  public static final int driverGamepadPort = 0;
  public static final double driverGamepadSensitivity = 0.6; //Value selected from 2018 code
  public static final int driverGamepadDeadzone = 0; //Value selected from 2018 code

  /* Intake */
  public static final double cargoIntakeInSpeed = 1.0;
  public static final double cargoIntakeHoldSpeed = 0;
  public static final double cargoIntakeOutSpeed = -1.0;
  /* Sensor Threshold derived by testing the minimum voltage 
   * readouts when the ball is placed LEFT, RIGHT, and CENTER.
   * This value should then be compared to when there is no cargo
   * to ensure that the values do not overlap. The distinguishing value 
   * is then denoted as the treshold.
   */
  public static final double irSensorThreshold = 1.7;

  /* Linefollow */
  public static final double fastVelocity = 0.5; //constant velocity given to both motors
  public static final double slowVelocity = 0.2; //value given to motor when trying to turn
}