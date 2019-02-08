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
import frc.team4909.robot.subsystems.elevator.SetElevatorPosition;
import frc.team4909.robot.Robot;
import frc.team4909.robot.RobotConstants;
import frc.team4909.robot.operator.controllers.BionicF310;

public class ElevatorSubsystem extends Subsystem{
    WPI_VictorSPX VictorSPX1, VictorSPX2, VictorSPX3;
    WPI_TalonSRX masterSRX;

    public int holdingPosition;
    public boolean encoderOverride;

    public void ElevatorSubsytem(){

        //Lift
        masterSRX = new WPI_TalonSRX(RobotConstants.elevatorSRXID);
        VictorSPX1 = new WPI_VictorSPX(RobotConstants.elevatorSPX1ID);
        VictorSPX2 = new WPI_VictorSPX(RobotConstants.elevatorSPX2ID);
        VictorSPX3 = new WPI_VictorSPX(RobotConstants.elevatorSPX3ID);

        VictorSPX1.follow(masterSRX);
        VictorSPX3.follow(masterSRX);
        VictorSPX2.follow(masterSRX);
        masterSRX.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);

    }

    @Override
    public void periodic() {
        double moveSpeed = Robot.manipulatorGamepad.getThresholdAxis(BionicF310.RY) * RobotConstants.elevatorSpeedMultiplier;

        if(moveSpeed == 0 && !encoderOverride) {
            masterSRX.set(ControlMode.Position, holdingPosition);
        } else if(moveSpeed > 0){
            masterSRX.set(ControlMode.PercentOutput, moveSpeed);
            holdCurrentPosition();
        } else if(moveSpeed < 0){
            masterSRX.set(ControlMode.PercentOutput, moveSpeed);
            holdCurrentPosition();
        }
    }

    public InstantCommand holdPosition(int height){
        return new SetElevatorPosition(height);
    }

    public void holdCurrentPosition(){
            holdingPosition = masterSRX.getSelectedSensorPosition();
    }
    
    public void elevatorSetSpeed(double speed){
        masterSRX.set(ControlMode.PercentOutput, speed);
    }

    @Override
    protected void initDefaultCommand() {

    }
}