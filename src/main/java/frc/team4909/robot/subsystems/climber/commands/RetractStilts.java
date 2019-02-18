package frc.team4909.robot.subsystems.climber.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team4909.robot.Robot;
import frc.team4909.robot.RobotConstants;

public class RetractStilts extends Command {

    public RetractStilts() {
        requires(Robot.climberSubsystem);
        // requires(Robot.elevatorSubsystem);
    }

    @Override
    protected void initialize() {
        Robot.elevatorSubsystem.setPIDValues();
        Robot.climberSubsystem.setSpeeds(-RobotConstants.climberStiltSpeed);
    }

    protected void execute() {
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