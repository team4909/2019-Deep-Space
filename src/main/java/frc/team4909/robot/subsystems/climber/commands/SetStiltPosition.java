package frc.team4909.robot.subsystems.climber.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4909.robot.Robot;
import frc.team4909.robot.RobotConstants;
import frc.team4909.robot.operator.controllers.BionicF310;

public class SetStiltPosition extends Command {

    public SetStiltPosition(){
        requires(Robot.climberSubsystem);
        requires(Robot.elevatorSubsystem);
    }

     @Override
    protected void execute() {
        double moveSpeed = -Robot.manipulatorGamepad.getThresholdAxis(BionicF310.LY) // elevator speed for manipulator gamepad
            * RobotConstants.elevatorSpeedMultiplier;
        
        double moveStiltSpeed = -Robot.climberGamepad.getThresholdAxis(BionicF310.RY) //Stilt Speed
            * RobotConstants.climbSpeedMultiplier;
        
        double moveElevatorSpeed = -Robot.climberGamepad.getThresholdAxis(BionicF310.LY) // elevator speed for climber gamepad
            * RobotConstants.elevatorSpeedMultiplier;
        
        double driveStiltPosSpeed = -Robot.driverGamepad.getThresholdAxis(BionicF310.RT)  // Stilt drive forward
            * RobotConstants.climberDriveSpeedManual;
        
        double driveStiltNegSpeed = Robot.driverGamepad.getThresholdAxis(BionicF310.LT)  //Stilt drive backwards
            * RobotConstants.climberDriveSpeedManual;

        double driveBothUp = -Robot.climberGamepad.getThresholdAxis(BionicF310.RT) 
            * RobotConstants.climbBothSpeedMultiplier;
        
        double driveBothDown = -Robot.climberGamepad.getThresholdAxis(BionicF310.LT)
            * RobotConstants.climbBothSpeedMultiplier;

        if(Robot.elevatorSubsystem.getPosition() < 0){
            Robot.climberSubsystem.setStiltsDriveSpeed(RobotConstants.climberDriveSpeedAuto); // Stilts via climnber
        }

       

        // no one is touching the joysticks
         if(moveSpeed==0 && moveStiltSpeed == 0 && moveElevatorSpeed == 0 && driveBothDown == 0 && driveBothUp == 0){
             Robot.climberSubsystem.setPosition(Robot.climberSubsystem.holdingStiltsPosition);
             Robot.elevatorSubsystem.setPosition(Robot.elevatorSubsystem.holdingPosition);
         }
         else if (moveSpeed != 0 && moveStiltSpeed == 0 && moveElevatorSpeed == 0 && driveBothDown == 0 && driveBothUp == 0) { // Elevator via manipulator gamepad
            Robot.elevatorSubsystem.configReverseLimitSwitch(false);
            Robot.elevatorSubsystem.setSpeed(moveSpeed);
            Robot.elevatorSubsystem.holdingPosition = Robot.elevatorSubsystem.getPosition();
         }
         else if(moveSpeed == 0 && moveStiltSpeed != 0  && moveElevatorSpeed == 0 && driveBothDown == 0 && driveBothUp == 0){ // Stilts via climber gamepad
            Robot.climberSubsystem.setStiltsClimbSpeed(moveStiltSpeed);
            Robot.climberSubsystem.holdingStiltsPosition = Robot.climberSubsystem.getPosition();
        } 
        else if(moveElevatorSpeed != 0 && moveSpeed == 0 && moveStiltSpeed == 0 && driveBothDown == 0 && driveBothUp == 0){ // Elevator via climber gamepad
            Robot.elevatorSubsystem.configReverseLimitSwitch(true);
            Robot.elevatorSubsystem.setSpeed(moveElevatorSpeed);
            Robot.elevatorSubsystem.holdingPosition = Robot.elevatorSubsystem.getPosition();       
         }
        
        else if(moveElevatorSpeed == 0 && moveSpeed == 0 && moveStiltSpeed == 0 && driveBothDown == 0 && driveBothUp != 0){
            Robot.climberSubsystem.setStiltsClimbSpeed(driveBothUp);
            // Robot.elevatorSubsystem.setSpeed(RobotConstants.liftClimbSpeed);
            // Elevator Drum is 1.3" Diameter, C = PI * D = Math.PI * 1.3
            // Stilts pinion gear Pitch Diameter is 1.1" which is the circumference
            int climberPos = Robot.climberSubsystem.getPosition();
            int elevPos = (int) (climberPos * (1.1/1.3));
            Robot.elevatorSubsystem.setPosition(elevPos);
    
            Robot.elevatorSubsystem.holdingPosition = elevPos;
            Robot.climberSubsystem.holdingStiltsPosition = climberPos;   
        }
        else if(moveElevatorSpeed == 0 && moveSpeed == 0 && moveStiltSpeed == 0 && driveBothDown != 0 && driveBothUp == 0){
            Robot.climberSubsystem.setStiltsClimbSpeed(driveBothDown);
            // Robot.elevatorSubsystem.setSpeed(-RobotConstants.liftClimbSpeed);

            // Elevator Drum is 1.3" Diameter, C = PI * D = Math.PI * 1.3
            // Stilts pinion gear Pitch Diameter is 1.1" which is the circumference
            int climberPos = Robot.climberSubsystem.getPosition();
            int elevPos = (int) (climberPos * (1.1/1.3));
            Robot.elevatorSubsystem.setPosition(elevPos);

            Robot.elevatorSubsystem.holdingPosition = elevPos;
            Robot.climberSubsystem.holdingStiltsPosition = climberPos;     
        }
        else {
            Robot.climberSubsystem.setSpeed(0);
            Robot.elevatorSubsystem.setPosition(Robot.elevatorSubsystem.holdingPosition);
            Robot.climberSubsystem.setPosition(Robot.climberSubsystem.holdingStiltsPosition);
        }
        


        if(driveStiltPosSpeed != 0 && driveStiltNegSpeed != 0) {
            Robot.climberSubsystem.setStiltsDriveSpeed(0);
        }
        else if(driveStiltPosSpeed != 0 && driveStiltNegSpeed == 0){
            Robot.climberSubsystem.setStiltsDriveSpeed(driveStiltPosSpeed);
        }
        else if(driveStiltNegSpeed != 0 && driveStiltPosSpeed == 0){
            Robot.climberSubsystem.setStiltsDriveSpeed(driveStiltNegSpeed);
        }
        else{
            Robot.climberSubsystem.setStiltsDriveSpeed(0);
        }

    }
    @Override
    protected boolean isFinished() {
        return false;
    }

    protected void interrupted()
    {
        double holdingPosition = Robot.elevatorSubsystem.holdingPosition;
        double holdingStiltsPosition = Robot.climberSubsystem.holdingStiltsPosition;

        Robot.climberSubsystem.setPosition(Robot.climberSubsystem.holdingStiltsPosition);
        Robot.elevatorSubsystem.setPosition(Robot.elevatorSubsystem.holdingPosition);
    }


    protected void end()
    {
    }



}