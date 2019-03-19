// package frc.team4909.robot.subsystems.climber;

// import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
// import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

// import edu.wpi.first.wpilibj.command.Command;
// import edu.wpi.first.wpilibj.command.Subsystem;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import frc.team4909.robot.RobotMap;
// import frc.team4909.robot.subsystems.climber.commands.Default_StiltsHoldPos;
// import frc.team4909.robot.Robot;
// import frc.team4909.robot.RobotConstants;
// import com.ctre.phoenix.motorcontrol.ControlMode;
// import com.ctre.phoenix.motorcontrol.FeedbackDevice;
// import com.ctre.phoenix.motorcontrol.NeutralMode;

// public class ClimberSubsystem extends Subsystem {

//     // All motor controllers should be private.
//     // Methods that allow safe motion should be provided by the subsystem
//     private WPI_TalonSRX climberLiftMaster;
//     private WPI_VictorSPX climberLiftSlave;
//     private int holdingStiltsPosition = 0;

//     public ClimberSubsystem() {
//         //super should always be called to ensure proper subystem initialization
//         super();
//         climberLiftMaster = new WPI_TalonSRX(RobotMap.climberMasterSRXID);
//         climberLiftSlave = new WPI_VictorSPX(RobotMap.climberSlaveSPXID);

//         // Talons have sticky nonvolatile flash memory.
//         // Lets clear any sticky settings to ensure we use the settings configured below
//         climberLiftMaster.configFactoryDefault();

//         // We want Relative to use the quadrature output of the encoder.
//         // Absolute is only good if the output rotates less than 1 revolution
//         climberLiftMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);

//         // All slaves will output the same value as the master SRX
//         climberLiftSlave.follow(climberLiftMaster);

//         // Set all controllers to coast in case the stilts drop down or hit something
//         // during a match
//         climberLiftMaster.setNeutralMode(NeutralMode.Brake);
//         climberLiftSlave.setNeutralMode(NeutralMode.Brake);

//         // Pick a value so that positive PercentOutput yields a positive change in
//         // sensor
//         climberLiftMaster.setSensorPhase(true); // @todo validate correct

//         // One side needs to be inverted so the motors spin in the same direction
//         climberLiftMaster.setInverted(true);
//         climberLiftSlave.setInverted(false);

//         // Limit the max motor speed
//         climberLiftMaster.configPeakOutputForward(.5, RobotConstants.timeoutMs);
//         climberLiftMaster.configPeakOutputReverse(-.5, RobotConstants.timeoutMs);
       
//         final int slotIdx = 1;
//         final int pidIdx = 0;
//         //set default slot to use for closed loop control
//         climberLiftMaster.selectProfileSlot(slotIdx, pidIdx);

//         //set constants for closed loop control
//         climberLiftMaster.config_kP(slotIdx, 3, 0);
//         climberLiftMaster.config_kI(slotIdx, 0);
//         climberLiftMaster.config_kD(slotIdx, 0);
//     }

//     @Override
//     public void periodic() {
//         SmartDashboard.putNumber("Climber Stilts - Current Position", getPosition());
//         SmartDashboard.putNumber("Climber Stilts - Setpoint Position", holdingStiltsPosition);
//         SmartDashboard.putNumber("Climber Stilts - Setpoint Error", climberLiftMaster.getClosedLoopError());
//     }

//     protected void initDefaultCommand() {
//         setDefaultCommand(new Default_StiltsHoldPos());
//     }

//     // Zero the relative encoder
//     public void reset(){
//         // climberLiftMaster.setSelectedSensorPosition(0);
//         holdingStiltsPosition = getPosition();
//     }

//     // Move the climber up (+) or down (-)
//     public void setStiltsClimbSpeed(double speed) {
//         climberLiftMaster.set(speed);
//     }

//     // Use the closed loop control to move the stilts at speed
//     public void setSpeed(double speed) {
//         climberLiftMaster.set(ControlMode.PercentOutput, speed);
//     }

//     // Attempt to move both the elevator and the stilts at the same velocity.
//     // @note, need to make sure the unit conversion works as expected....


//     // public void setStiltVelocity(double moveSpeed){
//     //     climberLiftMaster.set(ControlMode.Velocity, moveSpeed);
//     // }

//     // Allow public access to the encoder position
//     public int getPosition(){
//         return climberLiftMaster.getSelectedSensorPosition();
//     }

//     // Use the closed loop control to hold the stilts at pos
//     public void setPosition(int pos) {
//         climberLiftMaster.set(ControlMode.Position, pos);
//     }
//     // public void setSensorZero(){
//     //     climberLiftMaster.setSelectedSensorPosition(0);
//     //     holdingStiltsPosition = 0;
//     // }

//     public void updateHoldingPos() {
//         holdingStiltsPosition = getPosition();
//     }

//     public void updateHoldingPos(int positionInTicks) {
//         holdingStiltsPosition = positionInTicks;
//     }

//     public void holdPosition() {
//         setPosition(holdingStiltsPosition);
//     }

   

    
// }