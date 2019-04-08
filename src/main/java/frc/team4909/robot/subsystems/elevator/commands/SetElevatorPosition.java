package frc.team4909.robot.subsystems.elevator.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.team4909.robot.subsystems.elevator.ElevatorSubsystem;
import frc.team4909.robot.Robot;
import frc.team4909.robot.RobotConstants;

public class SetElevatorPosition extends Command {
    private int height = 0; 
    private boolean goingUp = true;

    public SetElevatorPosition(boolean goingUp) {
        requires(Robot.elevatorSubsystem);
        this.goingUp = goingUp;
    }

    @Override
    protected void initialize() {

        if(goingUp){  // if going up
            height++; // increase height by 1
        }
        else { // going down
            height--; // decrease height by 1
        }

        if(height < 0){
            height = 0;
        }
        else if(height > 6){
            height = 6;
        }

        if(height == 0){
            Robot.elevatorSubsystem.setPosition(RobotConstants.elevatorSetpointHatchLow); // hatch low
        }
        else if(height == 1){
            Robot.elevatorSubsystem.setPosition(RobotConstants.elevatorSetpointCargoLow); // cargo low
        }
        else if(height == 2){
            Robot.elevatorSubsystem.setPosition(RobotConstants.elevatorSetpointCargoShip); // cargo ship
        }
        else if(height == 3){
            Robot.elevatorSubsystem.setPosition(RobotConstants.elevatorSetpointHatchMiddle); // hatch middke
        }
        else if(height == 4){
            Robot.elevatorSubsystem.setPosition(RobotConstants.elevatorSetpointCargoMiddle); // cargo middle
        }
        else if(height == 5){
            Robot.elevatorSubsystem.setPosition(RobotConstants.elevatorSetpointHatchHigh); // hatch high
        }
        else if(height == 6){
            Robot.elevatorSubsystem.setPosition(RobotConstants.elevatorSetpointCargoHigh); // cargo high
        }

        setTimeout(1);
    }
    @Override
    protected boolean isFinished() {
            return isTimedOut();
        }
        
    }

    
