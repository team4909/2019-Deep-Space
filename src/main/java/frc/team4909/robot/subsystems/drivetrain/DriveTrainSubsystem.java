package frc.team4909.robot.subsystems.drivetrain;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.team4909.robot.operator.controllers.BionicF310;

public class DriveTrainSubsystem extends Subsystem{
    CANSparkMax frontLeftSparkMax, rearLeftSparkMax, frontRightSparkMax, rearRightSparkMax;
    DifferentialDrive bionicDrive;
    double speedMultiplier = 1.0;
    SpeedControllerGroup m_left, m_right;

    public DriveTrainSubsystem(){
        frontLeftSparkMax = new CANSparkMax(1
                            ,MotorType.kBrushless);

        rearLeftSparkMax = new CANSparkMax(2
                            ,MotorType.kBrushless);

        SpeedControllerGroup m_left = new SpeedControllerGroup(frontLeftSparkMax, rearLeftSparkMax);                    

        frontRightSparkMax = new CANSparkMax(3
                            ,MotorType.kBrushless);

        rearRightSparkMax = new CANSparkMax(4
                            ,MotorType.kBrushless);

        SpeedControllerGroup m_right = new SpeedControllerGroup(frontRightSparkMax, rearRightSparkMax);
        
        bionicDrive = new DifferentialDrive(m_left, m_right);
    }

    public void arcadeDrive(double x, double y){
        double xSpeed = x * speedMultiplier;
        double ySpeed = y * speedMultiplier;
        bionicDrive.arcadeDrive(xSpeed, ySpeed);
    }

    protected void initDefaultCommand(){

    }






}