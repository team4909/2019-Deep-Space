package frc.team4909.robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PixyFollow extends Command {

    String str = "y";    
    public PixyFollow(){
    }
    @Override
    protected void initialize() {
        System.out.println("Command is called");
    }
    @Override
    protected void execute() {
        
        Robot.arduino.writeString(str); 
        byte[] arr = Robot.arduino.read(1);

        if(arr[0] == 1){ // case 1 does not find line
            Robot.myDrive.tankDrive(0.2, 0.2);
            SmartDashboard.putString("Case", "1");
            
        }
        else if(arr[0] == 2){ // veering off to right, want to move left
            Robot.myDrive.tankDrive(0.4, 0.2);
            SmartDashboard.putString("Case", "2");

        }
        else if(arr[0] == 3){ // veering off to left, want to move right
            Robot.myDrive.tankDrive(0.2, 0.4); 
            SmartDashboard.putString("Case", "3");

        }
        else{ // drive normal
            Robot.myDrive.tankDrive(0.2,0.2);
            SmartDashboard.putString("Case", "4");

        }

    }

    @Override
    protected boolean isFinished() {
        return false;
    }

}