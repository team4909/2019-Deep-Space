package frc.team4909.robot.sensors;

// import frc.team4909.robot.operator.*;
import edu.wpi.cscore.*;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.team4909.robot.sensors.Stream;
import frc.team4909.robot.Robot;

public class ToggleCamera extends InstantCommand{
    public ToggleCamera(){

    }

    protected void execute(){ //part of superclass.
        Robot.stream.toggleCamera();
    }

}