/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team4909.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;


/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class ExampleSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

/*
#include <Wire.h>


const int lidar = 0;
const int rio = 1;
int i = 0;

void setup() {
  // put your setup code here, to run once:
  Serial.begin(115200);
  pinMode(lidar, INPUT);
  pinMode(rio, OUTPUT);
  Wire.begin();
}

void loop() {
  // put your main code here, to run repeatedly:
  int bytes[1000];
  while (Serial.available()>0)
  {
    int bytex = digitalRead(lidar);
    bytes[i] = bytex;
    Serial.print(bytex);
    i++;
  }
   for (int i=0; i<=sizeof(bytes); i++)
   {
      Wire.write((byte)bytes[i]);
   }
  }

*/

  
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

 
    
  }

