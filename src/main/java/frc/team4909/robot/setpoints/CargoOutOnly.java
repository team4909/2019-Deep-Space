package frc.team4909.robot.setpoints;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4909.robot.Robot;
import frc.team4909.robot.RobotConstants;


public class CargoOutOnly extends Command {
    public CargoOutOnly(){
        requires(Robot.elevatorArmSubsystem);
    }

    @Override
    protected void initialize() {
        Robot.elevatorArmSubsystem.setAngle(RobotConstants.elevatorArmSetpointCargoOut);
        setTimeout(1.5);
    }

    @Override
    protected boolean isFinished() {
        return isTimedOut();
    }

}