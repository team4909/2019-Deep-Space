package frc.team4909.robot.subsystems.intake.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.team4909.robot.subsystems.led.RGBStrip.Color;
import frc.team4909.robot.Robot;

public class HatchPanelIntakeClose extends InstantCommand {

    public HatchPanelIntakeClose() {
        requires(Robot.intakeSubsystem);
        requires(Robot.LEDs);
    }

    protected void initialize() {
        Robot.intakeSubsystem.hatchPanelIntakeClose();
        Robot.LEDs.set(Color.Lime);
    }
}