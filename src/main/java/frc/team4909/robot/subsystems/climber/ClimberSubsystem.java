package frc.team4909.robot.subsystems.climber;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team4909.robot.RobotMap;
import frc.team4909.robot.RobotConstants;
import frc.team4909.robot.subsystems.climber.commands.ClimbOI;
import frc.team4909.robot.subsystems.climber.commands.SetStiltPosition;
import frc.team4909.robot.subsystems.elevator.ElevatorSubsystem;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import frc.team4909.robot.Robot;
import frc.team4909.robot.operator.controllers.BionicF310;

public class ClimberSubsystem extends Subsystem {

    // All motor controllers should be private.
    // Methods that allow safe motion should be provided by the subsystem
    private WPI_TalonSRX climberLiftMaster;
    private WPI_VictorSPX climberDriveSPX, climberLiftSlave;

    public ClimberSubsystem() {
        //super should always be called to ensure proper subystem initialization
        super();
        climberLiftMaster = new WPI_TalonSRX(RobotMap.climberMasterSRXID);
        climberLiftSlave = new WPI_VictorSPX(RobotMap.climberSlaveSPXID);
        climberDriveSPX = new WPI_VictorSPX(RobotMap.climberDriveSPXID);

        // Talons have sticky nonvolatile flash memory.
        // Lets clear any sticky settings to ensure we use the settings configured below
        climberLiftMaster.configFactoryDefault();

        // We want Relative to use the quadrature output of the encoder.
        // Absolute is only good if the output rotates less than 1 revolution
        climberLiftMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);

        // All slaves will output the same value as the master SRX
        climberLiftSlave.follow(climberLiftMaster);

        // Set all controllers to coast in case the stilts drop down or hit something
        // during a match
        climberLiftMaster.setNeutralMode(NeutralMode.Coast);
        climberLiftSlave.setNeutralMode(NeutralMode.Coast);

        // Pick a value so that positive PercentOutput yields a positive change in
        // sensor
        climberLiftMaster.setSensorPhase(false); // @todo validate correct

        // One side needs to be inverted so the motors spin in the same direction
        climberLiftMaster.setInverted(true);
        climberLiftSlave.setInverted(false);
       
        final int slotIdx = 1;
        final int pidIdx = 0;
        //set default slot to use for closed loop control
        climberLiftMaster.selectProfileSlot(slotIdx, pidIdx);

        //set constants for closed loop control
        climberLiftMaster.config_kP(slotIdx, 0.5, 0);
        climberLiftMaster.config_kI(slotIdx, 0);
        climberLiftMaster.config_kD(slotIdx, 0);
    }

    // Zero the relative encoder
    public void reset(){
        climberLiftMaster.setSelectedSensorPosition(0);
    }

    // Spin the wheels on the bottom of the stilts to move the robot forward
    public void setStiltsDriveSpeed(double speed) {
        climberDriveSPX.set(speed);
    }

    // Move the climber up (+) or down (-)
    public void setStiltsClimbSpeed(double speed) {
        climberLiftMaster.set(speed);
    }

    // Attempt to move both the elevator and the stilts at the same velocity.
    // @note, need to make sure the unit conversion works as expected....
    public void setSpeeds(double speed) {
        climberLiftMaster.set(ControlMode.Velocity, speed);
        Robot.elevatorSubsystem.setVelocity(speed);
    }

    // Use the closed loop control to hold the stilts at holding position
    public void setStiltsPosition(int holdingPosition){
        climberLiftMaster.set(ControlMode.Position, holdingPosition);
    }

    // Allow public access to the encoder position
    public int getPosition(){
        return climberLiftMaster.getSelectedSensorPosition();
    }

    protected void initDefaultCommand(){
        // setDefaultCommand(new CommandGroup() {
        //     {
        //         requires(Robot.climberSubsystem);

        //         addParallel(new ClimbOI());
        //         addParallel(new SetStiltPosition());
        //     }
        // });
        setDefaultCommand(new SetStiltPosition());
    }
}