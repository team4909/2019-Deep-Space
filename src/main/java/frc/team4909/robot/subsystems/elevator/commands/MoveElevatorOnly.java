package frc.team4909.robot.subsystems.elevator.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team4909.robot.Robot;
import frc.team4909.robot.RobotConstants;
import frc.team4909.robot.operator.controllers.BionicF310;
import frc.team4909.robot.operator.generic.BionicAxis;

public class MoveElevatorOnly extends Command {
    BionicF310 gamepad;
    BionicAxis axis;
      public MoveElevatorOnly(BionicF310 gamepad, BionicAxis axis) {
        requires(Robot.elevatorSubsystem);
        this.gamepad = gamepad;
        this.axis = axis;
      }
      protected void initialize() {
        SmartDashboard.putString("Elevator Status", "init");
        Robot.elevatorSubsystem.configReverseLimitSwitch(false);
        
      }
      protected void execute() {
        SmartDashboard.putString("Elevator Status", "exe");
        double speed = gamepad.getThresholdAxis(axis);
        Robot.elevatorSubsystem.setSpeed(-1 * speed * RobotConstants.elevatorSpeedMultiplier);
        // Robot.elevatorSubsystem.updateHoldingPos();
      }
      protected boolean isFinished() {
        return false;
      }
      protected void end() {
        SmartDashboard.putString("Elevator Status", "end");
        Robot.elevatorSubsystem.setSpeed(0);
        Robot.elevatorSubsystem.updateHoldingPos();
      }
      @Override
        protected void interrupted() {
          end();
          SmartDashboard.putString("Elevator Status", "interrupted");
        }
    }