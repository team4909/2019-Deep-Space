package frc.team4909.robot.subsystems.intake;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team4909.robot.Robot;
import frc.team4909.robot.RobotConstants;
import frc.team4909.robot.RobotMap;
import frc.team4909.robot.subsystems.intake.HatchPanelIntakeClose;

public class IntakeSubsystem extends Subsystem {
    Solenoid hatchPanelSolenoid;
    WPI_VictorSPX cargoIntakeMotor;

    AnalogInput leftIRSensor, rightIRSensor;

    public IntakeSubsystem() {
        hatchPanelSolenoid = new Solenoid(RobotMap.intakePCMChannel);
        cargoIntakeMotor = new WPI_VictorSPX(RobotMap.intakeMotorCAN);

        leftIRSensor = new AnalogInput(RobotMap.leftIRSensor);
        rightIRSensor = new AnalogInput(RobotMap.rightIRSensor);
    }

    public void hatchPanelIntakeOpen() {
        hatchPanelSolenoid.set(true);
    }

    public void hatchPanelIntakeClose() {
        hatchPanelSolenoid.set(false);
    }

    public void setCargoIntakeSpeed(double speed) {
        speed = -speed;

        System.out.println(getCargoIntakeCurrent());
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