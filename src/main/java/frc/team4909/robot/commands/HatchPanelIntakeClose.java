package frc.team4909.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.team4909.robot.Robot;

public class HatchPanelIntakeClose extends InstantCommand{
    
    public void HatchPanelIntakeClose(){
        requires(Robot.intakeSubsystem);
    }
    protected void initialize() {
        Robot.intakeSubsystem.hatchPanelIntakeClose();
    }

}