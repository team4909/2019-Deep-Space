/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team4909.robot.sensors;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;

import edu.wpi.first.cameraserver.CameraServer;
//import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;

//Init of Camera and Bandwidth control.

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class Stream {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  //Max Bandwidth: 4mbps, 1.6 for each camera.
  public Stream(){
    
  };
  public void streamCamera(){
    //Camera1 = CameraServer.getInstance().startAutomaticCapture(0);
    //Camera2 = CameraServer.getInstance().startAutomaticCapture(1);
    //Camera1.setResolution(320, 240);
    //Camera2.setResolution(320, 240);

    new Thread(() -> {
      UsbCamera camera1 = CameraServer.getInstance().startAutomaticCapture(0);
      UsbCamera camera2 = CameraServer.getInstance().startAutomaticCapture(1);
      camera1.setResolution(640, 480);
      camera2.setResolution(160, 120);
      camera1.setFPS(12);
      camera2.setFPS(20);
      CvSink cvSink = CameraServer.getInstance().getVideo();
      CvSource outputStream = CameraServer.getInstance().putVideo("Blur", 640, 480);
      CvSink cvSink2 = CameraServer.getInstance().getVideo();
      CvSource outputStream2 = CameraServer.getInstance().putVideo("Blur", 640, 480);
      
      Mat source1 = new Mat();
      Mat source2 = new Mat();
      Mat output1 = new Mat();
      Mat output2 = new Mat();

      while(!Thread.interrupted()) {
          cvSink.grabFrame(source1);
          Imgproc.cvtColor(source1, output1, Imgproc.COLOR_BGR2HSV);
          outputStream.putFrame(output1);
          cvSink2.grabFrame(source2);
          Imgproc.cvtColor(source2, output2, Imgproc.COLOR_BGR2HSV);
          outputStream2.putFrame(output2);
      }
  }).start();
  }
}
