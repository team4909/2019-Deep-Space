package frc.team4909.robot.subsystems.intake;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.team4909.robot.Robot;
import frc.team4909.robot.RobotConstants;

public class CargoIntakeIn extends InstantCommand{

    public void CargoIntakeIn(){
        requires(Robot.intakeSubsystem);
    }
    protected void initialize() {
        Robot.intakeSubsystem.setCargoIntakeSpeed(RobotConstants.cargoIntakeInSpeed);
    }

}