package frc.team4909.robot.setpoints;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4909.robot.Robot;
import frc.team4909.robot.RobotConstants;


public class Arm45 extends Command {
    public Arm45(){
        requires(Robot.elevatorArmSubsystem);
    }

    @Override
    protected void initialize() {
        Robot.elevatorArmSubsystem.setPosition(RobotConstants.elevatorArmSetpointCargoOut);
        setTimeout(1.5);
    }

    @Override
    protected boolean isFinished() {
        return isTimedOut();
    }
    @Override
    protected void end() {
        Robot.elevatorArmSubsystem.holdingPosition = Robot.elevatorArmSubsystem.getPosition();

    }

}