package frc.team4909.robot.vision.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.team4909.robot.Robot;

public class SetLights extends Command {

    public SetLights(){
        // requires(Robot.vision);
    }

    @Override
    protected void initialize() {
        Robot.vision.setLights(3);
    }

    @Override
    protected void end() {
        Robot.vision.setLights(1);
    }
    @Override
    protected boolean isFinished() {
        return false;
    }


}