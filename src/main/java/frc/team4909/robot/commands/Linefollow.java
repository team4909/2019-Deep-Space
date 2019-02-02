// package frc.team4909.robot.commands;

// import edu.wpi.first.wpilibj.DigitalInput;
// import edu.wpi.first.wpilibj.command.Command;
// import edu.wpi.first.wpilibj.command.InstantCommand;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import frc.team4909.robot.Robot;
// import frc.team4909.robot.subsystems.drivetrain.DriveTrainSubsystem;

// public class Linefollow extends Command {

//     public static DigitalInput LeftSensor, MiddleLeftSensor, MiddleRightSensor, RightSensor;

//     private double velocity = 0.5;

//     public Linefollow() {
//         requires(Robot.drivetrainsub);
//     }

//     protected void initialize() {
//         LeftSensor = new DigitalInput(0);
//         MiddleLeftSensor = new DigitalInput(1);
//         MiddleRightSensor = new DigitalInput(2);
//         RightSensor = new DigitalInput(3);
//     }

//     protected void execute() {
//         SmartDashboard.putBoolean("LEFT line following", !LeftSensor.get());
//         SmartDashboard.putBoolean("RIGHT line following", !RightSensor.get());

//         boolean left = MiddleLeftSensor.get();
//         boolean right = MiddleRightSensor.get();

//         // Should work no matter which side of the robot the cargo ship is on

//         if (!left && right) { // back left is on line, back right is not on line
//             System.out.println(left + "  " + right + " off line to RIGHT");
//             Robot.drivetrainsub.tankDrive(0.5, 0.2);
//         } else if (left && !right) { // back right is on line, back left is not on line
//             System.out.println(left + "  " + right + " off line to LEFT");
//             Robot.drivetrainsub.tankDrive(0.2, 0.5);
//         } else {
//             System.out.println(left + "  " + right + " ON LINE");
//             Robot.drivetrainsub.tankDrive(velocity, velocity);
//         }

//     }


//     /*
//      * isFinished - Our isFinished method always returns false meaning this command
//      * never completes on it's own. The reason we do this is that this command will
//      * be set as the default command for the subsystem. This means that whenever the
//      * subsystem is not running another command, it will run this command. If any
//      * other command is scheduled it will interrupt this command, then return to
//      * this command when the other command completes.
//      */
//     protected boolean isFinished() {
//         return false;
//     }

//     protected void end() {
//     }

//     protected void interrupted() {
//     }
// }