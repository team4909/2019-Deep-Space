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
import frc.team4909.robot.subsystems.climber.ClimberSubsystem;
import frc.team4909.robot.subsystems.drivetrain.DriveTrainSubsystem;
import frc.team4909.robot.subsystems.drivetrain.commands.InvertDriveDirection;
import frc.team4909.robot.subsystems.drivetrain.commands.TogglePreciseMode;
import frc.team4909.robot.subsystems.elevator.ElevatorSubsystem;
import frc.team4909.robot.subsystems.elevatorarm.ElevatorArmSubsystem;
import frc.team4909.robot.subsystems.elevatorarm.commands.SetWristAngle;
import frc.team4909.robot.subsystems.intake.IntakeSubsystem;
import frc.team4909.robot.subsystems.intake.commands.CargoIntakeIn;
import frc.team4909.robot.subsystems.intake.commands.CargoIntakeOut;
import frc.team4909.robot.subsystems.intake.commands.HatchPanelIntakeOpen;

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
    manipulatorGamepad.buttonHeld(BionicF310.LY, 0.2, new Command()
    {
      // public Command() {
      //   requires(Robot.elevatorSubsystem);
      // }
      protected void initialize() {
        requires(Robot.elevatorSubsystem);
        Robot.elevatorSubsystem.configReverseLimitSwitch(false);
      }
      protected void execute() {
        double speed = manipulatorGamepad.getThresholdAxis(BionicF310.LY);
        Robot.elevatorSubsystem.setSpeed(-1 * speed * RobotConstants.elevatorSpeedMultiplier);
        Robot.elevatorSubsystem.updateHoldingPos();
      }
      protected boolean isFinished() {
        return false;
      }
      protected void end() {
        Robot.elevatorSubsystem.setSpeed(0);
        Robot.elevatorSubsystem.updateHoldingPos();
      }
    });

    //move just the stilts
    climberGamepad.buttonHeld(BionicF310.RY, 0.2, new Command() {
      // public Command() {
      //   requires(Robot.climberSubsystem);
      // }
      protected void initialize() {
        requires(Robot.climberSubsystem);
      }
      protected void execute() {
        double speed = climberGamepad.getThresholdAxis(BionicF310.RY);
        climberSubsystem.setStiltsClimbSpeed(-1 * speed * RobotConstants.climbSpeedMultiplier);
        climberSubsystem.updateHoldingPos();
      }
      protected boolean isFinished() {
        return false;
      }
      protected void end() {
        climberSubsystem.updateHoldingPos();
        climberSubsystem.setStiltsClimbSpeed(0);
      }
    });

    //move just the elevator
    climberGamepad.buttonHeld(BionicF310.LY, 0.2, new Command() {
      // public Command() {
      //   requires(Robot.climberSubsystem);
      // }
      protected void initialize() {
        requires(Robot.climberSubsystem);
      }
      protected void execute() {
        double speed = climberGamepad.getThresholdAxis(BionicF310.LY);
        elevatorSubsystem.setSpeed(-1 * speed * RobotConstants.elevatorSpeedMultiplier);
        elevatorSubsystem.updateHoldingPos();
      }
      protected boolean isFinished() {
        return false;
      }
      protected void end() {
        elevatorSubsystem.updateHoldingPos();
        elevatorSubsystem.setSpeed(0);
      }
    });

    // move both the elevator and stilts up
    climberGamepad.buttonHeld(BionicF310.RT, 0.2, new Command() {
      int startStiltPos;
      int startElevatorPos;
      // public Command() {
      //   requires(Robot.climberSubsystem);
      //   requires(Robot.elevatorSubsystem);
      // }
      protected void initialize() {
        requires(Robot.climberSubsystem);
        requires(Robot.elevatorSubsystem);

        startStiltPos = climberSubsystem.getPosition();
        startElevatorPos = elevatorSubsystem.getPosition();
      }
      protected void execute() {
        SmartDashboard.putString("Climb", "Move Both Up Climber Gamepad");

        double speed = climberGamepad.getThresholdAxis(BionicF310.RT);
        speed *= RobotConstants.climbBothSpeedMultiplier;

        climberSubsystem.setStiltsClimbSpeed(speed * 2); //*2 because trigger only goes to .3

        int stiltDelta = Math.abs(startStiltPos - climberSubsystem.getPosition());

        // Elevator Drum is 1.3" Diameter, C = PI * D = Math.PI * 1.3
        // Stilts pinion gear Pitch Diameter is 1.1" which is the circumference
        int elevDelta = (int) (stiltDelta * (1.1/1.3));

        // the sign here is the only difference between up and down
        elevatorSubsystem.setPosition(startElevatorPos + elevDelta);
      }
      protected boolean isFinished() {
        return false;
      }
      protected void end() {
        elevatorSubsystem.setSpeed(0);
        climberSubsystem.setSpeed(0);
      }
    });

    //move both the elevator and stilts down
    climberGamepad.buttonHeld(BionicF310.LT, 0.2, new Command() {
      int startStiltPos;
      int startElevatorPos;
      // public Command() {
      //   requires(Robot.climberSubsystem);
      //   requires(Robot.elevatorSubsystem);
      // }
      protected void initialize() {
        requires(Robot.climberSubsystem);
        requires(Robot.elevatorSubsystem);

        startStiltPos = climberSubsystem.getPosition();
        startElevatorPos = elevatorSubsystem.getPosition();
      }
      protected void execute() {
        SmartDashboard.putString("Climb", "Move Both Down Climber Gamepad");

        double speed = climberGamepad.getThresholdAxis(BionicF310.RT);
        speed *= RobotConstants.climbBothSpeedMultiplier;

        climberSubsystem.setStiltsClimbSpeed(speed * 2); //*2 because trigger only goes to .3

        int stiltDelta = Math.abs(startStiltPos - climberSubsystem.getPosition());

        // Elevator Drum is 1.3" Diameter, C = PI * D = Math.PI * 1.3
        // Stilts pinion gear Pitch Diameter is 1.1" which is the circumference
        int elevDelta = (int) (stiltDelta * (1.1/1.3));

        // the sign here is the only difference between up and down
        elevatorSubsystem.setPosition(startElevatorPos - elevDelta);
      }
      protected boolean isFinished() {
        return false;
      }
      protected void end() {
        elevatorSubsystem.setSpeed(0);
        climberSubsystem.setSpeed(0);
      }
    });
    driverGamepad.buttonHeld(BionicF310.RT, 0.2, new Command() {
      // public Command() {
      //   requires(stiltWheelSubsystem);
      // }
      @Override
      protected void initialize() {
        requires(stiltWheelSubsystem);
      }
      @Override
      protected void execute() {
        double speed = -Robot.driverGamepad.getThresholdAxis(BionicF310.RT);
        stiltWheelSubsystem.setSpeed(-1 * speed * RobotConstants.climberDriveSpeedManual);
      }
      @Override
      protected boolean isFinished() {
        return false;
      }
      @Override
      protected void end() {
        stiltWheelSubsystem.setSpeed(0);
      }
    });
    driverGamepad.buttonHeld(BionicF310.LT, 0.2, new Command() {
      // public Command() {
      //   requires(stiltWheelSubsystem);
      // }
      @Override
      protected void initialize() {
      }
      @Override
      protected void execute() {
        double speed = Robot.driverGamepad.getThresholdAxis(BionicF310.LT) * RobotConstants.climberDriveSpeedManual;
        stiltWheelSubsystem.setSpeed(speed * RobotConstants.climberDriveSpeedManual);
      }
      @Override
      protected boolean isFinished() {
        return false;
      }
      @Override
      protected void end() {
        stiltWheelSubsystem.setSpeed(0);
      }
    });

    /* Drivetrain */
    driverGamepad.buttonPressed(BionicF310.RB, new InvertDriveDirection());
    driverGamepad.buttonPressed(BionicF310.X, new TogglePreciseMode());

    /* Wrist Setpoints */
    manipulatorGamepad.buttonPressed(BionicF310.A, new SetWristAngle(RobotConstants.wristSetpointCargoIn));
    manipulatorGamepad.buttonPressed(BionicF310.B, new SetWristAngle(RobotConstants.wristSetpointHatch));
    manipulatorGamepad.buttonPressed(BionicF310.Y, new SetWristAngle(RobotConstants.wristSetpointCargoScore));
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
    SmartDashboard.putNumber("LT Climber Rise", climberGamepad.getThresholdAxis(BionicF310.LT));
    SmartDashboard.putNumber("LY Climber Elev", climberGamepad.getThresholdAxis(BionicF310.LY));
    SmartDashboard.putNumber("RY Climber Stilts", climberGamepad.getThresholdAxis(BionicF310.RY));
    SmartDashboard.putNumber("RT Climber Sink", climberGamepad.getThresholdAxis(BionicF310.RT));
    SmartDashboard.putNumber("LY Elevator", manipulatorGamepad.getThresholdAxis(BionicF310.LY));
    
    // process();
    Scheduler.getInstance().run();
  }

  public void autonomousInit() {
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
