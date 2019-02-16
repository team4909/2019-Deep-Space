package frc.team4909.robot.subsystems.elevator.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.team4909.robot.subsystems.elevator.ElevatorSubsystem;
import frc.team4909.robot.Robot;

public class SetElevatorPosition extends Command {
    private int setpoint, threshold;

    public SetElevatorPosition(int setpoint, int threshold) {
        this.setpoint = setpoint;  
        this.threshold = threshold;     
        requires(Robot.elevatorSubsystem);
    }

    @Override
    protected void execute() {
        Robot.elevatorSubsystem.setPosition(setpoint);

    }

    @Override
    protected boolean isFinished() {
        System.out.println("New position is: " + Robot.elevatorSubsystem.getPosition());
        return false;
        //return Math.abs(Robot.elevatorSubsystem.getPosition() - setpoint) < threshold;
    }
}