package frc.team4909.robot.subsystems.intake;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4909.robot.Robot;

public class CargoIntakeIn extends Command{
    public void CargoIntakeIn(){
        requires(Robot.intakeSubsystem);
    }

    protected void execute() {
        Robot.intakeSubsystem.setCargoIntakeSpeed(RobotConstants.cargoIntakeInSpeed);
    }

    @Override
    protected boolean isFinished() {
        return Robot.intakeSubsystem.getCargoIntakeCurrent() > RobotConstants.cargoIntakeCurrentLimit;
    }
}