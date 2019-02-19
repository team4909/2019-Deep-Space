package frc.team4909.robot.subsystems.climber.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4909.robot.Robot;
import frc.team4909.robot.RobotConstants;

public class ExtendStilts extends Command {

    public ExtendStilts() {
        requires(Robot.climberSubsystem);
        requires(Robot.elevatorSubsystem);
       // Robot.elevatorSubsystem.setNewPIDValues();
    }

    @Override
    protected void initialize() {
    }

    protected void execute() {
        Robot.climberSubsystem.setSpeeds(RobotConstants.climberStiltSpeed);
    }

    @Override
    protected void end() {
        Robot.climberSubsystem.setSpeeds(0);
       // Robot.elevatorSubsystem.setInitialPIDValues();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}