// package frc.team4909.robot.subsystems.elevator.commands;

// import edu.wpi.first.wpilibj.command.Command;
// import frc.team4909.robot.Robot;

// public class Default_ElevHoldPos extends Command {

//     public Default_ElevHoldPos()
//     {
//         requires(Robot.elevatorSubsystem);
//     }
//     @Override
//     protected void initialize() {
//         // Robot.elevatorSubsystem.updateHoldingPos();
//         Robot.elevatorSubsystem.holdPosition();
//     }
//     @Override
//     protected void execute() {
//         Robot.elevatorSubsystem.holdPosition();
//     }

//     @Override
//     protected boolean isFinished() {
//         return false;
//     }
// }