package frc.team4909.robot.subsystems.intake;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team4909.robot.Robot;
import frc.team4909.robot.RobotConstants;
import frc.team4909.robot.RobotMap;
import frc.team4909.robot.subsystems.intake.commands.HatchPanelIntakeClose;
import frc.team4909.robot.subsystems.intake.commands.CargoIntakeHold;

public class IntakeSubsystem extends Subsystem {
    DoubleSolenoid hatchPanelSolenoid;
    WPI_VictorSPX cargoIntakeMotor;

    AnalogInput leftIRSensor, rightIRSensor;

    public IntakeSubsystem() {
        hatchPanelSolenoid = new DoubleSolenoid(RobotMap.intakePCMChannelL,RobotMap.intakePCMChannelR);
        cargoIntakeMotor = new WPI_VictorSPX(RobotMap.intakeMotorCAN);

        leftIRSensor = new AnalogInput(RobotMap.leftIRSensor);
        rightIRSensor = new AnalogInput(RobotMap.rightIRSensor);
    }

    public void hatchPanelIntakeOpen() {
        hatchPanelSolenoid.set(DoubleSolenoid.Value.kForward);
    }

    public void hatchPanelIntakeClose() {
        hatchPanelSolenoid.set(DoubleSolenoid.Value.kReverse);
    }

    public void setCargoIntakeSpeed(double speed) {
        speed = -speed;

        //System.out.println(getCargoIntakeCurrent());
        cargoIntakeMotor.set(speed);
    }

    public double getCargoIntakeCurrent() {
        return Robot.powerDistributionPanel.getCurrent(RobotMap.intakeMotorPDP);
    }

    public boolean hasCargo() {
        // When either IR Sensor Voltage Reading is Higher than the predetermined
        // threshold.
        return leftIRSensor.getVoltage() > RobotConstants.irSensorThreshold
                || rightIRSensor.getVoltage() > RobotConstants.irSensorThreshold;
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