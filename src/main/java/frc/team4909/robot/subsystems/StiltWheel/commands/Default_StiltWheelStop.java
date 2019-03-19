package frc.team4909.robot.subsystems.stiltwheel.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4909.robot.Robot;

public class Default_StiltWheelStop extends Command {

    public Default_StiltWheelStop()
    {
        requires(Robot.stiltWheelSubsystem);
    }
    
    @Override
    protected void initialize() {
        Robot.stiltWheelSubsystem.setSpeed(0);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}