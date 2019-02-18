package frc.team4909.robot.subsystems.elevator;

import edu.wpi.first.wpilibj.Encoder;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
// import com.sun.org.apache.xerces.internal.xni.QName;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.command.Subsystem;

import java.lang.module.ModuleDescriptor.Requires;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team4909.robot.subsystems.elevator.commands.ElevatorOperatorControl;
import frc.team4909.robot.subsystems.elevator.commands.SetElevatorPosition;
import frc.team4909.robot.Robot;
import frc.team4909.robot.RobotConstants;
import frc.team4909.robot.RobotMap;
import frc.team4909.robot.operator.controllers.BionicF310;

public class ElevatorSubsystem extends Subsystem {
    WPI_VictorSPX leftSPX, rightSPX1, rightSPX2;
    WPI_TalonSRX leftSRX;

    public int holdingPosition;

    public ElevatorSubsystem() {

        // Lift
        leftSRX = new WPI_TalonSRX(RobotMap.elevatorSRXID); // master SRX
        leftSPX = new WPI_VictorSPX(RobotMap.elevatorSPX1ID); // slave SPX 1
        rightSPX1 = new WPI_VictorSPX(RobotMap.elevatorSPX2ID); // slave SPX 2
        rightSPX2 = new WPI_VictorSPX(RobotMap.elevatorSPX3ID); // slave SPX 3

        leftSRX.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute);

        leftSPX.follow(leftSRX); // All slaves will output the same value as the master SRX
        rightSPX1.follow(leftSRX);
        rightSPX2.follow(leftSRX);

        leftSRX.setNeutralMode(NeutralMode.Brake); // TODO: Should these possibly be Coast instead?
        leftSPX.setNeutralMode(NeutralMode.Brake);
        rightSPX1.setNeutralMode(NeutralMode.Brake);
        rightSPX2.setNeutralMode(NeutralMode.Brake);

        rightSPX1.setInverted(true);
        rightSPX2.setInverted(true);
        leftSRX.setSensorPhase(false);
        // update();
        leftSRX.selectProfileSlot(1, 0);
        leftSRX.config_kP(1, 0.1, 0);
        // leftSRX.config_kP(1, 0);
        leftSRX.config_kI(1, 0);
        leftSRX.config_kD(1, 0);
    }

    public void update() {
        leftSRX.setSelectedSensorPosition(0);
    }

    public void holdCurrentPosition() { // hold elevator in position
        holdingPosition = leftSRX.getSelectedSensorPosition();
    }

    public void setSpeed(double speed) { // set elevator speed value
        leftSRX.set(ControlMode.PercentOutput, speed);
    }

    public void setPosition(int position) {
        // leftSRX.setSelectedSensorPosition(position, 0, 0); Need to test
        leftSRX.set(ControlMode.Position, position);
    }

    public void setMagicPosition(int position) {
        leftSRX.set(ControlMode.MotionMagic, position);
    }

    public int getPosition() {
        return leftSRX.getSelectedSensorPosition();
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new ElevatorOperatorControl());
    }
}