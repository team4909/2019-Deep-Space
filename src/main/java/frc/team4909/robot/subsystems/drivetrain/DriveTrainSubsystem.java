package frc.team4909.robot.subsystems.drivetrain;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team4909.robot.commands.DrivetrainCommand;
import frc.team4909.robot.operator.controllers.BionicF310;

public class DriveTrainSubsystem extends Subsystem{
    CANSparkMax frontLeftSparkMax, rearLeftSparkMax, frontRightSparkMax, rearRightSparkMax;
    RobotDrive BionicDrive;
    BionicF310 driverGamepad;
    double speedMultiplier = 1.0;

    public DriveTrainSubsystem(){
        frontLeftSparkMax = new CANSparkMax(0
                            ,MotorType.kBrushed);

        rearLeftSparkMax = new CANSparkMax(1
                            ,MotorType.kBrushed);

        frontRightSparkMax = new CANSparkMax(2
                            ,MotorType.kBrushed);
        rearRightSparkMax = new CANSparkMax(2
                            ,MotorType.kBrushed);
        
        BionicDrive = new RobotDrive(frontLeftSparkMax, rearLeftSparkMax, frontRightSparkMax, rearRightSparkMax);
    }

    public void arcadeDrive(double x, double y){
        double xSpeed = x * speedMultiplier;
        double ySpeed = y * speedMultiplier;
        BionicDrive.arcadeDrive(xSpeed, ySpeed);
    }

    protected void initDefaultCommand(){
        setDefaultCommand(new DrivetrainCommand());
    }






}