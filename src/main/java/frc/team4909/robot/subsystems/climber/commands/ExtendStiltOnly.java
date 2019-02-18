package frc.team4909.robot.subsystems.climber.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4909.robot.Robot;
import frc.team4909.robot.RobotConstants;

public class ExtendStiltOnly extends Command {

    public ExtendStiltOnly() {
        requires(Robot.climberSubsystem);
    }

    @Override
    protected void initialize() {
        Robot.climberSubsystem.setStiltsClimbSpeed(RobotConstants.climberStiltSpeed);
    }

    protected void execute() {
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