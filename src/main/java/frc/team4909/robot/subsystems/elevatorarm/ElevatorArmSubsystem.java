package frc.team4909.robot.subsystems.elevatorarm;

import frc.team4909.robot.Robot;
import frc.team4909.robot.RobotConstants;
import frc.team4909.robot.RobotMap;
import frc.team4909.robot.operator.controllers.BionicF310;
import frc.team4909.robot.subsystems.elevatorarm.commands.ElevatorArmOperatorControl;
import frc.team4909.robot.subsystems.elevatorarm.commands.SetAngle;
import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class ElevatorArmSubsystem extends Subsystem{
    TalonSRX elevatorArmSRX;

    public int holdingPosition = 0;

    public ElevatorArmSubsystem(){
        //Elevator arm
        super();
        elevatorArmSRX = new TalonSRX(RobotMap.elevatorArmSRXID);
        elevatorArmSRX.configFactoryDefault();


        // elevatorArmSRX.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
        elevatorArmSRX.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
        //update();       
        elevatorArmSRX.setNeutralMode(NeutralMode.Brake);
        elevatorArmSRX.configContinuousCurrentLimit(3);
        elevatorArmSRX.configPeakCurrentLimit(6);
        elevatorArmSRX.selectProfileSlot(0, 0);
        elevatorArmSRX.config_kP(0, 3, 0);
        elevatorArmSRX.config_kI(0, 0);
        elevatorArmSRX.config_kD(0, 0, 0);
        holdCurrentPosition();        
    }


    public InstantCommand setAngle(int height){
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
    public void reset(){
        holdingPosition = getPosition();
    }

    public void setSensorZero(){
        elevatorArmSRX.setSelectedSensorPosition(0);
        holdingPosition = 0;
    }
    
    @Override
    public void periodic() {
        SmartDashboard.putNumber("Wrist position", elevatorArmSRX.getSelectedSensorPosition());
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new ElevatorArmOperatorControl());
    }


}