package frc.team4909.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.buttons.POVButton;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team4909.robot.operator.controllers.BionicF310;
import frc.team4909.robot.operator.controllers.FlightStick;
import frc.team4909.robot.operator.generic.BionicPOV;
import frc.team4909.robot.sensors.LidarLitePWM;
import frc.team4909.robot.sensors.Stream;
import frc.team4909.robot.setpoints.HatchLow;
import frc.team4909.robot.setpoints.HatchMiddle;
import frc.team4909.robot.subsystems.climber.ClimberSubsystem;
import frc.team4909.robot.subsystems.climber.commands.BothLiftUp;
import frc.team4909.robot.subsystems.climber.commands.DriveStiltsBack;
import frc.team4909.robot.subsystems.climber.commands.DriveStiltsForward;
import frc.team4909.robot.subsystems.climber.commands.StiltsDownOnly;
import frc.team4909.robot.subsystems.climber.commands.StiltsUpOnly;
import frc.team4909.robot.subsystems.drivetrain.DriveTrainSubsystem;
import frc.team4909.robot.subsystems.drivetrain.Linefollow;
import frc.team4909.robot.subsystems.drivetrain.commands.InvertDriveDirection;
import frc.team4909.robot.subsystems.elevator.ElevatorSubsystem;
import frc.team4909.robot.subsystems.elevator.commands.SetElevatorPosition;
import frc.team4909.robot.subsystems.elevatorarm.ElevatorArmSubsystem;
import frc.team4909.robot.subsystems.intake.IntakeSubsystem;
import frc.team4909.robot.subsystems.intake.commands.CargoIntakeIn;
import frc.team4909.robot.subsystems.intake.commands.CargoIntakeOut;
import frc.team4909.robot.subsystems.intake.commands.HatchPanelIntakeOpen;

import frc.team4909.robot.subsystems.climber.commands.ZeroStilts;
import frc.team4909.robot.subsystems.elevator.commands.ZeroElevator;

/*  @TODO: Finish typing controls
CONTROLS

Port 0: Driver Gamepad

Port 1: Manipulator Gamepad

Port 2: Climber Gamepad

*/

public class Robot extends TimedRobot {

  // Camera
  public static Stream stream;
  // public static GripPipeline grip;
  // Operator Input
  public static BionicF310 driverGamepad;
  public static BionicF310 manipulatorGamepad;
  public static BionicF310 climberGamepad;

  // Subsystems
  public static PowerDistributionPanel powerDistributionPanel;
  public static DriveTrainSubsystem drivetrainSubsystem;
  public static IntakeSubsystem intakeSubsystem;
  public static ElevatorSubsystem elevatorSubsystem;
  public static ElevatorArmSubsystem elevatorArmSubsystem;
  public static ClimberSubsystem climberSubsystem;
  public static Compressor c;

  // Sensors
  public static LidarLitePWM lidar;

  // SmartDashboard Buttons
  public boolean CargoIntake;
  public boolean CargoOuttake;

  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */

  @Override
  public void robotInit() {

    // Cameras (subsystem)
    stream = new Stream();
    // // CameraServer.getInstance().startAutomaticCapture();
    stream.streamCamera();
    // grip = new GripPipeline();

    // Compressor
    c = new Compressor(0); // Initialize Compressor
    c.setClosedLoopControl(true); // Start Compressor in Closed Loop Control

    // Subsystems
    powerDistributionPanel = new PowerDistributionPanel();
    drivetrainSubsystem = new DriveTrainSubsystem();
    intakeSubsystem = new IntakeSubsystem();
    elevatorSubsystem = new ElevatorSubsystem();
    elevatorArmSubsystem = new ElevatorArmSubsystem();
    climberSubsystem = new ClimberSubsystem();

    // Sensors
    lidar = new LidarLitePWM(RobotMap.lidarPort);

    // Operator Input
    driverGamepad = new BionicF310(RobotMap.driverGamepadPort, // Port
        RobotConstants.driverGamepadDeadzone, // Deadzone
        RobotConstants.driverGamepadSensitivity // Gamepad sensitivity
    );

    manipulatorGamepad = new BionicF310(RobotMap.manipulatorGamepadPort, // Port
        RobotConstants.manipulatorGamepadDeadzone, // Deadzone
        RobotConstants.manipulatorGamepadSensitivity // Gamepad sensitivity
    );

    climberGamepad = new BionicF310(RobotMap.climberGamepadPort, // Port
        RobotConstants.manipulatorGamepadDeadzone, // Deadzone
        RobotConstants.manipulatorGamepadSensitivity // Gamepad sensitivity
    );


    /* Intake */
    manipulatorGamepad.buttonHeld(BionicF310.RT, 0.2, new CargoIntakeOut());
    manipulatorGamepad.buttonHeld(BionicF310.LT, 0.2, new CargoIntakeIn());
    manipulatorGamepad.buttonHeld(BionicF310.RB, new HatchPanelIntakeOpen());

    /* Climber */
    driverGamepad.buttonHeld(BionicF310.LB, new DriveStiltsBack());
    driverGamepad.buttonHeld(BionicF310.RB, new DriveStiltsForward());
    climberGamepad.buttonHeld(BionicF310.RB, new StiltsUpOnly());
    climberGamepad.buttonHeld(BionicF310.LB, new StiltsDownOnly());
    //climberGamepad.buttonHeld(BionicPOV.UP, 0.2, new BothLiftUp()); @TODO: NEED TO ADD
    //climberGamepad.buttonHeld(BionicPOV.DOWN, 0.2, new BothLiftDown()); @TODO: NEED TO ADD


    /* Elevator Setpoints */
    manipulatorGamepad.buttonPressed(BionicF310.A, new HatchMiddle());
    manipulatorGamepad.buttonPressed(BionicF310.B, new HatchLow());

    /* Sensors/Misc. */
    driverGamepad.buttonPressed(BionicF310.RB, new InvertDriveDirection());
    // driverGamepad.buttonPressed(BionicF310.B, new Linefollow());
    // manipulatorGamepad.buttonPressed(BionicF310.X, new ToggleCamera());

    /* Arm Setpoints */
    manipulatorGamepad.buttonPressed(BionicF310.A, new CargoIntakeOnly());
    manipulatorGamepad.buttonPressed(BionicF310.B, new HatchOnly());
    manipulatorGamepad.buttonPressed(BionicF310.Y, new CargoOutOnly());


    SmartDashboard.putData(new ZeroElevator());
    SmartDashboard.putData(new ZeroStilts());
  }

  /**
   * '' This function is called every robot packet, no matter the mode. Use this
   * for items like diagnostics that you want ran during disabled, autonomous,
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
  public void teleopInit() {
    // Reset elevator encoder
    
  }
@Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
    elevatorSubsystem.reset();
    climberSubsystem.reset();
  }

  @Override
  public void teleopPeriodic() {
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
