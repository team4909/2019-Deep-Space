package frc.team4909.robot.subsystems.drivetrain.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4909.robot.Robot;
import frc.team4909.robot.operator.controllers.BionicF310;

public class Default_Drive extends Command {
    public Default_Drive() {
        requires(Robot.drivetrainSubsystem);
    }

    public void execute() {
        // Calls tank function using left Y and right Y
        // Negated since Y Axis is scaled -1 to +1 top to bottom (counterintuively)
        Robot.drivetrainSubsystem.arcadeDrive(-Robot.driverGamepad.getThresholdAxis(BionicF310.LY),
                Robot.driverGamepad.getThresholdAxis(BionicF310.RX));

    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}