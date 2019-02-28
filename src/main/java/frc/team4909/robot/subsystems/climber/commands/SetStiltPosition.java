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
        double moveSpeedBoth = -Robot.climberGamepad.getThresholdAxis(BionicF310.LY)
        * RobotConstants.climbSpeedMultiplier;

        double moveSpeed = -Robot.manipulatorGamepad.getThresholdAxis(BionicF310.LY)
        * RobotConstants.elevatorSpeedMultiplier;

        double moveDrivePosSpeed = -Robot.driverGamepad.getThresholdAxis(BionicF310.RT) 
        * RobotConstants.climberDriveSpeedManual;
        
        double moveDriveNegSpeed = Robot.driverGamepad.getThresholdAxis(BionicF310.LT) 
        * RobotConstants.climberDriveSpeedManual;

        double moveElevatorSpeed = -Robot.climberGamepad.getThresholdAxis(BionicF310.RY)
        * RobotConstants.elevatorSpeedMultiplier;

        // Set constant slow drive speed for wheels
        Robot.climberSubsystem.setStiltsDriveSpeed(RobotConstants.climberDriveSpeedAuto);
        

         if(moveSpeed==0 && moveSpeedBoth == 0 && moveElevatorSpeed == 0){
             SmartDashboard.putString("thisblock", "1");
             Robot.climberSubsystem.setPosition(Robot.climberSubsystem.holdingStiltsPosition);
             Robot.elevatorSubsystem.setPosition(Robot.elevatorSubsystem.holdingPosition);
         }
         else if (moveSpeed != 0 && moveSpeedBoth == 0 && moveElevatorSpeed == 0 ) { // If Y-stick value is not moving, HOLD position
            SmartDashboard.putString("thisblock", "2");
            Robot.elevatorSubsystem.setSpeed(moveSpeed);
            Robot.elevatorSubsystem.holdingPosition = Robot.elevatorSubsystem.getPosition();
         }
         else if(moveSpeed == 0 && moveSpeedBoth != 0  && moveElevatorSpeed == 0){
            SmartDashboard.putString("thisblock", "3 here "+ moveSpeedBoth);

            // Elevator Drum is 1.3" Diameter, C = PI * D = Math.PI * 1.3
            // Stilts pinion gear Pitch Diameter is 1.1" which is the circumference

            Robot.climberSubsystem.setStiltsClimbSpeed(-moveSpeedBoth);
            int climberPos = Robot.climberSubsystem.getPosition();
            int elevPos = (int) -(climberPos * (1.1/1.3));
            
            // (int) Math.floor(climberPos * ((Math.PI * 1.3) / 1.1));
            SmartDashboard.putNumber("Elevator Pos calculated", elevPos);
            // SmartDashboard.putNumber("Climber Pos", climberPos);
            Robot.elevatorSubsystem.setPosition(elevPos);

            Robot.elevatorSubsystem.holdingPosition = elevPos;
            Robot.climberSubsystem.holdingStiltsPosition = climberPos;


            //@todo how can we make holdingpositon private?
            

        } 
        else if(moveElevatorSpeed != 0 && moveSpeed == 0 && moveSpeedBoth == 0){
            SmartDashboard.putString("thisblock", "5 here "+ moveElevatorSpeed);

            Robot.elevatorSubsystem.setSpeed(moveElevatorSpeed);
            Robot.elevatorSubsystem.holdingPosition = Robot.elevatorSubsystem.getPosition();        }
        else {
            SmartDashboard.putString("thisblock", "4");
            Robot.elevatorSubsystem.setPosition(Robot.elevatorSubsystem.holdingPosition);
            Robot.climberSubsystem.setPosition(Robot.climberSubsystem.holdingStiltsPosition);
        }
        


        if(moveDrivePosSpeed != 0 && moveDriveNegSpeed != 0) {
            Robot.climberSubsystem.setStiltsDriveSpeed(0);
        }
        else if(moveDrivePosSpeed != 0 && moveDriveNegSpeed == 0){
            Robot.climberSubsystem.setStiltsDriveSpeed(moveDrivePosSpeed);
        }
        else if(moveDriveNegSpeed != 0 && moveDrivePosSpeed == 0){
            Robot.climberSubsystem.setStiltsDriveSpeed(moveDriveNegSpeed);
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