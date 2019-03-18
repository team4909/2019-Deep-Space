package frc.team4909.robot.subsystems.climber.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team4909.robot.Robot;
import frc.team4909.robot.RobotConstants;
import frc.team4909.robot.operator.controllers.BionicF310;
import frc.team4909.robot.operator.generic.BionicAxis;

public class MoveElevAndStiltsGyro extends Command {
    private double startGyroPos;
    private boolean isGoingUp;
    private BionicAxis axis;

    public MoveElevAndStiltsGyro(boolean isGoingUp) {
        requires(Robot.climberSubsystem);
        requires(Robot.elevatorSubsystem);

        this.isGoingUp = isGoingUp;
        if (isGoingUp) {
            axis = BionicF310.RT;
        } else {
            axis = BionicF310.LT;
        }
    }
    protected void initialize() {
        startGyroPos = Robot.navx.getRawGyroY();
    }

    protected void execute() {
        Robot.climberSubsystem.setStiltsClimbSpeed(
            Robot.climberGamepad.getThresholdAxis(axis)
                * RobotConstants.climbBothSpeedMultiplier
                * (isGoingUp ? 1 : -1) // Maps Direction
                * 2 // Corrects for Joystick Range
        );

        Robot.elevatorSubsystem.setSpeed(
            (Robot.navx.getRawGyroY() - startGyroPos) // Gyro Delta
                * RobotConstants.elevatorGyroP // Basic P Controller
        );
    }

    protected void end() {
        // Stop
        Robot.elevatorSubsystem.setSpeed(0);
        Robot.climberSubsystem.setSpeed(0);

        // Hold
        Robot.elevatorSubsystem.updateHoldingPos();
        Robot.climberSubsystem.updateHoldingPos();
    }

    protected boolean isFinished() {
        return false;
    }
}