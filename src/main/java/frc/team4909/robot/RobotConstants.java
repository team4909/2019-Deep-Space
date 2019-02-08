package frc.team4909.robot;

import frc.team4909.robot.operator.generic.BionicAxis;

public class RobotConstants {
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

  /* Elevator */
  public static int elevatorSRXID = 7;
  public static int elevatorSPX1ID = 4;
  public static int elevatorSPX2ID = 5;
  public static int elevatorSPX3ID = 6;

  public static double elevatorSpeedMultiplier = 1.0;
}