package frc.team4909.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.team4909.robot.Robot;
import frc.team4909.robot.subsystems.DriveTrainSubsystem;

public class InvertDriveDirection extends InstantCommand {
    public InvertDriveDirection(){
        requires(Robot.drivetrainSubsystem);
    }

    protected void initialize() {
        Robot.drivetrainSubsystem.invertDriveDirection();
    }
}