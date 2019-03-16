package frc.team4909.robot.sensors;


import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoSink;
import edu.wpi.cscore.VideoSource;
import edu.wpi.first.cameraserver.CameraServer;
//Init of Camera and Bandwidth control.
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTable;

public class Stream {

  boolean seeHatchCam = true;
  public void toggleCamera(){
      seeHatchCam = !seeHatchCam;
  }
  //Max Bandwidth: 4mbps, 1.6 for each camera.
  public Stream(){
    
  };
  public void streamCamera(){
    new Thread(() -> {
      //Instantiate Cameras
      UsbCamera camera0 = CameraServer.getInstance().startAutomaticCapture(0); //Function: Drive
      UsbCamera camera1 = CameraServer.getInstance().startAutomaticCapture(1); //Function: Line
      UsbCamera camera2 = CameraServer.getInstance().startAutomaticCapture(2); //Function: Hatch 
      UsbCamera camera3 = CameraServer.getInstance().startAutomaticCapture(3); //Function: Cargo

      //Set Camera 0 Resolution and FPS
      camera0.setResolution(160, 120);    
      camera0.setFPS(18);                   

      //Set Camera 1 Resolution and FPS
      camera1.setResolution(160, 120);    
      camera1.setFPS(10);                 

      //Set Camera 2 Resolution and FPS
      camera2.setResolution(160, 120);    
      camera2.setFPS(6);                 

      //Set Camera 3 Resolution and FPS
      camera3.setResolution(160, 120);    
      camera3.setFPS(12);      

      // CameraServer intakeServer = CameraServer.getInstance();//.addCamera(intakeServer);
      
      // CvSink cvSink1 = CameraServer.getInstance().getVideo();
      // CvSource outputStream = CameraServer.getInstance().putVideo("Source 1", 160, 120);
      // CvSink cvSink2 = CameraServer.getInstance().getVideo();
      // CvSource outputStream2 = CameraServer.getInstance().putVideo("Source 2", 160, 120);
      // CvSink cvSink3 = CameraServer.getInstance().getVideo();
      // CvSource outputStream3 = CameraServer.getInstance().putVideo("Source 3", 160, 120);
      // CvSink cvSink4 = CameraServer.getInstance().getVideo();
      // CvSource outputStream4 = CameraServer.getInstance().putVideo("Source 4", 160, 120);

      //Toggle Command In progress.
  //     while(!Thread.interrupted()) {
  //         if (seeHatchCam){
  //             //Show Camera for Hatches
  //             intakeServer.addCamera(camera2);
  //             intakeServer.removeCamera(camera3.getName());

  //         } else {
  //             //Show Cargo Intake Camera
  //             intakeServer.addCamera(camera3);
  //             intakeServer.removeCamera(camera2.getName());
  //         }
  // }
  }).start();
  }
}