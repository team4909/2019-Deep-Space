package frc.team4909.robot.subsystems.drivetrain;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.team4909.robot.operator.controllers.BionicF310;

public class DriveTrainSubsystem extends Subsystem{
    CANSparkMax frontLeftSparkMax, rearLeftSparkMax, frontRightSparkMax, rearRightSparkMax;
    DifferentialDrive bionicDrive;
    double speedMultiplier = 1.0;

    public DriveTrainSubsystem(){
        frontLeftSparkMax = new CANSparkMax(1
                            ,MotorType.kBrushed);

        rearLeftSparkMax = new CANSparkMax(1
                            ,MotorType.kBrushed);

        frontRightSparkMax = new CANSparkMax(2
                            ,MotorType.kBrushed);

        rearRightSparkMax = new CANSparkMax(2
                            ,MotorType.kBrushed);
        
        bionicDrive = new DifferentialDrive(frontLeftSparkMax, frontRightSparkMax);
    }

    public void tankDrive(double x, double y){
        double xSpeed = x * speedMultiplier;
        double ySpeed = y * speedMultiplier;
        bionicDrive.tankDrive(xSpeed, ySpeed);
    }

    protected void initDefaultCommand(){

    }
}