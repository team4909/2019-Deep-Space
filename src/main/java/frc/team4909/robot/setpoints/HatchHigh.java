package frc.team4909.robot.setpoints;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4909.robot.Robot;


public class HatchHigh extends Command {
    public HatchHigh(){
        requires(Robot.elevatorSubsystem);
        requires(Robot.elevatorArmSubsystem);
    }

    @Override
    protected void initialize() {
        Robot.elevatorSubsystem.setPosition(-51300);
        Robot.elevatorArmSubsystem.setAngle(90);
        setTimeout(1.5);
    }

    @Override
    protected boolean isFinished() {
        return isTimedOut();
    }

}