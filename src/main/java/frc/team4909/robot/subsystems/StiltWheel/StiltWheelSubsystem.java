package frc.team4909.robot.subsystems.StiltWheel;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team4909.robot.Robot;
import frc.team4909.robot.RobotMap;
import com.ctre.phoenix.motorcontrol.NeutralMode;

public class StiltWheelSubsystem extends Subsystem {

    // All motor controllers should be private.
    // Methods that allow safe motion should be provided by the subsystem
    private WPI_VictorSPX climberDriveSPX;

    public StiltWheelSubsystem() {
        // super should always be called to ensure proper subystem initialization
        super();
        climberDriveSPX = new WPI_VictorSPX(RobotMap.climberDriveSPXID);

        // Set all controllers to coast in case the stilts drop down or hit something
        // during a match
        climberDriveSPX.setNeutralMode(NeutralMode.Brake);
    }

    @Override
    public void periodic() {
    }

    protected void initDefaultCommand() {
        setDefaultCommand(new Command() {
            @Override
            protected void initialize() {
                Robot.stiltWheelSubsystem.setSpeed(0);
            }

            @Override
            protected boolean isFinished() {
                return false;
            }
        });
    }

    // Spin the wheels on the bottom of the stilts to move the robot forward
    public void setSpeed(double speed) {
        climberDriveSPX.set(speed);
    }
}
