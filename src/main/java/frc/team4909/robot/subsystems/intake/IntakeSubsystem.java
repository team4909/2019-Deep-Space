package frc.team4909.robot.subsystems.intake;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.command.Subsystem;

public class IntakeSubsystem extends Subsystem{
    DoubleSolenoid doubleSolenoid;
    WPI_VictorSPX victorSPX;

    public IntakeSubsystem(){
        doubleSolenoid = new DoubleSolenoid(1,2);
        victorSPX = new WPI_VictorSPX(1);

    }

    public void hatchPanelIntakeOpen(){
        doubleSolenoid.set(DoubleSolenoid.Value.kForward);
    }

    public void hatchPanelIntakeClose(){
        doubleSolenoid.set(DoubleSolenoid.Value.kReverse);
    }

    public void setSpeed(double speed){
        victorSPX.set((speed));
    }

    @Override
    protected void initDefaultCommand() {

    }
}