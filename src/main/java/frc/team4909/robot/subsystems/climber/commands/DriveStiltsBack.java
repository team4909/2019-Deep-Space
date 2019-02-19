package frc.team4909.robot.subsystems.climber.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4909.robot.Robot;
import frc.team4909.robot.RobotConstants;

public class DriveStiltsBack extends Command {
    public DriveStiltsBack() {
        requires(Robot.climberSubsystem);
    }

    protected void execute() {
        Robot.climberSubsystem.setStiltsDriveSpeed(-RobotConstants.climberDriveSpeedManual);
    }

    @Override
    protected void end() {
        Robot.climberSubsystem.setStiltsDriveSpeed(0);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}