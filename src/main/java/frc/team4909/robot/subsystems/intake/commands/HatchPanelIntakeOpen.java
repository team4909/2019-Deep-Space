package frc.team4909.robot.subsystems.intake.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4909.robot.subsystems.intake.commands.HatchPanelIntakeClose;
import frc.team4909.robot.subsystems.led.RGBStrip.Color;
import frc.team4909.robot.Robot;

public class HatchPanelIntakeOpen extends Command {
    public HatchPanelIntakeOpen() {
        requires(Robot.intakeSubsystem);
        requires(Robot.LEDs);
    }

	protected void execute() {
        Robot.intakeSubsystem.hatchPanelIntakeOpen();
        Robot.LEDs.set(Color.Yellow);
    }

    protected void end() {
        Robot.intakeSubsystem.hatchPanelIntakeClose();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

}