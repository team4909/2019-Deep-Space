package frc.team4909.robot.subsystems.climber.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.team4909.robot.Robot;

public class StopExtend extends InstantCommand {
    public StopExtend() {
        requires(Robot.climberSubsystem);
    }

    protected void execute() {
        Robot.climberSubsystem.stopExtend();
    }
}