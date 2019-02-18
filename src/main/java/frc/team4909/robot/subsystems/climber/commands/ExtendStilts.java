package frc.team4909.robot.subsystems.climber.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4909.robot.Robot;
import frc.team4909.robot.RobotConstants;

public class ExtendStilts extends Command {

    double speed;

    public ExtendStilts(double speed) {
        requires(Robot.climberSubsystem);
        requires(Robot.elevatorSubsystem);
        speed = this.speed;
    }

    @Override
    protected void initialize() {
        Robot.elevatorSubsystem.setPIDValues();
        Robot.climberSubsystem.setSpeeds(speed);
    }

    protected void execute() {
    }

    @Override
    protected void end() {
        Robot.climberSubsystem.setSpeeds(0);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}