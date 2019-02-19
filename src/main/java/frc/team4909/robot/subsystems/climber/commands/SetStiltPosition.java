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
             SmartDashboard.putString("thisblock", "1");
             Robot.climberSubsystem.setPosition(Robot.climberSubsystem.holdingStiltsPosition);
             Robot.elevatorSubsystem.setPosition(Robot.elevatorSubsystem.holdingPosition);
         }
         else if (moveSpeed != 0 && moveSpeedBoth == 0 ) { // If Y-stick value is not moving, HOLD position
            SmartDashboard.putString("thisblock", "2");
            Robot.elevatorSubsystem.setSpeed(moveSpeed);
            Robot.elevatorSubsystem.holdingPosition = Robot.elevatorSubsystem.getPosition();
         }
        else if(moveSpeed == 0 && moveSpeedBoth!= 0 ){
            SmartDashboard.putString("thisblock", "3 here "+ moveSpeedBoth);

            if (false) {
                Robot.climberSubsystem.setStiltsClimbSpeed(-moveSpeedBoth);
                Robot.elevatorSubsystem.setSpeed(moveSpeedBoth * 0.69 );

                Robot.elevatorSubsystem.holdingPosition = Robot.elevatorSubsystem.getPosition();
                Robot.climberSubsystem.holdingStiltsPosition = Robot.climberSubsystem.getPosition();
            }

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
            

            // Robot.elevatorSubsystem.setSpeed(moveSpeedBoth);
            // int stiltPos = (int) Math.floor(Robot.elevatorSubsystem.getPosition() * (1.1 / (Math.PI * 1.3)));
            // Robot.climberSubsystem.setPosition(stiltPos);

            

        } else {
            SmartDashboard.putString("thisblock", "4");
            Robot.elevatorSubsystem.setPosition(Robot.elevatorSubsystem.holdingPosition);
            Robot.climberSubsystem.setPosition(Robot.climberSubsystem.holdingStiltsPosition);
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