package frc.team4909.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import frc.team4909.robot.subsystems.drivetrain.Linefollow;
import frc.team4909.robot.subsystems.intake.CargoIntakeIn;
import frc.team4909.robot.subsystems.intake.CargoIntakeOut;
import frc.team4909.robot.subsystems.intake.HatchPanelIntakeOpen;
import frc.team4909.robot.subsystems.intake.HatchPanelIntakeClose;
import frc.team4909.robot.operator.controllers.BionicF310;
import frc.team4909.robot.subsystems.drivetrain.DriveTrainSubsystem;
import frc.team4909.robot.subsystems.intake.IntakeSubsystem;


public class Robot extends TimedRobot {
  // Operator Input
  public static BionicF310 driverGamepad;

  // Subsystems
  public static DriveTrainSubsystem drivetrainSubsystem;
  public static IntakeSubsystem intakeSubsystem;

  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {
    // Subsystems
    drivetrainSubsystem = new DriveTrainSubsystem();
    intakeSubsystem = new IntakeSubsystem();

    // Operator Input
    driverGamepad = new BionicF310(RobotConstants.driverGamepadPort, //Port
                                   RobotConstants.driverGamepadDeadzone, //Deadzone
                                   RobotConstants.driverGamepadSensitivity //Gamepad sensitivity
                                   );
    driverGamepad.buttonPressed(BionicF310.A, new Linefollow());
    driverGamepad.buttonPressed(BionicF310.X, new CargoIntakeIn());
    driverGamepad.buttonPressed(BionicF310.Y, new CargoIntakeOut());
    driverGamepad.buttonPressed(BionicF310.LB, new HatchPanelIntakeOpen());
    driverGamepad.buttonPressed(BionicF310.RB, new HatchPanelIntakeClose());
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
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
