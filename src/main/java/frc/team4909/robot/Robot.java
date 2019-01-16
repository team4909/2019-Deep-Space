package frc.team4909.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.I2C;

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

  I2C Lidar;
  byte[] byte1;
  int count = 2;

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

    I2C Lidar = new I2C(Port.kOnboard, 0x62);
    byte1 = new byte[count];
    Lidar.write(0x00, 0x04);
    boolean isNotZero = true;
    while(isNotZero){
      Lidar.read(0x04, 1, byte1);
      if((byte1[0] & 0x01) == 0){
        isNotZero = false;
      }
    }
    Lidar.read(0x8f, 2, byte1);
    long lidarDist = (byte1[0]*256)+byte1[1]; //distance of each beam in centimeters.
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

    Lidar.write(0x04, 0x00);
    Lidar.read(0x01, count,byte1);
    

    
  }

  public void teleopInit(){

  }
}
