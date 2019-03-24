package frc.team4909.robot.subsystems.elevatorarm.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.team4909.robot.Robot;
import frc.team4909.robot.subsystems.elevatorarm.ElevatorArmSubsystem;

public class SetWristSpeed extends Command {

    private double  speed = 0;

    public SetWristSpeed(double speed1) {
        requires(Robot.elevatorArmSubsystem);
        speed = speed1;
    }

    @Override
    public void initialize() {
        Robot.elevatorArmSubsystem.elevatorArmSetSpeed(speed);
    }
    @Override
    protected void end() {
        Robot.elevatorArmSubsystem.elevatorArmSetSpeed(0);
        Robot.elevatorArmSubsystem.holdingPosition = Robot.elevatorArmSubsystem.getPosition();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

}