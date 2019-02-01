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
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.team4909.robot.commands.Linefollow;
import frc.team4909.robot.operator.controllers.BionicF310;
import frc.team4909.robot.operator.generic.BionicAxis;
import frc.team4909.robot.operator.generic.BionicJoystick;
import frc.team4909.robot.subsystems.drivetrain.DriveTrainSubsystem;
import frc.team4909.robot.subsystems.intake.IntakeSubsystem;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.SpeedControllerGroup;

//import frc.team4909.robot.subsystems.drivetrain.DriveTrainSubsystem;

// import com.revrobotics.CANSparkMax;
// import com.revrobotics.CANSparkMaxLowLevel.MotorType;

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
  public static DriveTrainSubsystem drivetrainsub;

  public static IntakeSubsystem intakeSubsystem;

  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  public static BionicF310 driverGamepad;
  private static Linefollow linefollow;

  // LIDAR lidar1;
  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
    driverGamepad = new BionicF310(0, 0, 0.6);
    drivetrainsub = new DriveTrainSubsystem();
    intakeSubsystem = new IntakeSubsystem();
    driverGamepad.buttonPressed(BionicF310.A, new Linefollow());
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for
   * items like diagnostics that you want ran during disabled, autonomous,
   * teleoperated and test.
   *
   * <p>
   * This runs after the mode specific periodic functions, but before LiveWindow
   * and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {

  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable chooser
   * code works with the Java SmartDashboard. If you prefer the LabVIEW Dashboard,
   * remove all of the chooser code and uncomment the getString line to get the
   * auto name from the text box below the Gyro
   *
   * <p>
   * You can add additional auto modes by adding additional comparisons to the
   * switch structure below with additional strings. If using the SendableChooser
   * make sure to add them to the chooser code above as well.
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

    drivetrainsub.tankDrive(Robot.driverGamepad.getRawAxis(1), Robot.driverGamepad.getRawAxis(5));
    }


  // Lidar.write(0x04, 0x00);
  // Lidar.read(0x01, count,byte1);

  // Lidar.read(0x8f, 2, byte1);
  // long lidarDist = byte1[0]*256 + byte1[1]; //distance of each beam in
  // centimeters.
  // System.out.println(lidarDist);
  // Lidar.read(0x96, 2, byte1);
  // long lidarDist = byte1[0]*256 + byte1[1]; //distance of each beam in
  // centimeters.
  // System.out.println(byte1[0] + " " + byte1[1]);
  // System.out.println(lidar1.getDistance());

  @Override
  public void testPeriodic() {

  }
  /**
   * This function is called periodically during test mode.
   */
}
