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
import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team4909.robot.Robot;
import frc.team4909.robot.operator.controllers.BionicF310;

public class ClimberSubsystem extends Subsystem {
    WPI_TalonSRX climberLiftSRX;
    WPI_VictorSPX climberDriveSPX;
    int driveDirection;

    public ClimberSubsystem() {
        climberLiftSRX = new WPI_TalonSRX(RobotMap.climberSRXID); // Climber Lift
        climberDriveSPX = new WPI_VictorSPX(RobotMap.climberSPXID); // Climber Drive

        climberLiftSRX.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute);

        climberLiftSRX.setNeutralMode(NeutralMode.Brake);
        /* PID yet to be calibrated */
        climberLiftSRX.config_kP(3, 0, 0);
        climberLiftSRX.config_kI(3, 0);
        climberLiftSRX.config_kD(3, 0);
    }

    public void setStiltsDriveSpeed(double speed) {
        climberDriveSPX.set(speed);
    }

    public void setStiltsClimbSpeed(double speed) {
        climberLiftSRX.set(speed);
    }

    protected void initDefaultCommand() {
    }
}