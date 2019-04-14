
package frc.team4909.robot.subsystems.drivetrain;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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

    public void driveeNoInvert(double speed) {
        bionicDrive.tankDrive(speed, speed);
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
        if (preciseMode == true) {
            turnMultiplier = RobotConstants.turnPreciseMultiplier;
            speedMultiplier = RobotConstants.drivePreciseMultiplier;

        } else {
            
            turnMultiplier = RobotConstants.maxTurnSpeed  - ((RobotConstants.maxTurnSpeed - RobotConstants.minTurnSpeed) / RobotConstants.elevatorEncoderTicks) * (Math.abs(Robot.elevatorSubsystem.getPosition())); 
            speedMultiplier = RobotConstants.maxDriveSpeed  - ((RobotConstants.maxDriveSpeed - RobotConstants.minDriveSpeed) / RobotConstants.elevatorEncoderTicks) * (Math.abs(Robot.elevatorSubsystem.getPosition())); 

            SmartDashboard.putNumber("Drivetrain turnMultiplier", turnMultiplier);
            SmartDashboard.putNumber("Drivetrain speedMultiplier", speedMultiplier);

            turnMultiplier = RobotConstants.speedTurnMultiplier;
            speedMultiplier = RobotConstants.speedMultiplier;
        }

        speedOutput = speedOutput * speedMultiplier;
        turnOutput = turnOutput * turnMultiplier;

        bionicDrive.arcadeDrive(speedOutput, turnOutput);
    }

    public void curvatureDrive(double xSpeed, double zRotation, boolean isQuickTurn){
        bionicDrive.curvatureDrive(xSpeed, zRotation, isQuickTurn);
    }

    public void invertDriveDirection() {
        inverted = !inverted;
    }

    public void togglePreciseMode() {
        preciseMode = !preciseMode;
    }

    public void preciseModeTrue(){
        preciseMode = true;
    }

    protected void initDefaultCommand() {
        setDefaultCommand(new Drive());
    }

    @Override
    public void periodic() {
        SmartDashboard.putBoolean("Drivatrain - Precise", preciseMode);
    }
}