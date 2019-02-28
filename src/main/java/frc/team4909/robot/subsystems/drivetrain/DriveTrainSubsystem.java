
package frc.team4909.robot.subsystems.drivetrain;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.team4909.robot.RobotMap;
import frc.team4909.robot.operator.controllers.BionicF310;
import frc.team4909.robot.Robot;
import frc.team4909.robot.RobotConstants;
import frc.team4909.robot.subsystems.drivetrain.commands.Drive;

public class DriveTrainSubsystem extends Subsystem {
    CANSparkMax frontLeftSparkMax, rearLeftSparkMax, frontRightSparkMax, rearRightSparkMax;
    SpeedControllerGroup m_left, m_right;
    DifferentialDrive bionicDrive;
    double speedMultiplier = RobotConstants.speedMultiplier;
    double turnMultiplier = RobotConstants.speedTurnMultiplier;
    boolean inverted = false;
    boolean preciseMode = false;

    public DriveTrainSubsystem() {
        frontLeftSparkMax = new CANSparkMax(RobotMap.driveFrontLeftSparkMaxCAN, MotorType.kBrushless);
        rearLeftSparkMax = new CANSparkMax(RobotMap.driveRearLeftSparkMaxCAN, MotorType.kBrushless);
        SpeedControllerGroup m_left = new SpeedControllerGroup(frontLeftSparkMax, rearLeftSparkMax);                    

        frontRightSparkMax = new CANSparkMax(RobotMap.driveFrontRightSparkMaxCAN, MotorType.kBrushless);
        rearRightSparkMax = new CANSparkMax(RobotMap.driveRearRightSparkMaxCAN, MotorType.kBrushless);
        SpeedControllerGroup m_right = new SpeedControllerGroup(frontRightSparkMax, rearRightSparkMax);

        bionicDrive = new DifferentialDrive(m_left, m_right);
    }

    public void tankDrive(double leftSpeed, double rightSpeed) {
        double leftSpeedOutput = leftSpeed;
        double rightSpeedOutput = rightSpeed;

        if (inverted) { //inverts tankDrive direction
            leftSpeedOutput = -rightSpeed;
            rightSpeedOutput = -leftSpeed;
        }

        leftSpeedOutput = leftSpeedOutput * speedMultiplier;
        rightSpeedOutput = rightSpeedOutput * speedMultiplier;

        bionicDrive.tankDrive(leftSpeedOutput, rightSpeedOutput);
    }

    public void arcadeDrive(double leftSpeed, double rightSpeed) {
        double speedOutput = leftSpeed;
        double turnOutput = rightSpeed;
        double speedTurnMultiplier = turnMultiplier;
        if (preciseMode == true){
            speedTurnMultiplier = RobotConstants.speedTurnPreciseMultiplier;
        } else{
            speedTurnMultiplier = RobotConstants.speedTurnMultiplier;
        }
        if (inverted) { //inverts arcadeDrive
            speedOutput = -rightSpeed;
            turnOutput = -leftSpeed;
        }

        speedOutput = speedOutput * speedMultiplier;
        turnOutput = turnOutput * speedTurnMultiplier;

        bionicDrive.arcadeDrive(speedOutput, turnOutput);
    }

    public void invertDriveDirection() {
        inverted = !inverted;
    }

    public void swapTurnSpeed() {
        preciseMode = !preciseMode;
    }

    protected void initDefaultCommand() {
        setDefaultCommand(new Drive());
    }
}