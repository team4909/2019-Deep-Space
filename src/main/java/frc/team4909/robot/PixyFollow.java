package frc.team4909.robot;

import edu.wpi.first.wpilibj.command.Command;

public class PixyFollow extends Command {

    public PixyFollow(){
    }
    @Override
    protected void execute() {
        Robot.arduino.writeString("give me data");  
        

        
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

}