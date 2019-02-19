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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.lang.module.ModuleDescriptor.Requires;

import com.ctre.phoenix.ErrorCode;
import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team4909.robot.subsystems.elevator.commands.ElevatorOperatorControl;
import frc.team4909.robot.subsystems.elevator.commands.SetElevatorPosition;
import frc.team4909.robot.Robot;
import frc.team4909.robot.RobotConstants;
import frc.team4909.robot.RobotMap;
import frc.team4909.robot.operator.controllers.BionicF310;

public class ElevatorSubsystem extends Subsystem {
    WPI_VictorSPX leftSlave, rightSlave1, rightSlave2;
    WPI_TalonSRX leftMaster;

    public int holdingPosition;

    public ElevatorSubsystem() {
        // Lift
        leftMaster = new WPI_TalonSRX(RobotMap.elevatorSRXID); // master SRX
        leftSlave = new WPI_VictorSPX(RobotMap.elevatorSPX1ID); // slave SPX 1
        rightSlave1 = new WPI_VictorSPX(RobotMap.elevatorSPX2ID); // slave SPX 2
        rightSlave2 = new WPI_VictorSPX(RobotMap.elevatorSPX3ID); // slave SPX 3


        leftMaster.configFactoryDefault();
        leftMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);

        leftSlave.follow(leftMaster); // All slaves will output the same value as the master SRX
        rightSlave1.follow(leftMaster);
        rightSlave2.follow(leftMaster);

        leftMaster.setNeutralMode(NeutralMode.Brake);
        leftSlave.setNeutralMode(NeutralMode.Brake);
        rightSlave1.setNeutralMode(NeutralMode.Brake);
        rightSlave2.setNeutralMode(NeutralMode.Brake);

        leftMaster.setSensorPhase(true);

        leftMaster.setInverted(false);
        leftSlave.setInverted(false);
        rightSlave1.setInverted(true);
        rightSlave2.setInverted(true);

        leftMaster.configNominalOutputForward(0, RobotConstants.timeoutMs);
        leftMaster.configNominalOutputReverse(0, RobotConstants.timeoutMs);
        leftMaster.configPeakOutputForward(1, RobotConstants.timeoutMs);
        leftMaster.configPeakOutputReverse(-1, RobotConstants.timeoutMs);

        leftMaster.selectProfileSlot(1, 0);
        leftMaster.config_kF(1, 0, RobotConstants.timeoutMs);
        leftMaster.config_kP(1, 0.1, RobotConstants.timeoutMs);
        leftMaster.config_kI(1, 0, RobotConstants.timeoutMs);
        leftMaster.config_kD(1, 0, RobotConstants.timeoutMs);

        leftMaster.config_kF(2, 0, RobotConstants.timeoutMs);
        leftMaster.config_kP(2, 0.5, RobotConstants.timeoutMs);
        leftMaster.config_kI(2, 0, RobotConstants.timeoutMs);
        leftMaster.config_kD(2, 0, RobotConstants.timeoutMs);

        // leftSRX.configMotionCruiseVelocity(14047, RobotConstants.timeoutMs);
        // leftSRX.configMotionAcceleration(14047, RobotConstants.timeoutMs);

    }

    public void reset() {
        leftMaster.setSelectedSensorPosition(0);
    }

    public void holdCurrentPosition() { // hold elevator in position
        holdingPosition = leftMaster.getSelectedSensorPosition();
    }

    public void setSpeed(double speed) { // set elevator speed value
        leftMaster.set(ControlMode.PercentOutput, speed);
    }

    public void setPosition(int position) {
        // leftSRX.setSelectedSensorPosition(position, 0, 0); Need to test
        leftMaster.set(ControlMode.Position, position);
    }

    public void setMagicPosition(int position) {
        leftMaster.set(ControlMode.MotionMagic, position);
    }

    public int getPosition() {
        return leftMaster.getSelectedSensorPosition();
    }

    public int getVelocity() {
        return leftMaster.getSelectedSensorVelocity();
    }

    public int getError() {
        return leftMaster.getClosedLoopError();
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Elevator position", getPosition());
        SmartDashboard.putNumber("Elevator error", getError());
    }

    public void setVelocity(double speed) {
        leftMaster.set(ControlMode.Velocity, speed);
    }

    public void setInitialPIDValues() {
        leftMaster.selectProfileSlot(1, 0);
    }

    public void setNewPIDValues() { // TODO: Tune these PID values
        leftMaster.selectProfileSlot(2, 0);
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new ElevatorOperatorControl());
    }

}