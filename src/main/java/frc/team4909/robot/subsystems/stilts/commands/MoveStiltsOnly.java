package frc.team4909.robot.subsystems.stilts.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4909.robot.Robot;
import frc.team4909.robot.RobotConstants;
import frc.team4909.robot.operator.controllers.BionicF310;

public class MoveStiltsOnly extends Command {
      public MoveStiltsOnly() {
        requires(Robot.stiltSubsystem);
      }
      protected void initialize() {
      }
      protected void execute() {
        double speed = Robot.climberGamepad.getThresholdAxis(BionicF310.RY);
        Robot.stiltSubsystem.setStiltsClimbSpeed(-1 * speed * RobotConstants.climbSpeedMultiplier);
        // climberSubsystem.updateHoldingPos();
      }
      protected boolean isFinished() {
        return false;
      }
      protected void end() {
        Robot.stiltSubsystem.updateHoldingPos();
        Robot.stiltSubsystem.setStiltsClimbSpeed(0);
      }
}