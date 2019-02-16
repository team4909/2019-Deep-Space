package frc.team4909.robot.subsystems.climber;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.team4909.robot.Robot;
import frc.team4909.robot.RobotConstants;

public class ExtendStilts extends InstantCommand {
    public ExtendStilts() {
        requires(Robot.climberSubsystem);
    }

    protected void execute() {
        Robot.climberSubsystem.extendStilts();
    }

}