package frc.team4909.robot.subsystems.intake.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4909.robot.Robot;
import frc.team4909.robot.RobotConstants;

public class Default_CargoIntakeHold extends Command{
    public Default_CargoIntakeHold(){
        requires(Robot.intakeSubsystem);
    }

    protected void execute() {
        Robot.intakeSubsystem.holdCargoIntake();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}