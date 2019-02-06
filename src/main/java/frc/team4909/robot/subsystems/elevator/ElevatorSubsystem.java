package frc.team4909.robot.subsystems.elevator;

import edu.wpi.first.wpilibj.Encoder;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team4909.robot.subsystems.elevator.SetElevatorPosition;

public class ElevatorSubsystem extends Subsystem{
    WPI_VictorSPX VictorSPX1, VictorSPX2, VictorSPX3;
    WPI_TalonSRX masterSRX;
    DigitalInput topLimitSwitch, bottomLimitSwitch;

    public int holdingPosition;

    public void ElevatorSubsytem(){

        //Lift
        masterSRX = new WPI_TalonSRX(7);
        VictorSPX1 = new WPI_VictorSPX(4);
        VictorSPX2 = new WPI_VictorSPX(5);
        VictorSPX3 = new WPI_VictorSPX(6);

        VictorSPX1.follow(masterSRX);
        VictorSPX3.follow(masterSRX);
        VictorSPX2.follow(masterSRX);
        masterSRX.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
    }

    public InstantCommand setHeight(int height){
        return new SetElevatorPosition(height, this);
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