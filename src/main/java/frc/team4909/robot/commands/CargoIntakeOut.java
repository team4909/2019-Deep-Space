package frc.team4909.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4909.robot.Robot;
import frc.team4909.robot.RobotConstants;

public class CargoIntakeOut extends Command{
    public void CargoIntakeOut(){
        requires(Robot.intakeSubsystem);
    }

    protected void execute () {
        Robot.intakeSubsystem.setCargoIntakeSpeed(RobotConstants.cargoIntakeOutSpeed);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}