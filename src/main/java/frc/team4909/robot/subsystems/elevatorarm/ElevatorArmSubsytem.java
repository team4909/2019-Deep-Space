package frc.team4909.robot.subsystems.elevatorarm;

import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Encoder;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class ElevatorArmSubsytem extends Subsystem{
    TalonSRX elevatorArmSRX;

    public int holdingPosition;
    Encoder secondElevEncoder;
    TalonSRX srx;

    public void ElevatorArmSubsytem(){
        //Elevator arm
        elevatorArmSRX = new TalonSRX(0);
        srx=new TalonSRX(1);
        elevatorArmSRX.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
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

    public double encoderticks()
    {
        return secondElevEncoder.getRate();
    }

    public void setSmallMotorSpeed()
    {
        secondElevEncoder.reset();
        double val=secondElevEncoder.getDistance()/encoderticks();
        srx.set(ControlMode.PercentOutput,-val);
    }

    @Override
    protected void initDefaultCommand() {

    }


}