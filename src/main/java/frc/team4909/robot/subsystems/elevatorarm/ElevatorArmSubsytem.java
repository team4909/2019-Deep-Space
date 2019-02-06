package frc.team4909.robot.subsystems.elevatorarm;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;


import com.ctre.phoenix.motorcontrol.can.TalonSRX;

    TalonSRX bionicSRX;

    public ElevatorArmSubsytem(){
        bionicSRX = new TalonSRX(0);
    }


    @Override
    protected void initDefaultCommand() {

    }


}