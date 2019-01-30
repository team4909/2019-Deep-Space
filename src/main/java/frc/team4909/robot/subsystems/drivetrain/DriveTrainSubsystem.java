package frc.team4909.robot.subsystems.drivetrain;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.team4909.robot.commands.Drive;
import frc.team4909.robot.operator.controllers.BionicF310;

public class DriveTrainSubsystem extends Subsystem{
    CANSparkMax frontLeftSparkMax, rearLeftSparkMax, frontRightSparkMax, rearRightSparkMax;
    DifferentialDrive bionicDrive;
    double speedMultiplier = 1.0;

    public DriveTrainSubsystem(){             //Initialize motor controllers
        frontLeftSparkMax = new CANSparkMax(1
                            ,MotorType.kBrushed);

        rearLeftSparkMax = new CANSparkMax(1
                            ,MotorType.kBrushed);

    SpeedControllerGroup m_left = new SpeedControllerGroup(frontLeftSparkMax, rearLeftSparkMax);

        frontRightSparkMax = new CANSparkMax(2
                            ,MotorType.kBrushed);

        rearRightSparkMax = new CANSparkMax(2
                            ,MotorType.kBrushed);
                            
    SpeedControllerGroup m_right = new SpeedControllerGroup(frontRightSparkMax, rearRightSparkMax);
        
        bionicDrive = new DifferentialDrive(m_left, m_right); //Creates new drive object
    }

    public void arcadeDrive(double x, double y){
        double xSpeed = x * speedMultiplier;
        double ySpeed = y * speedMultiplier;
        bionicDrive.arcadeDrive(xSpeed, ySpeed);
    }

    public void tankDrive(double x, double y){
        double xSpeed = x;
        double ySpeed = y;
        bionicDrive.tankDrive(xSpeed, ySpeed);
    }

    protected void initDefaultCommand(){
        setDefaultCommand(new Drive());
    }
    
}