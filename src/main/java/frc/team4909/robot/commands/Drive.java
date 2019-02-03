package frc.team4909.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4909.robot.Robot;
import frc.team4909.robot.operator.controllers.BionicF310;

public class Drive extends Command{
    public Drive(){
        requires(Robot.drivetrainSubsystem);
    }

    public void execute(){
        // Calls tank function using left Y and right Y
        // Negated since Y Axis is scaled -1 to +1 top to bottom (counterintuively)
        Robot.drivetrainSubsystem.tankDrive(
            -Robot.driverGamepad.getThresholdAxis(BionicF310.LY), 
            -Robot.driverGamepad.getThresholdAxis(BionicF310.RY)
        );
  
    }
    
    @Override
    protected boolean isFinished() {
        return false;
    }
}