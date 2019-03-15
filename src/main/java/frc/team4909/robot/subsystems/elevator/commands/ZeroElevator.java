package frc.team4909.robot.subsystems.elevator.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.team4909.robot.subsystems.elevator.ElevatorSubsystem;
import frc.team4909.robot.Robot;

public class ZeroElevator extends InstantCommand {
    public ZeroElevator() {
        requires(Robot.elevatorSubsystem);
    }

    @Override
    protected void initialize() {
        Robot.elevatorSubsystem.reset();
    }
}
