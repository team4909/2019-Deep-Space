package frc.team4909.robot.subsystems.climber.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4909.robot.Robot;
import frc.team4909.robot.RobotConstants;

public class DriveStiltsForward extends Command {
    public DriveStiltsForward() {
        requires(Robot.climberSubsystem);
    }

    protected void execute() {
        Robot.climberSubsystem.driveStiltsForward();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}