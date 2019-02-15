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
    WPI_VictorSPX leftSPX, rightSRX1, rightSRX2;
    WPI_TalonSRX leftSRX;

    public int holdingPosition;

    public ElevatorSubsystem() {

        //Lift
        leftSRX = new WPI_TalonSRX(RobotMap.elevatorSRXID);  //master SRX
        leftSPX = new WPI_VictorSPX(RobotMap.elevatorSPX1ID); //slave SPX 1
        rightSRX1 = new WPI_VictorSPX(RobotMap.elevatorSPX2ID); //slave SPX 2
        rightSRX2 = new WPI_VictorSPX(RobotMap.elevatorSPX3ID); //slave SPX 3

        

        leftSPX.follow(leftSRX);  //All slaves will output the same value as the master SRX
        rightSRX1.follow(leftSRX);  
        rightSRX2.follow(leftSRX);
        
        rightSRX1.setInverted(true);
        rightSRX2.setInverted(true);
        leftSRX.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
        leftSRX.config_kP(1, 0.6, 0);
        leftSPX.config_kI(1, 0);
        leftSPX.config_kD(1, 0);
    }

    @Override
    public void periodic() {
        //Sets speed to manipulator gamepad right Y stick value
        double moveSpeed = Robot.manipulatorGamepad.getThresholdAxis(BionicF310.LY) * RobotConstants.elevatorSpeedMultiplier;

        if(moveSpeed == 0 ) {  //If Y-stick value is not moving, HOLD position
            leftSRX.set(ControlMode.Position, holdingPosition);
        } 
        else{ //Set speed to Y-stick value and HOLD position
            leftSRX.set(ControlMode.PercentOutput, moveSpeed);
            //leftSRX.pidWrite(output);
            holdCurrentPosition();
        }
    }

    public InstantCommand holdPosition(int height){  //sets elevator height
        return new SetElevatorPosition(height);
    }

    public void holdCurrentPosition(){  //hold elevator in position
            holdingPosition = leftSRX.getSelectedSensorPosition();
    }
    
    public void elevatorSetSpeed(double speed){  //set elevator speed value
        leftSRX.set(ControlMode.PercentOutput, speed);
    }

    @Override
    protected void initDefaultCommand() {

    }
}