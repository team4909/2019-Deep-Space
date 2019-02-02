package frc.team4909.robot.sensors;
/*import frc.team4909.robot.Robot;
import edu.wpi.first.wpilibj.DigitalInput;
public class PhotoElectricSensors{  //not sure if extends or implements
    public static DigitalInput frontLeftSensor, 
                              frontMiddleLeftSensor, 
                              frontMiddleRightSensor,
                              frontRightSensor;
    private double velocity = 0.7;
    boolean frontLeftTripped = false;
    boolean frontMiddleRightTripped = false;
    
   
    public PhotoElectricSensors(){
        frontLeftSensor = new DigitalInput(0);
        frontMiddleLeftSensor = new DigitalInput(1);
        frontMiddleRightSensor = new DigitalInput(2);
        frontRightSensor = new DigitalInput(3);
    }

    public void onLine(){
    boolean frontLeftNotOnLine = frontLeftSensor.get(); // true if sensor is not on line
    boolean frontMiddleLeftNotOnLine = frontMiddleLeftSensor.get(); // false if sensor is on line
    boolean frontMiddleRightNotOnLine = frontMiddleRightSensor.get();
    boolean frontRightNotOnLine= frontRightSensor.get();
    
    //Robot.drivetrainsub.arcadeDrive(velocity,velocity);
    //Case 1: Coming in from right side at an angle
    if(!frontLeftNotOnLine) {
      frontLeftTripped = true;
    }
      if(frontMiddleRightNotOnLine) {
        //frontRightMiddleTripped = true;
      }
      if(!frontMiddleRightTripped) {
        Robot.drivetrainsub.arcadeDrive(velocity,velocity);
      }
      else{
            if (frontMiddleRightTripped && frontLeftTripped) { // front middle left is on life, front middle right is not on
          // line
            Robot.drivetrainsub.arcadeDrive(velocity, velocity - 0.1);
          } else if (frontMiddleLeftNotOnLine && !frontLeftNotOnLine) { // front middle right is on line, froint middle left is
                 // not on line
            Robot.drivetrainsub.arcadeDrive(velocity - 0.1, velocity);
          }
          else {
            Robot.drivetrainsub.arcadeDrive(velocity, velocity);
          }
        }
      }
    //Case 2: 
    
}

*/

