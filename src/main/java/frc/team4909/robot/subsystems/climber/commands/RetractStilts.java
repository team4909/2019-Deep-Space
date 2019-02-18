package frc.team4909.robot.subsystems.climber.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team4909.robot.Robot;
import frc.team4909.robot.RobotConstants;

public class RetractStilts extends Command {

    public RetractStilts() {
        requires(Robot.climberSubsystem);
        requires(Robot.elevatorSubsystem);
    }

    protected void execute() {
        Robot.climberSubsystem.setStiltsClimbSpeed(-RobotConstants.climberStiltSpeed);
        Robot.elevatorSubsystem.setSpeed(RobotConstants.elevatorClimbSpeed);
    }

    @Override
    protected void end() {
        Robot.climberSubsystem.setStiltsClimbSpeed(0);
        Robot.elevatorSubsystem.setSpeed(0);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}