package frc.team4909.robot.subsystems.intake;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4909.robot.Robot;

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