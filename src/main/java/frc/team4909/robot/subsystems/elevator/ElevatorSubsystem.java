package frc.team4909.robot.subsystems.elevator;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.ctre.phoenix.motorcontrol.ControlMode;

import frc.team4909.robot.Robot;
import frc.team4909.robot.RobotConstants;
import frc.team4909.robot.RobotMap;
import frc.team4909.robot.subsystems.elevator.commands.Default_ElevHoldPos;

public class ElevatorSubsystem extends Subsystem {
    
    // All motor controllers should be private.
    // Public Methods that allow safe motion should be provided by the subsystem
    private WPI_VictorSPX leftSlave, rightSlave1, rightSlave2;
    private WPI_TalonSRX leftMaster;
    private int holdingPosition = 0;

    public ElevatorSubsystem() {
        // super should always be called to ensure proper subystem initialization
        super();
        // Lift
        leftMaster = new WPI_TalonSRX(RobotMap.elevatorSRXID); // master SRX
        leftSlave = new WPI_VictorSPX(RobotMap.elevatorSPX1ID); // slave SPX 1
        rightSlave1 = new WPI_VictorSPX(RobotMap.elevatorSPX2ID); // slave SPX 2
        rightSlave2 = new WPI_VictorSPX(RobotMap.elevatorSPX3ID); // slave SPX 3

        // Talons have sticky nonvolatile flash memory.
        // Lets clear any sticky settings to ensure we use the settings configured below
        leftMaster.configFactoryDefault();

        leftMaster.configClearPositionOnLimitR(true, RobotConstants.timeoutMs);

        // We want Relative to use the quadrature output of the encoder.
        // Absolute is only good if the output rotates less than 1 revolution
        leftMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);

        // All slaves will output the same value as the master SRX
        leftSlave.follow(leftMaster);
        rightSlave1.follow(leftMaster);
        rightSlave2.follow(leftMaster);

        // Set all controllers to brake to help keep the elevator in place when not
        // being driven
        leftMaster.setNeutralMode(NeutralMode.Brake);
        leftSlave.setNeutralMode(NeutralMode.Brake);
        rightSlave1.setNeutralMode(NeutralMode.Brake);
        rightSlave2.setNeutralMode(NeutralMode.Brake);

        // Pick a value so that positive PercentOutput yields a positive change in
        // sensor
        leftMaster.setSensorPhase(false);

        // One side needs to be inverted so the motors spin in the same direction
        // Also some of the 775 motors are wired backwards and require inversion
        leftMaster.setInverted(false);
        leftSlave.setInverted(false);
        rightSlave1.setInverted(true);
        rightSlave2.setInverted(false);

        //Limit the max percent output
        leftMaster.configPeakOutputForward(RobotConstants.elevatorMaxSpeedUp, RobotConstants.timeoutMs);
        leftMaster.configPeakOutputReverse(RobotConstants.elevatorMaxSpeedDown, RobotConstants.timeoutMs);

        final int primarySlotIdx = 1;
        final int pidIdx = 0;
        // set default slot to use for closed loop control
        leftMaster.selectProfileSlot(primarySlotIdx, pidIdx);

        // Set constants for closed loop control
        leftMaster.config_kF(primarySlotIdx, 0, RobotConstants.timeoutMs);
        leftMaster.config_kP(primarySlotIdx, 0.5, RobotConstants.timeoutMs);
        leftMaster.config_kI(primarySlotIdx, 0, RobotConstants.timeoutMs);
        leftMaster.config_kD(primarySlotIdx, 0, RobotConstants.timeoutMs);

        // leftMaster.configClosedloopRamp(. );
        // These values are use for motion magic

        // When the code starts (IE robot powered on) call that zero.
        // @note operators will need to setup the carriage in the same place each match.
        // reset();

    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Elevator test", Math.random());
        SmartDashboard.putNumber("Elevator - Current Position", getPosition());
        SmartDashboard.putNumber("Elevator - Setpoint Position", holdingPosition);
        SmartDashboard.putNumber("Elevator - Setpoint Error", getError());
    }


    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new Default_ElevHoldPos());
    }

    /* Methods */

    // Zero the relative encoder
    public void reset() {
        leftMaster.setSelectedSensorPosition(0);
        
    }

    public void setSpeed(double speed) { // set elevator speed value
        leftMaster.set(ControlMode.PercentOutput, speed);
    }

    public void setPosition(int position) {
        leftMaster.set(ControlMode.Position, position);
    }

    public int getPosition() {
        return leftMaster.getSelectedSensorPosition();
    }

    // public int getVelocity() {
    //     return leftMaster.getSelectedSensorVelocity();
    // }

    // Get the PID error from the SRX
    public int getError() {
        return leftMaster.getClosedLoopError();
    }

    // public void setVelocity(double speed) {
    //     leftMaster.set(ControlMode.Velocity, speed);
    // }

    public void setInitialPIDValues() {
        leftMaster.selectProfileSlot(1, 0);
    }

    // public void setNewPIDValues() {
    //     leftMaster.selectProfileSlot(2, 0);
    // }

    public void configReverseLimitSwitch(boolean override) {
        if(override)
            leftMaster.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.Disabled);
        else
            leftMaster.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);
    }

    // public void setSensorZero(){
    //     leftMaster.setSelectedSensorPosition(0);
    //     holdingPosition = 0;
    // }

    public void updateHoldingPos() {
        holdingPosition = getPosition();
    }

    public void updateHoldingPos(int positionInTicks) {
        holdingPosition = positionInTicks;
    }

    public void holdPosition() {
        setPosition(holdingPosition);
    }
}