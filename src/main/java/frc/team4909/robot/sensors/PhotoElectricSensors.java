package frc.team4909.robot.sensors;
import frc.team4909.robot.Robot;
import edu.wpi.first.wpilibj.DigitalInput;
public class PhotoElectricSensors extends DigitalInput{  //not sure if extends or implements
    public static DigitalInput frontLeftSensor, 
                             frontMiddleLeftSensor, 
                             frontMiddleRightSensor,
                             frontRightSensor;
    double velocity = 0.7;
                             
   
    public PhotoElectricSensors(){
        frontLeftSensor = new DigitalInput(0);
        frontMiddleLeftSensor = new DigitalInput(1);
        frontMiddleRightSensor = new DigitalInput(2);
        frontRightSensor = new DigitalInput(3);
    }

    public void onLine(){
    boolean frontLeftOnLine = frontLeftSensor.get(); // true if sensor is not on line
    boolean frontMiddleLeftOnLine = frontMiddleLeftSensor.get(); // false if sensor is on line
    boolean frontMiddleRightOnLine = frontMiddleRightSensor.get();
    boolean frontRightOnLine= frontRightSensor.get();

    if(!frontMiddleLeftOnLine && frontMiddleRightOnLine){  //front middle left is on life, front middle right is not on line
      Robot.drivetrainsub.arcadeDrive(velocity, velocity - 0.1);
    }
    else if(frontMiddleLeftOnLine && !frontMiddleRightOnLine){  // front middle right is on line, froint middle left is not on line
      Robot.drivetrainsub.arcadeDrive(velocity - 0.1, velocity);
    }
    else {
      Robot.drivetrainsub.arcadeDrive(velocity, velocity);
    }
    }


}