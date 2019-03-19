package frc.team4909.robot.subsystems.intake.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.team4909.robot.Robot;

public class Default_HatchPanelIntakeClose extends InstantCommand {

    public Default_HatchPanelIntakeClose() {
        requires(Robot.intakeSubsystem);
    }

    protected void initialize() {
        Robot.intakeSubsystem.hatchPanelIntakeClose();
    }
}