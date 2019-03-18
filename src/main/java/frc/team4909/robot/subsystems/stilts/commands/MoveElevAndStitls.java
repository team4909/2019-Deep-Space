package frc.team4909.robot.subsystems.stilts.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team4909.robot.Robot;
import frc.team4909.robot.RobotConstants;
import frc.team4909.robot.operator.controllers.BionicF310;
import frc.team4909.robot.operator.generic.BionicAxis;

public class MoveElevAndStitls extends Command {
    private int startStiltPos;
    private int startElevatorPos;
    private boolean isGoingUp;
    private BionicAxis axis;

    public MoveElevAndStitls(boolean isGoingUp) {
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
        startStiltPos = Robot.climberSubsystem.getPosition();
        startElevatorPos = Robot.elevatorSubsystem.getPosition();
    }

    protected void execute() {
        SmartDashboard.putString("Climb", "Move Both Climber Gamepad");



        double speed = Robot.climberGamepad.getThresholdAxis(axis);
        speed *= RobotConstants.climbBothSpeedMultiplier;

        Robot.climberSubsystem.setStiltsClimbSpeed((isGoingUp ? 1 : -1) * speed * 2); // *2 because trigger only goes to .3

        int stiltDelta = Math.abs(startStiltPos - Robot.climberSubsystem.getPosition());

        // Elevator Drum is 1.3" Diameter, C = PI * D = Math.PI * 1.3
        // Stilts pinion gear Pitch Diameter is 1.1" which is the circumference
        int elevDelta = (int) (stiltDelta * (1.1 / 1.3));

        // the sign here is the only difference between up and down
        if (isGoingUp) {
            Robot.elevatorSubsystem.setPosition(startElevatorPos + elevDelta);
        } else {
            Robot.elevatorSubsystem.setPosition(startElevatorPos - elevDelta);  
        }
        
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
        Robot.elevatorSubsystem.setSpeed(0);
        Robot.elevatorSubsystem.updateHoldingPos();
        Robot.climberSubsystem.setSpeed(0);
        Robot.climberSubsystem.updateHoldingPos();
    }
}