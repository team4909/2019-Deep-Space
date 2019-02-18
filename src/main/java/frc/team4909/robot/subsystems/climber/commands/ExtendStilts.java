package frc.team4909.robot.subsystems.climber.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4909.robot.Robot;
import frc.team4909.robot.RobotConstants;

public class ExtendStilts extends Command {

    public ExtendStilts() {
        requires(Robot.climberSubsystem);
        requires(Robot.elevatorSubsystem);
    }

    protected void execute() {
        Robot.elevatorSubsystem.setPIDValues();
        Robot.climberSubsystem.setStiltsClimbSpeed(RobotConstants.climberStiltSpeed);
        // Robot.elevatorSubsystem.setSpeed(-RobotConstants.elevatorClimbSpeed);
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