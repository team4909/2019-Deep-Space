package frc.team4909.robot.subsystems.climber.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.team4909.robot.Robot;

public class ZeroStilts extends InstantCommand {
    public ZeroStilts() {
        requires(Robot.climberSubsystem);
    }

    @Override
    protected void initialize() {
        Robot.climberSubsystem.reset();
    }
}
