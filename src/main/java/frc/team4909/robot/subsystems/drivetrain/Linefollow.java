package frc.team4909.robot.subsystems.drivetrain;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team4909.robot.Robot;
import frc.team4909.robot.RobotConstants;

public class Linefollow extends Command {

    public static DigitalInput leftSensor,rightSensor;
/* middleLeftSensor, middleRightSensor, */
    public Linefollow() {
        requires(Robot.drivetrainSubsystem);

        leftSensor = new DigitalInput(0);
        //middleLeftSensor = new DigitalInput(1);
       // middleRightSensor = new DigitalInput(2);
        rightSensor = new DigitalInput(1);
    }

    protected void initialize() {
    }

    protected void execute() {
        System.out.println("L: " + !leftSensor.get());
    System.out.println("R: " + !rightSensor.get());
        SmartDashboard.putBoolean("LEFT line following", !leftSensor.get());
        SmartDashboard.putBoolean("RIGHT line following", !rightSensor.get());
        // boolean outsideLeft = leftSensor.get();
        boolean leftOffLine = leftSensor.get(); // true = sensor is OFF line; false = sensor is ON line
        boolean rightOffLine = rightSensor.get(); // true = sensor is OFF line; false = sensor is ON line
        // boolean outsideRight = rightSensor.get();

        if (!leftOffLine && rightOffLine) { // Middle left is on line
            System.out.println(leftOffLine + "  " + rightOffLine + " off line to RIGHT");
            Robot.drivetrainSubsystem.tankDrive(RobotConstants.slowVelocity, RobotConstants.fastVelocity);
        } else if (leftOffLine && !rightOffLine) { // Middle right is on line
            System.out.println(leftOffLine + "  " + rightOffLine + " off line to LEFT");
            Robot.drivetrainSubsystem.tankDrive(RobotConstants.fastVelocity, RobotConstants.slowVelocity);
        // } else if (!outsideLeft && outsideRight) { // Outside left sensor is on the line
        //     Robot.drivetrainSubsystem.tankDrive(RobotConstants.slowVelocity, RobotConstants.fastVelocity);
        // } else if (outsideLeft && !outsideRight) { // Outside right sensor is on the line
        //     Robot.drivetrainSubsystem.tankDrive(RobotConstants.fastVelocity, RobotConstants.slowVelocity);
        } else {
            System.out.println(leftOffLine + "  " + rightOffLine + " ON LINE");
            Robot.drivetrainSubsystem.tankDrive(RobotConstants.fastVelocity, RobotConstants.fastVelocity);
        }
    }

    protected boolean isFinished() {
        return Robot.lidar.getDistance() < RobotConstants.lidarLimit; // return lidar.getDistance() < dist
    }

    protected void end() {

    }

    protected void interrupted() {

    }
}