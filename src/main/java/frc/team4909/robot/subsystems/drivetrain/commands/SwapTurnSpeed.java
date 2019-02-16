package frc.team4909.robot.subsystems.drivetrain.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.team4909.robot.Robot;

public class SwapTurnSpeed extends InstantCommand {
    public SwapTurnSpeed() {
        requires(Robot.drivetrainSubsystem);
    }

    protected void initialize() {
        Robot.drivetrainSubsystem.swapTurnSpeed();
    }
}