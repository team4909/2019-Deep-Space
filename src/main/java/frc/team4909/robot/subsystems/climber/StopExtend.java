package frc.team4909.robot.subsystems.climber;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4909.robot.Robot;

public class StopExtend extends Command {
    public void RetractStilts() {
        requires(Robot.climberSubsystem);
    }

    protected void execute() {
        Robot.climberSubsystem.stopExtend();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}