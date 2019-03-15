package frc.team4909.robot.setpoints;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4909.robot.Robot;
import frc.team4909.robot.RobotConstants;


public class HatchMiddle extends Command {
    public HatchMiddle(){
        requires(Robot.elevatorSubsystem);
        requires(Robot.elevatorArmSubsystem);
    }

    @Override
    protected void initialize() {
        Robot.elevatorSubsystem.setPosition(RobotConstants.elevatorSetpointHatchMiddle);
        Robot.elevatorArmSubsystem.setAngle(RobotConstants.wristSetpointHatch);
        setTimeout(1.5);
    }

    @Override
    protected boolean isFinished() {
        return isTimedOut();
    }

}