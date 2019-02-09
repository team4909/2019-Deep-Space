package frc.team4909.robot.subsystems.elevator.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.team4909.robot.subsystems.elevator.ElevatorSubsystem;
import frc.team4909.robot.Robot;

public class SetElevatorPosition extends InstantCommand {
    private int setpoint;

    public SetElevatorPosition(int setpoint) {
        this.setpoint = setpoint;       
        requires(Robot.elevatorSubsystem);
    }

    @Override
    public void initialize() {
        Robot.elevatorSubsystem.holdingPosition = setpoint;
    }
}