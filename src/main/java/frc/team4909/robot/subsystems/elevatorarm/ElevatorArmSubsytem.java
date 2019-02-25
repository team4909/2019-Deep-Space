package frc.team4909.robot.subsystems.elevatorarm;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.command.Subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class ElevatorArmSubsystem extends Subsystem {
    TalonSRX elevatorArmSRX;
    Encoder elevatorArmEncoder;

    public int holdingPosition;

    public void ElevatorArmSubsystem() {
        //Elevator arm
        elevatorArmSRX = new TalonSRX(0);

        elevatorArmSRX.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
    }

    public InstantCommand setHeight(int height){
        if (height==0)
        {
            elevatorArmEncoder.setIndexSource(0);
        }
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
}