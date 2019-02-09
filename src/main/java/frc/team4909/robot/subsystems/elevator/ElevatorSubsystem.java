package frc.team4909.robot.subsystems.elevator;

import edu.wpi.first.wpilibj.Encoder;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.command.Subsystem;

import java.lang.module.ModuleDescriptor.Requires;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team4909.robot.subsystems.elevator.commands.SetElevatorPosition;
import frc.team4909.robot.Robot;
import frc.team4909.robot.RobotConstants;
import frc.team4909.robot.RobotMap;
import frc.team4909.robot.operator.controllers.BionicF310;

public class ElevatorSubsystem extends Subsystem{
    WPI_VictorSPX VictorSPX1, VictorSPX2, VictorSPX3;
    WPI_TalonSRX masterSRX;

    public int holdingPosition;

    public ElevatorSubsystem() {

        //Lift
        masterSRX = new WPI_TalonSRX(RobotMap.elevatorSRXID);  //master SRX
        VictorSPX1 = new WPI_VictorSPX(RobotMap.elevatorSPX1ID); //slave SPX 1
        VictorSPX2 = new WPI_VictorSPX(RobotMap.elevatorSPX2ID); //slave SPX 2
        VictorSPX3 = new WPI_VictorSPX(RobotMap.elevatorSPX3ID); //slave SPX 3

        

        VictorSPX1.follow(masterSRX);  //All slaves will output the same value as the master SRX
        VictorSPX3.follow(masterSRX);  
        VictorSPX2.follow(masterSRX);
        
        VictorSPX2.setInverted(true);
        VictorSPX3.setInverted(true);
        masterSRX.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);

    }

    @Override
    public void periodic() {
        //Sets speed to manipulator gamepad right Y stick value
        double moveSpeed = Robot.manipulatorGamepad.getThresholdAxis(BionicF310.RY) * RobotConstants.elevatorSpeedMultiplier;

        if(moveSpeed == 0 ) {  //If Y-stick value is not moving, HOLD position
            masterSRX.set(ControlMode.Position, holdingPosition);
        } 
        else{ //Set speed to Y-stick value and HOLD position
            masterSRX.set(ControlMode.PercentOutput, moveSpeed);
            holdCurrentPosition();
        }
    }

    public InstantCommand holdPosition(int height){  //sets elevator height
        return new SetElevatorPosition(height);
    }

    public void holdCurrentPosition(){  //hold elevator in position
            holdingPosition = masterSRX.getSelectedSensorPosition();
    }
    
    public void elevatorSetSpeed(double speed){  //set elevator speed value
        masterSRX.set(ControlMode.PercentOutput, speed);
    }

    @Override
    protected void initDefaultCommand() {

    }
}