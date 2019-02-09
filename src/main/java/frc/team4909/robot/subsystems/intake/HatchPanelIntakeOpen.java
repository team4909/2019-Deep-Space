package frc.team4909.robot.subsystems.intake;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.team4909.robot.Robot;

public class HatchPanelIntakeOpen extends InstantCommand {

    public void HatchPanelIntakeOpen() {
        requires(Robot.intakeSubsystem);
    }

    protected void initialize() {
        Robot.intakeSubsystem.hatchPanelIntakeOpen();
    }

}