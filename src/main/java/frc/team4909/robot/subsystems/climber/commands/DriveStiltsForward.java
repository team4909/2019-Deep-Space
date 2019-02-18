package frc.team4909.robot.subsystems.climber.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4909.robot.Robot;
import frc.team4909.robot.RobotConstants;
import frc.team4909.robot.operator.controllers.BionicF310;

public class DriveStiltsForward extends Command {
    public DriveStiltsForward() {
        requires(Robot.climberSubsystem);
    }

    protected void execute() {
        Robot.climberSubsystem.setStiltsDriveSpeed(RobotConstants.climberDriveSpeed);
        
    }
    @Override
    protected void end() {
        Robot.climberSubsystem.setStiltsDriveSpeed(0);    
    }
    @Override
    protected boolean isFinished() {
        return false;
    }
}