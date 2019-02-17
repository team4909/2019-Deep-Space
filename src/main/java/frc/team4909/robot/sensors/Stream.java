/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team4909.robot.sensors;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import org.opencv.core.*;
import frc.team4909.robot.operator.controllers.*;
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoSink;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.cameraserver.CameraServer;
//import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.networktables.NetworkTable;

//Init of Camera and Bandwidth control.

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class Stream extends Subsystem{
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  boolean seeHatchCam = true;
    
  //Max Bandwidth: 4mbps, 1.6 for each camera.
  public Stream(){
    
  };

  public void toggleCamera(){
      seeHatchCam = !seeHatchCam;
  }

  VideoSink intakeServer = CameraServer.getInstance().getServer("Intake Camera");
  UsbCamera camera1, camera2, camera3, camera4;

  CvSink cvSink1;
  CvSource outputStream;
  CvSink cvSink2;
  CvSource outputStream2;
  CvSink cvSink3;
  CvSource outputStream3;
  CvSink cvSink4;
  CvSource outputStream4;
  
  Mat source1 = new Mat();
  Mat source2 = new Mat();
  Mat output1 = new Mat();
  Mat output2 = new Mat();
  Mat source3 = new Mat();
  Mat source4 = new Mat();
  Mat output3 = new Mat();
  Mat output4 = new Mat();
  Mat hatchWatchMatrix;
  int height1, length1;

  public void initialize(){
      //Cameras 3 and 4 have mutual toggle function.
      intakeServer = CameraServer.getInstance().getServer();
      camera1 = CameraServer.getInstance().startAutomaticCapture(0); //Function: 
      camera2 = CameraServer.getInstance().startAutomaticCapture(1); //Function: 
      camera3 = CameraServer.getInstance().startAutomaticCapture(2); //Function: 
      camera4 = CameraServer.getInstance().startAutomaticCapture(3); //Function: 
      camera1.setResolution(160, 120);
      camera2.setResolution(160, 120);
      camera1.setFPS(12);
      camera2.setFPS(12);
      cvSink1 = CameraServer.getInstance().getVideo();
      outputStream = CameraServer.getInstance().putVideo("Source 1", 160, 120);
      cvSink2 = CameraServer.getInstance().getVideo();
      outputStream2 = CameraServer.getInstance().putVideo("Source 2", 160, 120);
      camera3.setResolution(160, 120);
      camera4.setResolution(160, 120);
      camera3.setFPS(12);
      camera4.setFPS(12);
      cvSink3 = CameraServer.getInstance().getVideo();
      outputStream3 = CameraServer.getInstance().putVideo("Source 3", 160, 120);
      cvSink4 = CameraServer.getInstance().getVideo();
      outputStream4 = CameraServer.getInstance().putVideo("Source 4", 160, 120);

      source1 = new Mat();
      source2 = new Mat();
      output1 = new Mat();
      output2 = new Mat();
      source3 = new Mat();
      source4 = new Mat();
      output3 = new Mat();
      output4 = new Mat();
      
      Mat image1 = new Mat(cvSink1.grabFrame(source1));
      height1 = image1.rows();
      length1 = image1.cols();
      MatOfPoint2f src = new MatOfPoint2f(
                new Point((1/6)*height1, 0),
                new Point((5/6)*height1,0),
                new Point(height1-1,length1-1),
                new Point(0,length1-1)
                );

      MatOfPoint2f dst = new MatOfPoint2f(
                new Point(0, 0),
                new Point(height1-1,0),
                new Point(0,length1-1),
                new Point(height1-1,length1-1)      
                );
      hatchWatchMatrix = Imgproc.getPerspectiveTransform(src, dst);
      
      }
    public void streamLoop(){
          if (seeHatchCam == true){
              //Show Camera for Hatches
              intakeServer.setSource(camera3);
          } else if (seeHatchCam == false) {
              //Show Cargo Intake Camera
              intakeServer.setSource(camera4);
          }

          System.out.println("Source for intake Cam: " + intakeServer.getName());
    
    
          cvSink1.grabFrame(source1);
          Imgproc.warpPerspective(source1, output1, hatchWatchMatrix, new Size(height1, length1));
          //Imgproc.cvtColor(source1, output1, Imgproc.COLOR_BGR2HSV);
          outputStream.putFrame(output1);
          System.out.println(height1);
          //cvSink2.grabFrame(source2);
          //Imgproc.cvtColor(source2, output2, Imgproc.COLOR_BGR2HSV);
          //outputStream2.putFrame(output2);
          //cvSink3.grabFrame(source3);
          //Imgproc.cvtColor(source3, output3, Imgproc.COLOR_BGR2HSV);
          //outputStream3.putFrame(output3);
          //cvSink4.grabFrame(source4);
          //Imgproc.cvtColor(source4, output4, Imgproc.COLOR_BGR2HSV);
          //outputStream4.putFrame(output4);
    }

    protected void initDefaultCommand(){
        streamLoop();
    }
}
