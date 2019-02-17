package frc.team4909.robot.subsystems.climber;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.team4909.robot.RobotMap;
import frc.team4909.robot.RobotConstants;
import frc.team4909.robot.subsystems.drivetrain.commands.Drive;
import frc.team4909.robot.subsystems.climber.commands.ExtendStilts;
import frc.team4909.robot.subsystems.climber.commands.RetractStilts;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team4909.robot.Robot;
import frc.team4909.robot.operator.controllers.BionicF310;

public class ClimberSubsystem extends Subsystem {
    WPI_TalonSRX climberLiftSRX;
    WPI_VictorSPX climberDriveSPX;

    public ClimberSubsystem() {
        climberLiftSRX = new WPI_TalonSRX(RobotMap.climberSRXID); // Climber lift
        climberDriveSPX = new WPI_VictorSPX(RobotMap.climberSPXID); // Climber drive

        climberLiftSRX.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute);

        /* PID yet to be calibrated */
        climberLiftSRX.config_kP(1, 0.6, 0);
        climberLiftSRX.config_kI(1, 0);
        climberLiftSRX.config_kD(1, 0);
    }

    public void extendStilts() {
        climberLiftSRX.set(RobotConstants.climberStiltSpeed);
    }

    public void retractStilts() {
        climberLiftSRX.set(-RobotConstants.climberStiltSpeed);
    }

    public void driveStiltsForward() {
        climberDriveSPX.set(-RobotConstants.climberDriveSpeed);
    }

    public void driveStiltsBack() {
        climberDriveSPX.set(RobotConstants.climberDriveSpeed);
    }

    public void stopExtend() {
        climberLiftSRX.set(0);
    }

    protected void initDefaultCommand() {
    }
}