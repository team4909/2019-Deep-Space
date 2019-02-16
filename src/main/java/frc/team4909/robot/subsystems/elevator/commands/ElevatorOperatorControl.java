package frc.team4909.robot.subsystems.elevator.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.team4909.robot.subsystems.elevator.ElevatorSubsystem;
import frc.team4909.robot.Robot;
import frc.team4909.robot.RobotConstants;
import frc.team4909.robot.operator.controllers.BionicF310;

public class ElevatorOperatorControl extends Command {
    private int holdingPosition;

    public ElevatorOperatorControl() {
        requires(Robot.elevatorSubsystem);
    }

    @Override
    public void execute() {
        //Sets speed to manipulator gamepad right Y stick value
        double moveSpeed = Robot.manipulatorGamepad.getThresholdAxis(BionicF310.LY) * RobotConstants.elevatorSpeedMultiplier;
        if(moveSpeed == 0 ) {  //If Y-stick value is not moving, HOLD position
            Robot.elevatorSubsystem.setPosition(holdingPosition);
            // System.out.println("Get pos is  " + Robot.elevatorSubsystem.getPosition() + ", Holding Pos is:" + holdingPosition);

        } 
        else { //Set speed to Y-stick value and HOLD position
            Robot.elevatorSubsystem.setSpeed(moveSpeed);
            holdingPosition = Robot.elevatorSubsystem.getPosition();
        }
         System.out.println("Position " + Robot.elevatorSubsystem.getPosition());
    }
    
    @Override
    protected boolean isFinished() {
        return false;
    }
}