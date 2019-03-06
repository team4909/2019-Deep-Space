package frc.team4909.robot.subsystems.elevatorarm.commands;

import frc.team4909.robot.Robot;
import frc.team4909.robot.RobotConstants;
import frc.team4909.robot.operator.controllers.BionicF310;

import edu.wpi.first.wpilibj.command.Command;

public class ElevatorArmOperatorControl extends Command {

    // private int holdingPosition = Robot.elevatorArmSubsystem.getPosition();

    public ElevatorArmOperatorControl() {
        requires(Robot.elevatorArmSubsystem);
    }

    @Override
    public void execute() {
        // Sets speed to manipulator gamepad right Y stick value
        double moveSpeed = -Robot.manipulatorGamepad.getThresholdAxis(BionicF310.RY)
                * RobotConstants.elevatorArmSpeedMultiplier;

        if (moveSpeed == 0) { // If Y-stick value is not moving, HOLD position
            Robot.elevatorArmSubsystem.setPosition(Robot.elevatorArmSubsystem.holdingPosition);
        } else { // Set speed to Y-stick value and HOLD position
            Robot.elevatorArmSubsystem.elevatorArmSetSpeed(moveSpeed);
            Robot.elevatorArmSubsystem.holdingPosition = Robot.elevatorArmSubsystem.getPosition();
        }

    }

    @Override
    protected boolean isFinished() {
        return false;
    }

}