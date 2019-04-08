package frc.team4909.robot.subsystems.intake;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team4909.robot.Robot;
import frc.team4909.robot.RobotConstants;
import frc.team4909.robot.RobotMap;
import frc.team4909.robot.subsystems.intake.commands.CargoIntakeHold;
import frc.team4909.robot.subsystems.intake.commands.HatchPanelIntakeClose;

public class IntakeSubsystem extends Subsystem {
    DoubleSolenoid hatchPanelSolenoid;
    WPI_VictorSPX cargoIntakeMotor;
    boolean lastHasCargo;

    DigitalInput intakePhotoElectric;

    public IntakeSubsystem() {
        hatchPanelSolenoid = new DoubleSolenoid(RobotMap.intakePCMChannelL,RobotMap.intakePCMChannelR);
        cargoIntakeMotor = new WPI_VictorSPX(RobotMap.intakeMotorCAN);

        intakePhotoElectric = new DigitalInput(RobotMap.intakePhotoElectric);
    }

    public void hatchPanelIntakeOpen() {
        hatchPanelSolenoid.set(DoubleSolenoid.Value.kForward);
    }

    public void hatchPanelIntakeClose() {
        hatchPanelSolenoid.set(DoubleSolenoid.Value.kReverse);
    }

    public void holdCargoIntake(){
        // if(hasCargo()){
        //     setCargoIntakeSpeed(RobotConstants.cargoIntakeHoldSpeed);
        // } else {
        //     setCargoIntakeSpeed(0.0625);

        setCargoIntakeSpeed(RobotConstants.cargoIntakeHoldSpeed);
        // }
    }

    public void setCargoIntakeSpeed(double speed) {
        if(hasCargo() && speed > 0){
            speed = RobotConstants.cargoIntakeHoldSpeed;
        }

        cargoIntakeMotor.set(-speed);
    }

    @Override
    public void periodic() {
        SmartDashboard.putBoolean("Intake - Does Photoelectric Detect Cargo", intakePhotoElectric.get());

        SmartDashboard.putBoolean("Intake - Has Cargo?", hasCargo());
    }

    public double getCargoIntakeCurrent() {
        return Robot.powerDistributionPanel.getCurrent(RobotMap.intakeMotorPDP);
    }

    public boolean hasCargo() {
        // When photoelectric sensor returns that it detects an object
        // boolean currentHasCargo = intakePhotoElectric.get();

        // boolean hasCargo = lastHasCargo && currentHasCargo;
        // lastHasCargo = currentHasCargo;

        return !intakePhotoElectric.get();
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new CommandGroup() {
            {
                requires(Robot.intakeSubsystem);

                // Revert to Closed by Default, Will Simplify While
                // Held/Toggle Open Commands in Future
                addParallel(new HatchPanelIntakeClose());
                addParallel(new CargoIntakeHold());
            }
        });
    }
}