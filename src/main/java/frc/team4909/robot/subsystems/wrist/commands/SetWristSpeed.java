package frc.team4909.robot.subsystems.wrist.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4909.robot.Robot;

public class SetWristSpeed extends Command {

    private double  speed = 0;

    public SetWristSpeed(double speed1) {
        requires(Robot.wristSubsystem);
        speed = speed1;
    }

    @Override
    public void initialize() {
        Robot.wristSubsystem.elevatorArmSetSpeed(speed);
    }
    @Override
    protected void end() {
        Robot.wristSubsystem.elevatorArmSetSpeed(0);
        Robot.wristSubsystem.holdingPosition = Robot.wristSubsystem.getPosition();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

}