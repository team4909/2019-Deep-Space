package frc.team4909.robot.subsystems.climber.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team4909.robot.Robot;
import frc.team4909.robot.RobotConstants;
import frc.team4909.robot.operator.controllers.BionicF310;

public class SetStiltPosition extends Command {

    public SetStiltPosition(){
        requires(Robot.climberSubsystem);
        requires(Robot.elevatorSubsystem);
    }

    protected void initialize() {
        SmartDashboard.putString("end", "waiting");
        SmartDashboard.putString("interrupted", "waiting");
        SmartDashboard.putString("thisblock", "waiting");
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

        if(Robot.elevatorSubsystem.getPosition() > 0){
            Robot.climberSubsystem.setStiltsDriveSpeed(RobotConstants.climberDriveSpeedAuto); // Stilts via climnber
        }

       

        // no one is touching the joysticks
         if(moveSpeed==0 && moveStiltSpeed == 0 && moveElevatorSpeed == 0){
             SmartDashboard.putString("thisblock", "1");

             Robot.climberSubsystem.setPosition(Robot.climberSubsystem.holdingStiltsPosition);
             Robot.elevatorSubsystem.setPosition(Robot.elevatorSubsystem.holdingPosition);
         }
         else if (moveSpeed != 0 && moveStiltSpeed == 0 && moveElevatorSpeed == 0 ) { // Elevator via manipulator gamepad
            SmartDashboard.putString("thisblock", "2");

            Robot.elevatorSubsystem.setSpeed(moveSpeed);
            Robot.elevatorSubsystem.holdingPosition = Robot.elevatorSubsystem.getPosition();
         }
         else if(moveSpeed == 0 && moveStiltSpeed != 0  && moveElevatorSpeed == 0){ // Stilts via climber gamepad
            SmartDashboard.putString("thisblock", "3 here "+ moveStiltSpeed);

            Robot.climberSubsystem.setStiltsClimbSpeed(moveStiltSpeed);
            Robot.climberSubsystem.holdingStiltsPosition = Robot.climberSubsystem.getPosition();
        } 
        else if(moveElevatorSpeed != 0 && moveSpeed == 0 && moveStiltSpeed == 0){ // Elevator via climber gamepad
            SmartDashboard.putString("thisblock", "5 here "+ moveElevatorSpeed);

            Robot.elevatorSubsystem.setSpeed(moveElevatorSpeed);
            Robot.elevatorSubsystem.holdingPosition = Robot.elevatorSubsystem.getPosition();        }
        else {
            SmartDashboard.putString("thisblock", "4"); // hold positions
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
        SmartDashboard.putString("interrupted",
                "got interrupted: el=" + holdingPosition + ", st=" + holdingStiltsPosition);
        Robot.climberSubsystem.setPosition(Robot.climberSubsystem.holdingStiltsPosition);
        Robot.elevatorSubsystem.setPosition(Robot.elevatorSubsystem.holdingPosition);
        SmartDashboard.putString("thisblock", "interr");
    }


    protected void end()
    {
        SmartDashboard.putString("end", "got end");
        SmartDashboard.putString("thisblock", "end");
    }



}