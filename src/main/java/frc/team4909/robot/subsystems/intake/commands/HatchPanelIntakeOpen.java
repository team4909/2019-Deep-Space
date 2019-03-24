// package frc.team4909.robot.subsystems.intake.commands;

// import edu.wpi.first.wpilibj.command.Command;
// import frc.team4909.robot.subsystems.intake.commands.HatchPanelIntakeClose;
// import frc.team4909.robot.Robot;

// public class HatchPanelIntakeOpen extends Command {
//     public HatchPanelIntakeOpen() {
//         requires(Robot.intakeSubsystem);
//     }

//     protected void execute() {
//         Robot.intakeSubsystem.hatchPanelIntakeOpen();
//     }

//     protected void end() {
//         Robot.intakeSubsystem.hatchPanelIntakeClose();
//     }

//     @Override
//     protected boolean isFinished() {
//         return false;
//     }

// }