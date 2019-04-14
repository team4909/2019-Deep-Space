package frc.team4909.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DriverStation;
import frc.team4909.robot.operator.controllers.BionicF310;
import frc.team4909.robot.sensors.LidarLitePWM;
import frc.team4909.robot.sensors.Stream;
import frc.team4909.robot.subsystems.StiltWheel.StiltWheelSubsystem;
import frc.team4909.robot.subsystems.StiltWheel.commands.MoveStiltWheels;
import frc.team4909.robot.subsystems.climber.ClimberSubsystem;
import frc.team4909.robot.subsystems.climber.commands.*;
import frc.team4909.robot.subsystems.drivetrain.DriveTrainSubsystem;
import frc.team4909.robot.subsystems.drivetrain.commands.InvertDriveDirection;
import frc.team4909.robot.subsystems.drivetrain.commands.StayOnHab;
import frc.team4909.robot.subsystems.drivetrain.commands.TogglePreciseMode;
import frc.team4909.robot.subsystems.elevator.ElevatorSubsystem;
import frc.team4909.robot.subsystems.elevator.commands.*;
import frc.team4909.robot.subsystems.elevatorarm.ElevatorArmSubsystem;
import frc.team4909.robot.subsystems.elevatorarm.commands.*;
import frc.team4909.robot.subsystems.intake.IntakeSubsystem;
import frc.team4909.robot.subsystems.intake.commands.*;
import frc.team4909.robot.SetLights;


