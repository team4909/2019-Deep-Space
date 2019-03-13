package frc.team4909.robot.subsystems.climber.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team4909.robot.Robot;
import frc.team4909.robot.RobotConstants;

public class BothLiftDown extends Command{

    public BothLiftDown(){
        requires(Robot.climberSubsystem);
        requires(Robot.elevatorSubsystem);
    }

    public void execute() {
        Robot.climberSubsystem.setStiltsClimbSpeed(-RobotConstants.liftClimbSpeed);
        // Robot.elevatorSubsystem.setSpeed(-RobotConstants.liftClimbSpeed);

        // Elevator Drum is 1.3" Diameter, C = PI * D = Math.PI * 1.3
        // Stilts pinion gear Pitch Diameter is 1.1" which is the circumference
        int climberPos = Robot.climberSubsystem.getPosition();
        int elevPos = (int) -(climberPos * (1.1/1.3));
        Robot.elevatorSubsystem.setPosition(elevPos);

        Robot.elevatorSubsystem.holdingPosition = elevPos;
        Robot.climberSubsystem.holdingStiltsPosition = climberPos;     
    }

    public void end(){
        Robot.climberSubsystem.setStiltsClimbSpeed(0);
        Robot.elevatorSubsystem.setSpeed(0);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
    
}