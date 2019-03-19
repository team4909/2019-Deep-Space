package frc.team4909.robot.subsystems.stilts.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4909.robot.Robot;

public class MoveUpToLimit extends Command {

    public MoveUpToLimit() {
        requires(Robot.stiltSubsystem);
    }

    @Override
    protected void initialize() {
        int maxAmps = 1; // @todo determine this value
        Robot.stiltSubsystem.setCurrentLimit(maxAmps);
        Robot.stiltSubsystem.setSpeed(.25);
    }

    @Override
    protected boolean isFinished() {
        return Robot.stiltSubsystem.isAtTop();
    }
    @Override
    protected void end() {
        Robot.stiltSubsystem.setSpeed(0);
        Robot.stiltSubsystem.setCurrentLimit(0); //disable limit
        Robot.stiltSubsystem.updateHoldingPos();
        Robot.stiltSubsystem.holdPosition();
    }
}