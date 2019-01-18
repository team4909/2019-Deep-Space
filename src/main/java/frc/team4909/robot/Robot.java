package frc.team4909.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.buttons.Button;
import frc.team4909.robot.operator.controllers.BionicF310;
//import frc.team4909.robot.subsystems.drivetrain.BionicDrive;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.I2C;
import frc.team4909.robot.LIDAR;


/**
 * The VM is configured to automatically run this class, and to call thex
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  DigitalInput frontLeftSensor, frontMiddleSensor, frontRightSensor;

  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  private static BionicF310 driverGamepad;
  //public static BionicDrive drivetrain;
  public static DifferentialDrive myDrive;
  int velocity;
  I2C Lidar;
  byte[] byte1;
  int count;
  
  LIDAR lidar1;
  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
    driverGamepad = new BionicF310(0, 0, 0.6);
    // drivetrain = new BionicDrive(
    //             new CANSparkMax(
    //                     1, false,
    //                     FeedbackDevice.QuadEncoder, true,
    //                     1, 0.00001, 0,
    //                     1
    //             ),
    //             new CANSparkMax(
    //                     2, true,
    //                     FeedbackDevice.QuadEncoder, true,
    //                     1, 0.00001, 0,
    //                     4
    //             ),
    //             driverGamepad, BionicF310.LY, -1.0, 0.10,
    //             driverGamepad, BionicF310.RX, -0.6, 0.10, //rotationMult: -.75
    //             new DrivetrainConfig(
    //                     25, 0.5, 360,
    //                     21.76, 41.88, 654.49,
    //                     3, 2.74
    //             ));
    // CANSparkMax m_Left = new CANSparkMax(1, MotorType.kBrushed);

    // CANSparkMax m_Right = new CANSparkMax(2, MotorType.kBrushed);
    // frontLeftSensor = new DigitalInput(0);
    // frontMiddleSensor = new DigitalInput(1);
    // frontRightSensor = new DigitalInput(3);

    // count = 2;    
    // myDrive = new DifferentialDrive(m_Left, m_Right);
    // Lidar = new I2C(Port.kOnboard, 0x62);
    // byte1 = new byte[count];
     
    lidar1 = new LIDAR();
     

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
    double value;
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
    // myDrive.tankDrive(velocity, velocity);
    // boolean frontLeftOnLine = frontLeftSensor.get();
    // boolean frontMiddleOnLine = frontMiddleSensor.get();
    // boolean frontRightOnLine = frontRightSensor.get();
    // if(!frontLeftOnLine && frontRightOnLine){
    //   myDrive.tankDrive(velocity, velocity - 0.1);
    // }
    // if(frontLeftOnLine && !frontRightOnLine){
    //   myDrive.tankDrive(velocity - 0.1, velocity);
    // }

    
     
    // Lidar.write(0x00, 0x04);
    // boolean isNotZero = true;
    // while(isNotZero){
    //   Lidar.read(0x04, 1, byte1);
    //   System.out.println((byte1[0] & 0x01) + "   In while loop");
    //   if((byte1[0] & 0x01) == 0){
    //     isNotZero = false;
    //   }
    
    // Lidar.read(0x8f, 2, byte1);
    // long lidarDist = byte1[0]*256 + byte1[1]; //distance of each beam in centimeters.
    // System.out.println(lidarDist);
    // Lidar.read(0x96, 2, byte1);
    // long lidarDist = byte1[0]*256 + byte1[1]; //distance of each beam in centimeters.
    // System.out.println(byte1[0] + "  " + byte1[1]);
  System.out.println(lidar1.getDistance());
    
    }


  @Override
  public void testPeriodic() {

  }
    /**
   * This function is called periodically during test mode.
   */
}
