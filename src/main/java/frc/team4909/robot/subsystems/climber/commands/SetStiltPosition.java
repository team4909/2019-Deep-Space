package frc.team4909.robot.subsystems.climber.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team4909.robot.Robot;
import frc.team4909.robot.RobotConstants;
import frc.team4909.robot.operator.controllers.BionicF310;

public class SetStiltPosition extends Command {
    private int holdingPosition, holdingPosition2;

    public SetStiltPosition(){
        requires(Robot.climberSubsystem);
        requires(Robot.elevatorSubsystem);
        holdingPosition = Robot.climberSubsystem.getPosition();
    }

     @Override
    protected void execute() {
        // double moveSpeed = -Robot.climberGamepad.getThresholdAxis(BionicF310.LY)
        //         * RobotConstants.climbSpeedMultiplier;
        double moveSpeed = -Robot.climberGamepad.getThresholdAxis(BionicF310.LY)
                * RobotConstants.climbVelocityMultiplier;
        if (moveSpeed == 0) { // If Y-stick value is not moving, HOLD position
            // Robot.climberSubsystem.setStiltsPosition(holdingPosition);
            Robot.climberSubsystem.setStiltsPosition(holdingPosition);
            Robot.elevatorSubsystem.setPosition(holdingPosition2);
            SmartDashboard.putNumber("Holding Pos", holdingPosition);
            SmartDashboard.putNumber("Move speed", moveSpeed);


        } else { // Set speed to Y-stick value and HOLD position
            // Robot.climberSubsystem.setStiltsClimbSpeed(moveSpeed);
            Robot.climberSubsystem.setStiltVelocity(moveSpeed);
            Robot.elevatorSubsystem.setVelocity(-moveSpeed);
            holdingPosition = Robot.climberSubsystem.getPosition();
            holdingPosition2 = Robot.elevatorSubsystem.getPosition();
            SmartDashboard.putNumber("Move speed", moveSpeed);



        }
        System.out.println("Climber Position " + Robot.climberSubsystem.getPosition());
    }
    @Override
    protected boolean isFinished() {
        return false;
    }


}