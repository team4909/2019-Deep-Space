package frc.team4909.robot.subsystems.elevator.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.team4909.robot.subsystems.elevator.ElevatorSubsystem;
import frc.team4909.robot.Robot;

public class SetElevatorPosition extends InstantCommand {
    private int setpoint, threshold;

    public SetElevatorPosition(int setpoint, int threshold) {
        this.setpoint = setpoint;  
        this.threshold = threshold;     
        requires(Robot.elevatorSubsystem);
    }

    @Override
    public void initialize() {
        Robot.elevatorSubsystem.setPosition(setpoint);

    }

    @Override
    protected boolean isFinished() {
        System.out.println("done");
        return false;
    }
}