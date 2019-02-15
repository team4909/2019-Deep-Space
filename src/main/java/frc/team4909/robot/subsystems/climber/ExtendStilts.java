package frc.team4909.robot.subsystems.climber;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4909.robot.Robot;
import frc.team4909.robot.RobotConstants;

public class ExtendStilts extends Command {
    public void ExtendStilts() {
        requires(Robot.climberSubsystem);
    }

    protected void execute() {
        Robot.climberSubsystem.extendStilts();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}