package frc.team4909.robot.subsystems.intake.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4909.robot.Robot;
import frc.team4909.robot.RobotConstants;
import frc.team4909.robot.subsystems.led.RGBStrip.Color;

public class CargoIntakeOut extends Command {
    public CargoIntakeOut() {
        requires(Robot.intakeSubsystem);
        requires(Robot.LEDs);
    }

    protected void execute() {
        Robot.intakeSubsystem.setCargoIntakeSpeed(RobotConstants.cargoIntakeOutSpeed);
        Robot.LEDs.set(Color.Lime);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}