package frc.team4909.robot.subsystems.intake;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.team4909.robot.Robot;
import frc.team4909.robot.RobotConstants;

public class CargoIntakeOut extends InstantCommand{
    
    public void CargoIntakeOut(){
        requires(Robot.intakeSubsystem);
    }
    protected void initialize() {
        Robot.intakeSubsystem.setCargoIntakeSpeed(RobotConstants.cargoIntakeOutSpeed);
    }

}