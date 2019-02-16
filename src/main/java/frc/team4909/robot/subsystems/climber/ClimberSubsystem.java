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
    WPI_TalonSRX climberSRX;
    WPI_VictorSPX climberSPX;

    public ClimberSubsystem() {
        climberSRX = new WPI_TalonSRX(RobotMap.climberSRXID); // Climber lift
        climberSPX = new WPI_VictorSPX(RobotMap.climberSPXID); // CLimber drive

        climberSRX.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);

        /* PID yet to be calibrated */
        climberSRX.config_kP(1, 0.6, 0);
        climberSRX.config_kI(1, 0);
        climberSRX.config_kD(1, 0);
    }

    public void extendStilts() {
        climberSRX.set(RobotConstants.climberStiltSpeed);
    }

    public void retractStilts() {
        climberSRX.set(-RobotConstants.climberStiltSpeed);
    }

    public void driveStiltsForward() {
        climberSPX.set(-RobotConstants.climberDriveSpeed);
    }
    
    public void driveStiltsBack(){
        climberSPX.set(RobotConstants.climberDriveSpeed);
    }

    public void stopExtend() {
        climberSRX.set(0);
    }

    protected void initDefaultCommand() {
    }
}