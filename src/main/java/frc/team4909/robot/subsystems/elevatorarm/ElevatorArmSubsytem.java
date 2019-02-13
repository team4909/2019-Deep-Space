package frc.team4909.robot.subsystems.elevatorarm;

import frc.team4909.robot.Robot;
import frc.team4909.robot.RobotConstants;
import frc.team4909.robot.RobotMap;
import frc.team4909.robot.operator.controllers.BionicF310;

import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.command.Subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class ElevatorArmSubsytem extends Subsystem{
    TalonSRX elevatorArmSRX;

    public int holdingPosition;

    public ElevatorArmSubsytem(){
        //Elevator arm
        elevatorArmSRX = new TalonSRX(RobotMap.elevatorArmSRXID);

        elevatorArmSRX.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
 
        elevatorArmSRX.configContinuousCurrentLimit(3);
        elevatorArmSRX.configPeakCurrentLimit(6);
        
        elevatorArmSRX.config_kP(0,1, 0);
        elevatorArmSRX.config_kI(0,0);
        elevatorArmSRX.config_kD(0,30, 0);
    }

    @Override
    public void periodic() {
        //Sets speed to manipulator gamepad right Y stick value
        double moveSpeed = -Robot.manipulatorGamepad.getThresholdAxis(BionicF310.LY) * RobotConstants.elevatorArmSpeedMultiplier;

        if(moveSpeed == 0) {  //If Y-stick value is not moving, HOLD position
            elevatorArmSRX.set(ControlMode.Position, holdingPosition);
        } 
        else{ //Set speed to Y-stick value and HOLD position
            elevatorArmSRX.set(ControlMode.PercentOutput, moveSpeed);
            holdCurrentPosition();
        }
    }

    public InstantCommand setHeight(int height){
        return new SetAngle(height, this);
    }

    public void holdCurrentPosition(){
        holdingPosition = elevatorArmSRX.getSelectedSensorPosition();
    }

    public void elevatorArmSetSpeed(double speed){
        elevatorArmSRX.set(ControlMode.PercentOutput, speed);
    }

    @Override
    protected void initDefaultCommand() {

    }


}