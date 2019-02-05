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

        boolean left = middleLeftSensor.get(); // true = sensor is OFF line; false = sensor is ON line
        boolean right = middleRightSensor.get(); // true = sensor is OFF line; false = sensor is ON line

        if (!left && right) { // back left is on line, back right is not on line
            System.out.println(left + "  " + right + " off line to RIGHT");
            Robot.drivetrainSubsystem.tankDrive(RobotConstants.fastVelocity, RobotConstants.slowVelocity);
        } else if (left && !right) { // back right is on line, back left is not on line
            System.out.println(left + "  " + right + " off line to LEFT");
            Robot.drivetrainSubsystem.tankDrive(RobotConstants.slowVelocity, RobotConstants.fastVelocity);
        } else { //
            System.out.println(left + "  " + right + " ON LINE");
            Robot.drivetrainSubsystem.tankDrive(RobotConstants.fastVelocity, RobotConstants.fastVelocity);
        }

    }

    protected boolean isFinished() {
        return false; // return lidar.getDistance() < dist
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}