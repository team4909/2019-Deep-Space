// package frc.team4909.robot.subsystems.StiltWheel.commands;

// import edu.wpi.first.wpilibj.command.Command;
// import frc.team4909.robot.Robot;
// import frc.team4909.robot.RobotConstants;
// import frc.team4909.robot.operator.controllers.BionicF310;
// import frc.team4909.robot.operator.generic.BionicAxis;

// public class MoveStiltWheels extends Command {
//     private boolean isGoingForward;
//     private BionicAxis axis;

//     public MoveStiltWheels(boolean isGoingForward) {
//         requires(Robot.stiltWheelSubsystem);
//         this.isGoingForward = isGoingForward;
//         if (isGoingForward) {
//             axis = BionicF310.RT;
//         } else {
//             axis = BionicF310.LT;
//         }
//     }
    
//     @Override
//     protected void initialize() {
//     }
//     @Override
//     protected void execute() {
//         double speed = -Robot.driverGamepad.getThresholdAxis(axis);
//         double direction = isGoingForward ? 1 : -1;
//         Robot.stiltWheelSubsystem.setSpeed(direction * speed * RobotConstants.climberDriveSpeedManual);
//     }
//     @Override
//     protected boolean isFinished() {
//         return false;
//     }
//     @Override
//     protected void end() {
//         Robot.stiltWheelSubsystem.setSpeed(0);
//     }
    
// }