package frc.team4909.robot.subsystems.intake;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4909.robot.Robot;
import frc.team4909.robot.RobotConstants;

public class CargoIntakeIn extends Command {
    public void CargoIntakeIn() {
        requires(Robot.intakeSubsystem);
    }

    protected void execute() {
        Robot.intakeSubsystem.setCargoIntakeSpeed(RobotConstants.cargoIntakeInSpeed);
    }

    @Override
    protected boolean isFinished() {
        System.out.println(Robot.intakeSubsystem.getCargoIntakeCurrent());
        return Robot.intakeSubsystem.getCargoIntakeCurrent() > 1.875; //RobotConstants.cargoIntakeCurrentLimit;
    }
}