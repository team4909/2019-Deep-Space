package frc.team4909.robot.subsystems.climber.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.team4909.robot.Robot;
import frc.team4909.robot.RobotConstants;

public class RetractStilts extends InstantCommand {
    public RetractStilts() {
        requires(Robot.climberSubsystem);
    }

    protected void execute() {
        Robot.climberSubsystem.retractStilts();
    }

}