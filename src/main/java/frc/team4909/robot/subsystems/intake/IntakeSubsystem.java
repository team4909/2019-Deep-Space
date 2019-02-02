package frc.team4909.robot.subsystems.intake;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.command.Subsystem;

public class IntakeSubsystem extends Subsystem{
    // IR Sensor Threshold derived by testing the minimum voltage 
    // readouts when the ball is placed LEFT, RIGHT, and CENTER.
    // This value should then be compared to when there is no cargo
    // to ensure that the values do not overlap. The distinguishing value 
    // is then denoted as the treshold.
    double irSensorThreshold = 1.7;

    DoubleSolenoid doubleSolenoid;
    WPI_VictorSPX victorSPX;

    AnalogInput leftIRSensor;
    AnalogInput rightIRSensor;

    public IntakeSubsystem(){
        doubleSolenoid = new DoubleSolenoid(1,2);
        victorSPX = new WPI_VictorSPX(1);

        leftIRSensor = new AnalogInput(0);
        rightIRSensor = new AnalogInput(1);
    }

    public void hatchPanelIntakeOpen(){
        doubleSolenoid.set(DoubleSolenoid.Value.kForward);
    }

    public void hatchPanelIntakeClose(){
        doubleSolenoid.set(DoubleSolenoid.Value.kReverse);
    }

    public void setCargoIntakeSpeed(double speed){
        victorSPX.set((speed));
    }

    public boolean hasCargo(){
        // When either IR Sensor Voltage Reading is Higher than the predetermined threshold.
        return leftIRSensor.getVoltage() > irSensorThreshold || rightIRSensor.getVoltage() > irSensorThreshold;
    }

    @Override
    protected void initDefaultCommand() {

    }
}