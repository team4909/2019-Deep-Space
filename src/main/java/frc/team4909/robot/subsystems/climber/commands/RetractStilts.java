package frc.team4909.robot.subsystems.climber.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team4909.robot.Robot;
import frc.team4909.robot.RobotConstants;

public class RetractStilts extends Command {

    public RetractStilts() {
        requires(Robot.climberSubsystem);
        requires(Robot.elevatorSubsystem);
        Robot.elevatorSubsystem.setPIDValues();

    }

    @Override
    protected void initialize() {
    }

    protected void execute() {
        Robot.climberSubsystem.setSpeeds(-RobotConstants.climberStiltSpeed);
    }

    @Override
    protected void end() {
        Robot.climberSubsystem.setSpeeds(0);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}