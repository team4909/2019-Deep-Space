package frc.team4909.robot.subsystems.climber.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4909.robot.Robot;

public class Default_StiltsHoldPos extends Command {

    public Default_StiltsHoldPos()
    {
        requires(Robot.climberSubsystem);
    }
    @Override
    protected void initialize() {
        Robot.climberSubsystem.holdPosition();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}