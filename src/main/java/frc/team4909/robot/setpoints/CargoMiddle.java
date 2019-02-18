package frc.team4909.robot.setpoints;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4909.robot.Robot;


public class CargoMiddle extends Command {
    public CargoMiddle(){
        requires(Robot.elevatorSubsystem);
        requires(Robot.elevatorArmSubsystem);
    }

    @Override
    protected void initialize() {
        Robot.elevatorSubsystem.setPosition(-30638);
        Robot.elevatorArmSubsystem.setAngle(45);
        setTimeout(1.5);
    }

    @Override
    protected boolean isFinished() {
        return isTimedOut();
    }

}