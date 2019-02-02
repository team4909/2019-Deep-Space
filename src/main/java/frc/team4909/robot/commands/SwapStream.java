package frc.team4909.robot.commands;

import frc.team4909.robot.sensors.Stream;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.team4909.robot.Robot;

public class SwapStream extends InstantCommand{
    int stream = 0;
    public void StreamCommand(){
    }
    protected void initialize() {
        if (stream == 0){
            
            stream = 1;
        }
    }

}