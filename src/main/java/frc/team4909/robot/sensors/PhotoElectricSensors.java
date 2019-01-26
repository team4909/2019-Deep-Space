package frc.team4909.robot.sensors;
import frc.team4909.robot.Robot;
import edu.wpi.first.wpilibj.DigitalInput;
public class PhotoElectricSensors{  //not sure if extends or implements
    public static DigitalInput frontLeftSensor,     
                              frontMiddleLeftSensor, 
                              frontMiddleRightSensor,
                              frontRightSensor;
    private double velocity = 0.3;
    boolean state = false;
    boolean state2 = false;
    public PhotoElectricSensors(){
        super();
        frontLeftSensor = new DigitalInput(0);
        BackLeftSensor = new DigitalInput(1);
        frontRightSensor = new DigitalInput(2);
        BackRightSensor = new DigitalInput(3);
    }

    public void onLine(){
        
    if(!frontLeftSensor.get() && !frontRightSensor.get()){
        state=true;
    }
    else {
        Robot.myDrive.tankDrive(velocity, velocity);
        lineFollow();
    }
    //  if(!frontMiddleLeftSensor.get()){
    //    System.out.println("Middle Left Sensor is ON LINE");
    //    lineFollow();
    //  }  
    //  if(!frontMiddleRightSensor.get()){
    //      System.out.println("Middle Right Sensor is ON LINE");
    //      lineFollow();
    //     }
     
    }
    
    
    //Case 1: Coming in from right side at an angle
    public void lineFollow(){
      boolean backleft = BackLeftSensor.get();
      boolean backright = BackRightSensor.get();
      boolean frontleft = frontLeftSensor.get();
      boolean frontright = frontRightSensor.get();
      int leftspeed;
      int rightspeed;
          if (backleft) { // front middle left is on life, front middle right is not on
          // line
              System.out.println(leftspeed + "  " + rightspeed + " off line to BACKRIGHT");
              leftspeed=0.3;
              rightspeed=0;
           } if (backright) { // front middle right is on line, front middle left is
                 // not on line
              System.out.println(leftspeed + "  " + rightspeed + " off line to BACKLEFT");
              rightspeed=0.3;
              leftspeed=0;
            } if (frontleft){
              System.out.println(leftspeed + "  " + rightspeed + " off line to frontleft");
              leftspeed=0.2;
              rightspeed=0;
            } if (frontright){
              System.out.println(leftspeed + "  " + rightspeed + " off line to frontright");
              rightspeed=0.2;
              leftspeed=0;
            }
            Robot.myDrive.tankDrive(leftspeed, rightspeed);
        }
    public static void case1(){

    }
    
      
}