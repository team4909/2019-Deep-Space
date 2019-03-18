package frc.team4909.robot.subsystems.wrist;

import frc.team4909.robot.RobotConstants;
import frc.team4909.robot.RobotMap;
import frc.team4909.robot.subsystems.wrist.commands.ElevatorArmOperatorControl;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class WristSubsystem extends Subsystem{
    TalonSRX wristMaster;

    public int holdingPosition = 0;

    public WristSubsystem(){
        // super should always be called to ensure proper subystem initialization
        super();
        // Wrist
        wristMaster = new TalonSRX(RobotMap.elevatorArmSRXID);
        
        // Talons have sticky nonvolatile flash memory.
        // Lets clear any sticky settings to ensure we use the settings configured below
        wristMaster.configFactoryDefault();

        // We want Absolute to use the quadrature output of the encoder 4096 per rev.
        // Absolute works in this case because the wrist rotates less than 1 revolution
        wristMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute);

        // There are no followers at this time.

        // Set all controllers to brake to help keep the elevator in place when not
        // being driven
        wristMaster.setNeutralMode(NeutralMode.Brake);

        // Pick a value so that positive PercentOutput yields a positive change in
        // sensor
        wristMaster.setSensorPhase(false);

        // Only one motor so no inversions needed

        //Limit the max percent output
        wristMaster.configPeakOutputForward(RobotConstants.wristMaxSpeedUp, RobotConstants.timeoutMs);
        wristMaster.configPeakOutputReverse(RobotConstants.wristMaxSpeedDown, RobotConstants.timeoutMs);

        final int primarySlotIdx = 1;
        final int pidIdx = 0;
        // set default slot to use for closed loop control
        wristMaster.selectProfileSlot(primarySlotIdx, pidIdx);

        // Set constants for closed loop control
        wristMaster.config_kP(primarySlotIdx, 3, RobotConstants.timeoutMs);
        wristMaster.config_kI(primarySlotIdx, 0, RobotConstants.timeoutMs);
        wristMaster.config_kD(primarySlotIdx, 0, RobotConstants.timeoutMs);
        
        // Save the current encoder value as holding position
        holdCurrentPosition();        
    }

    public void holdCurrentPosition(){
        holdingPosition = wristMaster.getSelectedSensorPosition();
    }
    public int getPosition(){
        return wristMaster.getSelectedSensorPosition();
    }

    public void elevatorArmSetSpeed(double speed){
        wristMaster.set(ControlMode.PercentOutput, speed);
    }
    public void setPosition(double position){
        wristMaster.set(ControlMode.Position, position);
    }
    public void reset() {
        holdingPosition = getPosition();
    }

    public void setSensorZero() {
        wristMaster.setSelectedSensorPosition(0);
        holdingPosition = 0;
    }
    
    @Override
    public void periodic() {
        SmartDashboard.putNumber("Intake Wrist - Current Position", wristMaster.getSelectedSensorPosition());
        SmartDashboard.putNumber("Intake Wrist - Setpoint Position", holdingPosition);
        SmartDashboard.putNumber("Intake Wrist - Setpoint Error", wristMaster.getClosedLoopError());
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new ElevatorArmOperatorControl());
    }


}