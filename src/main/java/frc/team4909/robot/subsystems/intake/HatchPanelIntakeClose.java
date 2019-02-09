package frc.team4909.robot.subsystems.intake;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.team4909.robot.Robot;

public class HatchPanelIntakeClose extends InstantCommand {

    public HatchPanelIntakeClose() {
        requires(Robot.intakeSubsystem);
    }

    protected void initialize() {
        Robot.intakeSubsystem.hatchPanelIntakeClose();
    }

}