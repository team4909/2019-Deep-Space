package frc.team4909.robot.subsystems.intake;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PowerDistributionPanel;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.RobotController; 

public class IntakeSubsystem extends Subsystem{
    DoubleSolenoid doubleSolenoid;
    WPI_VictorSPX victorSPX;
    WPI_VictorSPX leftSPX;
    WPI_VictorSPX rightSPX;

    public IntakeSubsystem(){
        doubleSolenoid = new DoubleSolenoid(1,2);
        WPI_VictorSPX victorSPX = new WPI_VictorSPX(1);

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

    public void motorOut(double speed){
        leftSPX.set(speed);
        rightSPX.set(-(speed));
    }    

    public double currentOut(PowerDistributionPanel pdp)
    {
        return pdp.getCurrent(0);
    }

    public void isOverLimit(PWMVictorSPX spx) 
    {
        PowerDistributionPanel pdp = new PowerDistributionPanel();
        if (currentOut(pdp) >195) {
            spx.set(0.5);
        }   
    }

    @Override
    protected void initDefaultCommand() {

    }
}