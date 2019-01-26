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
public class Stream extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  //Max Bandwidth: 4mbps, 1.6 for each camera.
  public Stream(){};
  public void streamCamera(){
    //Camera1 = CameraServer.getInstance().startAutomaticCapture(0);
    //Camera2 = CameraServer.getInstance().startAutomaticCapture(1);
    //Camera1.setResolution(320, 240);
    //Camera2.setResolution(320, 240);

    new Thread(() -> {
                UsbCamera camera = CameraServer.getInstance().startAutomaticCapture(0);
                camera.setResolution(320, 240);
                //camera.setConnectionStrategy();
                //UsbCamera camera2 = CameraServer.getInstance().startAutomaticCapture(1);
                //camera2.setResolution(320, 240);

                CvSink cvSink1 = CameraServer.getInstance().getVideo();
                CvSource outputStream = CameraServer.getInstance().putVideo("Stream", 320, 240);
                
                Mat source = new Mat();
                Mat output = new Mat();
                
                while(!Thread.interrupted()) {
                    cvSink1.grabFrame(source);
                    Imgproc.cvtColor(source, output, Imgproc.COLOR_BGR2GRAY);
                    outputStream.putFrame(output);
                }
            }).start();
  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());

  }
}
