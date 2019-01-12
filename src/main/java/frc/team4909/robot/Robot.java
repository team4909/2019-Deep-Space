package frc.team4909.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * The VM is configured to automatically run this class, and to call thex
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static Joystick rightStick = new Joystick(1);
  private static Joystick leftStick = new Joystick(2);

  private static DifferentialDrive myDrive;
  double velocity;
  DigitalInput frontLeft, frontMiddle, frontRight;

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    CANSparkMax m_Left = new CANSparkMax(RobotMap.leftMotorCANDevice, CANSparkMaxLowLevel.MotorType.kBrushless);
    CANSparkMax m_Right = new CANSparkMax(RobotMap.rightMotorCANDevice, CANSparkMaxLowLevel.MotorType.kBrushless);
    myDrive = new DifferentialDrive(m_Left, m_Right);
    velocity = 0.5;

  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    myDrive.tankDrive(velocity, velocity);
    boolean frontLeftOnLine = frontLeft.get();
    boolean frontMiddleOnLine = frontMiddle.get();
    boolean frontRightOnLine = frontRight.get();
    if(!frontLeftOnLine && frontRightOnLine){
      myDrive.tankDrive(velocity, velocity - 0.1);
    }
    if(frontLeftOnLine && !frontRightOnLine){
      myDrive.tankDrive(velocity - 0.1, velocity);
    }

    
  }

  public void teleopInit(){

  }
}