/* 
CONTROLS

Port 0: Driver Gamepad
  LT: Drive Stilts Back
  RT: Drive Stilts Forward
  RB: Invert Drive
  LY & RY: Arcade Drive
  Push on R: Reduce Turn Speed

Port 1: Manipulator Gamepad
  LT: Cargo In
  RT: Cargo Out
  RB: Hatch Panel Open
  A: Elevator Arm Setpoint- Intake
  B: Elevator Arm Setpoint- Hatch Placement
  Y: Elevator Arm Settpoint- Outtake
  LY: Elevator Up/Down
  RY: Arm Up/Down

Port 2: Climber Gamepad
  POV UP: Elevator & Stilts Up
  POV DOWN: Elevator & Stilts Down
  LY: Elevator Up/Down
  RY: Stilts Up/Down
  Start: Move Hook In Position
  Back: Move Hook Out Position

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
  public static StiltWheelSubsystem stiltWheelSubsystem;
  public static Compressor c;

  // Sensors
  public static LidarLitePWM lidar;
  public static Vision vision;

  /**
   * map a number from one range to another
   * 
   * @param {num} value the value to be mapped
   * @param {num} old_min the minimum of value
   * @param {num} old_max the maximum of value
   * @param {num} new_min the new minimum value
   * @param {num} new_max the new maximum value
   * @return {num} the value remaped on the range [new_min new_max]
   */
  public static double map(double value, double old_min, double old_max, double new_min, double new_max) {
    return (value - old_min) / (old_max - old_min) * (new_max - new_min) + new_min;
  }

  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */

  @Override
  public void robotInit() {

    // Cameras (subsystem)
    stream = new Stream();
    // CameraServer.getInstance().startAutomaticCapture();
    stream.streamCamera();
    // grip = new GripPipeline();

    // Compressor
    c = new Compressor(0); // Initialize Compressor
    c.setClosedLoopControl(true); // Start Compressor in Closed Loop Control

    // lights

    // Subsystems
    powerDistributionPanel = new PowerDistributionPanel();
    drivetrainSubsystem = new DriveTrainSubsystem();
    intakeSubsystem = new IntakeSubsystem();
    elevatorSubsystem = new ElevatorSubsystem();
    elevatorArmSubsystem = new ElevatorArmSubsystem();
    climberSubsystem = new ClimberSubsystem();
    stiltWheelSubsystem = new StiltWheelSubsystem();

    // Sensors
    lidar = new LidarLitePWM(RobotMap.lidarPort);

    //Limelight
    vision = new Vision();
    vision.setLights(1);

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
        RobotConstants.climberGamepadDeadzone, // Deadzone
        RobotConstants.climberGamepadSensitivity // Gamepad sensitivity
    );


    /* Intake */
    manipulatorGamepad.buttonHeld(BionicF310.RT, 0.2, new CargoIntakeOut());
    manipulatorGamepad.buttonHeld(BionicF310.LT, 0.2, new CargoIntakeIn());
    manipulatorGamepad.buttonHeld(BionicF310.RB, new HatchPanelIntakeOpen());

    /* Stilts */

    // climberGamepad.buttonHeld(BionicF310.RB, new StiltsUpOnly());
    // climberGamepad.buttonHeld(BionicF310.LB, new StiltsDownOnly());



    //?????????????????????????????????????????????????????????????????????????????

    //@note: 0.2 is probably too big!

    // ?????????????????????????????????????????????????????????????????????????????



    /* Elevator */
    manipulatorGamepad.buttonHeld(BionicF310.LY, RobotConstants.manipulatorGamepadDeadzone, new MoveElevatorOnly(manipulatorGamepad, BionicF310.LY));

    //move just the stilts
    climberGamepad.buttonHeld(BionicF310.RY, RobotConstants.climberGamepadDeadzone, new MoveStiltsOnly());

    //move just the elevator
    climberGamepad.buttonHeld(BionicF310.LY, RobotConstants.climberGamepadDeadzone, new MoveElevatorOnlyClimb(climberGamepad, BionicF310.LY));

    // move both the elevator and stilts up
    climberGamepad.buttonHeld(BionicF310.RT, 0.05, new MoveElevAndStitls(true));

    //move both the elevator and stilts down
    climberGamepad.buttonHeld(BionicF310.LT, 0.05, new MoveElevAndStitls(false));

    climberGamepad.buttonHeld(BionicF310.RB, new SetWristSpeed(-RobotConstants.elevatorArmSpeed));
    climberGamepad.buttonHeld(BionicF310.LB, new SetWristSpeed(RobotConstants.elevatorArmSpeed));


    
    /* Drivetrain && Stilt Wheels*/

    // drive stilt wheels
    driverGamepad.buttonHeld(BionicF310.RT, 0.05, new MoveStiltWheels(true));
    driverGamepad.buttonHeld(BionicF310.LT, 0.05, new MoveStiltWheels(false));

    // Drivetrain
    driverGamepad.buttonPressed(BionicF310.RB, new InvertDriveDirection());
    driverGamepad.buttonPressed(BionicF310.X, new TogglePreciseMode());
    driverGamepad.buttonHeld(BionicF310.A, new StayOnHab());
    driverGamepad.buttonToggled(BionicF310.B, new SetLights());

    /* Wrist Setpoints */
    manipulatorGamepad.buttonPressed(BionicF310.A, new SetWristAngle(RobotConstants.wristSetpointCargoIn));
    manipulatorGamepad.buttonPressed(BionicF310.B, new SetWristAngle(RobotConstants.wristSetpointHatch));
    manipulatorGamepad.buttonPressed(BionicF310.Y, new SetWristAngle(RobotConstants.wristSetpointCargoScore));
    
    /* Climb Hook */
    climberGamepad.buttonHeld(BionicF310.Start, new SetHookSpeed(RobotConstants.hookMoveSpeed));
    climberGamepad.buttonHeld(BionicF310.Back, new SetHookSpeed(-RobotConstants.hookMoveSpeed));
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
  public void robotPeriodic() {
    SmartDashboard.putNumber("Time Remaining", DriverStation.getInstance().getMatchTime());//Useful Method to get match data.
    // SmartDashboard.putNumber("LT Climber Rise", climberGamepad.getThresholdAxis(BionicF310.LT));
    // SmartDashboard.putNumber("LY Climber Elev", climberGamepad.getThresholdAxis(BionicF310.LY));
    // SmartDashboard.putNumber("RY Climber Stilts", climberGamepad.getThresholdAxis(BionicF310.RY));
    // SmartDashboard.putNumber("RT Climber Sink", climberGamepad.getThresholdAxis(BionicF310.RT));
    // SmartDashboard.putNumber("LY Elevator", manipulatorGamepad.getThresholdAxis(BionicF310.LY));
    SmartDashboard.putNumber("Loop Period", getPeriod());
    SmartDashboard.putNumber("Lidar distance", lidar.getDistance());

    
    // process();
    Scheduler.getInstance().run();
  }

  public void autonomousInit() {
    new MoveUpToLimit().start();
    // elevatorSubsystem.setSensorZero();
    // elevatorArmSubsystem.setSensorZero();
    // climberSubsystem.setSensorZero();
  }

  /**
   * This function is called periodically during autonomous.
   */
  public void autonomousPeriodic() {
  }

  /**
   * This function is called periodically during operator control.
   */

  public void teleopInit() {
    new MoveUpToLimit().start();
    // Reset elevator encoder
    
  }
  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run(); 

    Robot.elevatorSubsystem.updateHoldingPos();
    Robot.elevatorArmSubsystem.holdingPosition = Robot.elevatorArmSubsystem.getPosition();
    Robot.climberSubsystem.updateHoldingPos();


  }

  public void teleopPeriodic() {   


  }

  /**
   * This function is called periodically during test mode.
   */
  public void testPeriodic() {
  }
}
