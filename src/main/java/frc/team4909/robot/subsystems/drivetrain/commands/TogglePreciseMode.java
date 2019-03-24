package frc.team4909.robot.subsystems.drivetrain.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4909.robot.Robot;

public class TogglePreciseMode extends Command {
    public TogglePreciseMode() {
        requires(Robot.drivetrainSubsystem);
    }

    protected void initialize() {
        Robot.drivetrainSubsystem.togglePreciseMode();
    }

    protected void end(){
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}