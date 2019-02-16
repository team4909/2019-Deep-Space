package frc.team4909.robot.subsystems.climber;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4909.robot.Robot;
import frc.team4909.robot.RobotConstants;

public class DriveStilts extends Command {
    public void DriveStilts() {
        requires(Robot.climberSubsystem);
    }

    protected void execute() {
        Robot.climberSubsystem.driveStilts();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}