package frc.team4909.robot.subsystems.climber.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4909.robot.Robot;
import frc.team4909.robot.RobotConstants;

public class RetractStiltOnly extends Command {

    public int holdingPosition;

    public RetractStiltOnly() {
        requires(Robot.climberSubsystem);
    }

    @Override
    protected void initialize() {
    }

    protected void execute() {
        holdingPosition = Robot.elevatorSubsystem.getPosition();
        Robot.climberSubsystem.setStiltsClimbSpeed(-RobotConstants.climberStiltSpeed);
        Robot.climberSubsystem.setStiltsPosition(holdingPosition);

    }

    @Override
    protected void end() {
        Robot.climberSubsystem.setStiltsClimbSpeed(0);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}