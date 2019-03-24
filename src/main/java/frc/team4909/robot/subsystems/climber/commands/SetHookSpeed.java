// package frc.team4909.robot.subsystems.climber.commands;

// import edu.wpi.first.wpilibj.command.Command;
// import frc.team4909.robot.Robot;

// public class SetHookSpeed extends Command {
//     private double speed;
//       public SetHookSpeed(double speed) {
//         //requires(Robot.climberSubsystem);
//         this.speed = speed;
//       }
//       protected void initialize() {
//       }
//       protected void execute() {
//         Robot.climberSubsystem.setHookSpeed(speed);
//         // climberSubsystem.updateHoldingPos();
//       }
//       protected boolean isFinished() {
//         return false;
//       }
//       protected void end() {
//           Robot.climberSubsystem.setHookSpeead(0);
//       }
// }