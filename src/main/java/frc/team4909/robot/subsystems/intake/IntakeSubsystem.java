package frc.team4909.robot.subsystems.intake;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.RobotController; 

public class IntakeSubsystem extends Subsystem{
    DoubleSolenoid doubleSolenoid;
    PWMVictorSPX leftSPX, rightSPX;

    public IntakeSubsystem(){
        doubleSolenoid = new DoubleSolenoid(1,2);
        leftSPX = new PWMVictorSPX(1);
        rightSPX = new PWMVictorSPX(2);

    }

    public void pneumaticForward(){
        doubleSolenoid.set(DoubleSolenoid.Value.kForward);
    }

    public void pneumaticReverse(){
        doubleSolenoid.set(DoubleSolenoid.Value.kReverse);
    }

    public void pneumaticOff(){
        doubleSolenoid.set(DoubleSolenoid.Value.kOff);
    }    

    public void motorIn(double speed){
        leftSPX.set(-(speed));
        rightSPX.set(speed);
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
        if (currentOut() >195) {
            spx.set(spx.get()%2);
        }   
    }

    @Override
    protected void initDefaultCommand() {

    }
}