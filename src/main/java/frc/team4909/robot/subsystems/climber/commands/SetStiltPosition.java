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
    }

     @Override
    protected void execute() {
        double moveSpeedBoth = -Robot.climberGamepad.getThresholdAxis(BionicF310.LY)
                * RobotConstants.climbSpeedMultiplier;
        // double moveSpeed = -Robot.climberGamepad.getThresholdAxis(BionicF310.LY)
        //         * RobotConstants.climbVelocityMultiplier;
        double moveSpeed = -Robot.manipulatorGamepad.getThresholdAxis(BionicF310.LY)
        * RobotConstants.elevatorSpeedMultiplier;

// if(holdingPosition <= 0){
    // Robot.elevatorSubsystem.setInitialPIDValues();
// }
// else {
//     Robot.elevatorSubsystem.setNewPIDValues();
// }

         if(moveSpeed==0 && moveSpeedBoth == 0){
             Robot.climberSubsystem.setStiltsPosition(Robot.climberSubsystem.holdingStiltsPosition);
             Robot.elevatorSubsystem.setPosition(Robot.elevatorSubsystem.holdingPosition);
         }
         else if (moveSpeed != 0 && moveSpeedBoth == 0 ) { // If Y-stick value is not moving, HOLD position
            Robot.elevatorSubsystem.setSpeed(moveSpeed);
            Robot.elevatorSubsystem.holdingPosition = Robot.elevatorSubsystem.getPosition();
         }
        else if(moveSpeed == 0 && moveSpeedBoth!= 0 ){
            Robot.climberSubsystem.setStiltsClimbSpeed(-moveSpeedBoth);
            Robot.elevatorSubsystem.setSpeed(moveSpeedBoth * 0.69 );
            Robot.elevatorSubsystem.holdingPosition = Robot.elevatorSubsystem.getPosition();
            Robot.climberSubsystem.holdingStiltsPosition = Robot.climberSubsystem.getPosition();
        }
        else{
            Robot.elevatorSubsystem.setPosition(Robot.elevatorSubsystem.holdingPosition);
            Robot.climberSubsystem.setStiltsPosition(Robot.climberSubsystem.holdingStiltsPosition);
        }

    // System.out.println("Get pos is " + Robot.elevatorSubsystem.getPosition() + ",
    // Holding Pos is:" + holdingPosition);

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
        Robot.climberSubsystem.setStiltsPosition(Robot.climberSubsystem.holdingStiltsPosition);
        Robot.elevatorSubsystem.setPosition(Robot.elevatorSubsystem.holdingPosition);
    }

    protected void end()
    {
        SmartDashboard.putString("end", "got end");
    }



}