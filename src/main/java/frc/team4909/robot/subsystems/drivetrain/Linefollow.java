package frc.team4909.robot.subsystems.drivetrain;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team4909.robot.Robot;
import frc.team4909.robot.RobotConstants;

public class Linefollow extends Command {

    public static DigitalInput leftSensor, middleLeftSensor, middleRightSensor, rightSensor;


    public Linefollow() {
        requires(Robot.drivetrainSubsystem);
    }

    protected void initialize() {
        leftSensor = new DigitalInput(0);
        middleLeftSensor = new DigitalInput(1);
        middleRightSensor = new DigitalInput(2);
        rightSensor = new DigitalInput(3);
    }

    protected void execute() {
        SmartDashboard.putBoolean("LEFT line following", !leftSensor.get());
        SmartDashboard.putBoolean("RIGHT line following", !rightSensor.get());
        boolean outsideLeft = leftSensor.get();
        boolean left = middleLeftSensor.get(); // true = sensor is OFF line; false =\ sensor is ON line
        boolean right = middleRightSensor.get(); // true = sensor is OFF line; false = sensor is ON line
        boolean outsideRight = rightSensor.get();

        if (!left && right) { // Inside left is on line
            System.out.println(left + "  " + right + " off line to RIGHT");
            Robot.drivetrainSubsystem.tankDrive(RobotConstants.fastVelocity, RobotConstants.slowVelocity);
        } else if (left && !right) { // Inside right is on line
            System.out.println(left + "  " + right + " off line to LEFT");
            Robot.drivetrainSubsystem.tankDrive(RobotConstants.slowVelocity, RobotConstants.fastVelocity);
        } else if(!outsideLeft && outsideRight){     // Outside left sensor is on the line
            Robot.drivetrainSubsystem.tankDrive(0.7, 0.2);
        
        } else if(outsideLeft && !outsideRight){ // Outside right sensor is on the line
            Robot.drivetrainSubsystem.tankDrive(0.2, 0.7);
        }
        else {
            System.out.println(left + "  " + right + " ON LINE");
            Robot.drivetrainSubsystem.tankDrive(RobotConstants.fastVelocity, RobotConstants.fastVelocity);
        }

    }


    
    protected boolean isFinished() {
        return Robot.lidar.getDistance() < RobotConstants.lidarLimit;  // return lidar.getDistance() < dist
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}