package frc.team4909.robot.subsystems.drivetrain.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4909.robot.Robot;

public class SwapTurnSpeed extends Command {
    public SwapTurnSpeed() {
        requires(Robot.drivetrainSubsystem);
    }

    protected void initialize() {
        Robot.drivetrainSubsystem.swapTurnSpeed();
    }

    protected void end(){
        Robot.drivetrainSubsystem.swapTurnSpeed();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}