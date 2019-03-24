
package frc.team4909.robot.testing;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team4909.robot.RobotMap;
import frc.team4909.robot.operator.controllers.BionicF310;
import frc.team4909.robot.Robot;
import frc.team4909.robot.RobotConstants;

public class DriveTrainSubsystem extends Subsystem {
    Spark m_left, m_right;
    DifferentialDrive bionicDrive;
    final double kP = 0.035;
    final double kD = 0;

    public DriveTrainSubsystem() {
        
        m_left = new Spark(0);
        m_right = new Spark(1);
        bionicDrive = new DifferentialDrive(m_left, m_right);
    }

    public void arcadeDrive(double leftSpeed, double rightSpeed) {
        
        bionicDrive.arcadeDrive(-leftSpeed, -rightSpeed);
    }
    public void driveAssisted(double throttle, double angleOffset) {
        System.out.println("Angle offset = " + angleOffset);
        double correction = angleOffset * kP - angleOffset * kD;
        arcadeDrive(throttle * 0.5, correction);
      }
    

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new Drive());    
    }

}