package frc.team4909.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.team4909.robot.subsystems.drivetrain.Linefollow;
import frc.team4909.robot.subsystems.intake.CargoIntakeIn;
import frc.team4909.robot.subsystems.intake.CargoIntakeOut;
import frc.team4909.robot.subsystems.intake.HatchPanelIntakeOpen;
import frc.team4909.robot.subsystems.intake.HatchPanelIntakeClose;
import frc.team4909.robot.operator.controllers.BionicF310;
import frc.team4909.robot.sensors.Stream;
import frc.team4909.robot.subsystems.drivetrain.DriveTrainSubsystem;
import frc.team4909.robot.subsystems.drivetrain.commands.InvertDriveDirection;
import frc.team4909.robot.subsystems.intake.IntakeSubsystem;
import frc.team4909.robot.subsystems.elevator.ElevatorSubsystem;
import frc.team4909.robot.subsystems.elevatorarm.ElevatorArmSubsytem;
import frc.team4909.robot.sensors.LidarLitePWM;
public class Robot extends TimedRobot {

  Stream stream = new Stream();
  // Operator Input
  public static BionicF310 driverGamepad;
  public static BionicF310 manipulatorGamepad;

  // Subsystems
  public static PowerDistributionPanel powerDistributionPanel;
  public static DriveTrainSubsystem drivetrainSubsystem;
  public static IntakeSubsystem intakeSubsystem;
  public static ElevatorSubsystem elevatorSubsystem;
  public static ElevatorArmSubsytem elevatorArmSubsystem;
  public static Compressor c;


  // Sensors
  public static LidarLitePWM lidar;

  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {

    c = new Compressor(0);        //Initialize Compressor
    c.setClosedLoopControl(true); // Start Compressor in Closed Loop Control


    // stream.streamCamera();
    // GripPipeline grip = new GripPipeline();

    // Subsystems
    powerDistributionPanel = new PowerDistributionPanel();
    drivetrainSubsystem = new DriveTrainSubsystem();
    intakeSubsystem = new IntakeSubsystem();
    elevatorSubsystem = new ElevatorSubsystem();
    elevatorArmSubsystem = new ElevatorArmSubsytem();

    // Sensors
    lidar = new LidarLitePWM(RobotMap.lidarPort);

    // Operator Input

    driverGamepad = new BionicF310(RobotConstants.driverGamepadPort, // Port
                                   RobotConstants.driverGamepadDeadzone, // Deadzone
                                   RobotConstants.driverGamepadSensitivity // Gamepad sensitivity
    );

    manipulatorGamepad = new BionicF310(RobotConstants.manipulatorGamepadPort, // Port
                                         RobotConstants.manipulatorGamepadDeadzone, // Deadzone 
                                         RobotConstants.manipulatorGamepadSensitivity // Gamepad sensitivity
    );
    manipulatorGamepad.buttonHeld(BionicF310.RT, 0.2, new CargoIntakeIn());
    manipulatorGamepad.buttonHeld(BionicF310.RB, new CargoIntakeOut());
    manipulatorGamepad.buttonHeld(BionicF310.LT, 0.2, new HatchPanelIntakeOpen());

    driverGamepad.buttonPressed(BionicF310.A, new Linefollow());
    driverGamepad.buttonPressed(BionicF310.RT, 0.2, new InvertDriveDirection());
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

    // process();
    Scheduler.getInstance().run();
  }

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
    System.out.println(lidar.getDistance()); // Remove for competition (necessary only for testing)

  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}