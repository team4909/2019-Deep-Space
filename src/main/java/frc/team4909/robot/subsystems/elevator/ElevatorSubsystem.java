package frc.team4909.robot.subsystems.elevator;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PWMTalonSRX;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ElevatorSubsystem extends Subsystem{
    PWMVictorSPX leftElevatorSPX, rightElevatorSPX;
    PWMTalonSRX leftElevatorSRX, rightElevatorSRX, pivotSRX;
    Encoder pivotEncoder, liftEncoder1, liftEncoder2;
    DigitalInput topLimitSwitch, bottomLimitSwitch;

    public void ElevatorSubsytem(){
        //Intake Pivot
        pivotSRX = new PWMTalonSRX(3);
        pivotEncoder = new Encoder(1, 2);

        //Lift
        leftElevatorSPX = new PWMVictorSPX(4);
        rightElevatorSPX = new PWMVictorSPX(5);
        leftElevatorSRX = new PWMTalonSRX(6);
        rightElevatorSRX = new PWMTalonSRX(7);
        liftEncoder1 = new Encoder(3, 4);
        liftEncoder2 = new Encoder(5, 6);
        topLimitSwitch = new DigitalInput(7);
        bottomLimitSwitch = new DigitalInput(8);

    }

    @Override
    protected void initDefaultCommand() {

    }

}