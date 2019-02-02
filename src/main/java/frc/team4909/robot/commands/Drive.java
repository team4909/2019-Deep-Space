package frc.team4909.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4909.robot.Robot;
import frc.team4909.robot.operator.controllers.BionicF310;
public class Drive extends Command{
    double speedMultiplier;
    
    public Drive(){
        requires(Robot.drivetrainsub);
        speedMultiplier = 1.0;
    }

    public void execute(){
        Robot.drivetrainsub.tankDrive(-Robot.driverGamepad.getThresholdAxis(BionicF310.LY) * speedMultiplier, -Robot.driverGamepad.getThresholdAxis(BionicF310.RY) * speedMultiplier); //Calls tank function using left Y and right Y
  
    }
    @Override
    protected boolean isFinished() {
        return false;
    }
}