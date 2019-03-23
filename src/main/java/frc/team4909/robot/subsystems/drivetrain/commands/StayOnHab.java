package frc.team4909.robot.subsystems.drivetrain.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4909.robot.Robot;

public class StayOnHab extends Command {
    public StayOnHab() {
        requires(Robot.drivetrainSubsystem);
    }

    protected void initialize() {
    }
    @Override
    protected void execute() {
        Robot.drivetrainSubsystem.driveeNoInvert(.3);

    }
    
    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        // Robot.drivetrainSubsystem.driveeNoInvert(0);
    }
}