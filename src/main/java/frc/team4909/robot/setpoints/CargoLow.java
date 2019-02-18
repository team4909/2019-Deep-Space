package frc.team4909.robot.setpoints;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4909.robot.Robot;


public class CargoLow extends Command {
    public CargoLow(){
        requires(Robot.elevatorSubsystem);
        requires(Robot.elevatorArmSubsystem);
    }

    @Override
    protected void initialize() {
        Robot.elevatorSubsystem.setPosition(-10582);
        Robot.elevatorArmSubsystem.setAngle(45);
        setTimeout(1.5);
    }

    @Override
    protected boolean isFinished() {
        return isTimedOut();
    }

}