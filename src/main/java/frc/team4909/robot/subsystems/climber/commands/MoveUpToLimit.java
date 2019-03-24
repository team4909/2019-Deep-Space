// package frc.team4909.robot.subsystems.climber.commands;

// import edu.wpi.first.wpilibj.command.Command;
// import frc.team4909.robot.Robot;

// public class MoveUpToLimit extends Command {

//     public MoveUpToLimit() {
//         requires(Robot.climberSubsystem);
//     }

//     @Override
//     protected void initialize() {
//         int maxAmps = 1; // @todo determine this value
//         Robot.climberSubsystem.setCurrentLimit(maxAmps);
//         Robot.climberSubsystem.setSpeed(.25);
//     }

//     @Override
//     protected boolean isFinished() {
//         return Robot.climberSubsystem.isAtTop();
//     }
//     @Override
//     protected void end() {
//         Robot.climberSubsystem.setSpeed(0);
//         Robot.climberSubsystem.setCurrentLimit(0); //disable limit
//         Robot.climberSubsystem.updateHoldingPos();
//         Robot.climberSubsystem.holdPosition();
//     }
// }