package frc.team4909.robot.subsystems.elevatorarm;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class ElevatorArmSubsytem extends Subsystem{
    TalonSRX elevatorArmSRX;
    Encoder elevatorArmEncoder;

    public ElevatorArmSubsytem(){
        //Elevator arm
        elevatorArmSRX = new TalonSRX(0);
    }


    @Override
    protected void initDefaultCommand() {

    }


}