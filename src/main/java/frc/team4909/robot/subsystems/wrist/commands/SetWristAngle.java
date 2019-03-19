package frc.team4909.robot.subsystems.wrist.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.team4909.robot.Robot;

public class SetWristAngle extends InstantCommand {
    private final int setpoint;

    public SetWristAngle(int setpoint) {
        this.setpoint = setpoint;
    }

    @Override
    public void initialize() {
        Robot.wristSubsystem.updateHoldingPos(setpoint);
    }

}