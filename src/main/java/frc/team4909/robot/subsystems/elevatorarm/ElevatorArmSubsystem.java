package frc.team4909.robot.subsystems.elevatorarm;

import frc.team4909.robot.Robot;
import frc.team4909.robot.RobotConstants;
import frc.team4909.robot.RobotMap;
import frc.team4909.robot.operator.controllers.BionicF310;

import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.command.Subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class ElevatorArmSubsystem extends Subsystem{
    TalonSRX elevatorArmSRX;

    public int holdingPosition;

    public ElevatorArmSubsystem(){
        //Elevator arm
        elevatorArmSRX = new TalonSRX(RobotMap.elevatorArmSRXID);

        elevatorArmSRX.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
        elevatorArmSRX.setNeutralMode(NeutralMode.Brake);
        elevatorArmSRX.configContinuousCurrentLimit(3);
        elevatorArmSRX.configPeakCurrentLimit(6);
        elevatorArmSRX.selectProfileSlot(0, 0);
        elevatorArmSRX.config_kP(0, 0.5, 0);
        elevatorArmSRX.config_kI(0, 0);
        elevatorArmSRX.config_kD(0, 0.5, 0);
    }


    public InstantCommand setHeight(int height){
        return new SetAngle(height, this);
    }

    public void holdCurrentPosition(){
        holdingPosition = elevatorArmSRX.getSelectedSensorPosition();
    }
    public int getPosition(){
        return elevatorArmSRX.getSelectedSensorPosition();
    }

    public void elevatorArmSetSpeed(double speed){
        elevatorArmSRX.set(ControlMode.PercentOutput, speed);
    }
    public void setPosition(double position){
        elevatorArmSRX.set(ControlMode.Position, position);
    }
    

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new ElevatorArmOperatorControl());
    }


}