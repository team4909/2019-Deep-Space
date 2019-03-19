package frc.team4909.robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PixyFollow extends Command {

    String a = "data";
    public PixyFollow(){
    }
    @Override
    protected void execute() {
        
        Robot.arduino.writeString(a);  
        int response = Robot.arduino.getBytesReceived();
        SmartDashboard.putNumber("Response from arduino", response);
        
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

}