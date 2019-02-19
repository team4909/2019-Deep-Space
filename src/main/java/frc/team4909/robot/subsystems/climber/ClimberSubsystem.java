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
    WPI_TalonSRX climberLiftSRX;
    WPI_VictorSPX climberDriveSPX;
    int driveDirection;
    public ClimberSubsystem() {
        climberLiftSRX = new WPI_TalonSRX(RobotMap.climberSRXID); // Climber Lift
        climberDriveSPX = new WPI_VictorSPX(RobotMap.climberSPXID); // Climber Drive

        climberLiftSRX.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);

        climberLiftSRX.setNeutralMode(NeutralMode.Brake); // TODO: See if maybe this should be set to Coast instead of
                                                          // Brake, as well as for the Elevator PID
        /* TODO: PID yet to be calibrated */
        climberLiftSRX.selectProfileSlot(3, 0);
        climberLiftSRX.config_kP(3, 0.5, 0);
        climberLiftSRX.config_kI(3, 0);
        climberLiftSRX.config_kD(3, 0);
    }
    public void reset(){
        climberLiftSRX.setSelectedSensorPosition(0);
    }

    public void setStiltsDriveSpeed(double speed) {
        climberDriveSPX.set(speed);
    }

    public void setStiltsClimbSpeed(double speed) {
        climberLiftSRX.set(speed);
    }

    public void setSpeeds(double speed) {
        climberLiftSRX.set(ControlMode.Velocity, speed);
        Robot.elevatorSubsystem.setVelocity(speed);
    }

    public void setStiltsPosition(int holdingPosition){
        climberLiftSRX.set(ControlMode.Position, holdingPosition);
    }

    public int getPosition(){
        return climberLiftSRX.getSelectedSensorPosition();
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