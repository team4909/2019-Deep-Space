package frc.team4909.robot.testing;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4909.robot.Robot;
import frc.team4909.robot.operator.controllers.BionicF310;

public class Drive extends Command {
    public Drive() {
        requires(Robot.myDrive);
    }
    @Override
    protected void initialize() {
        Robot.vision.setLights(1.0);
        
    }

    public void execute() {
        // Calls tank function using left Y and right Y
        // Negated since Y Axis is scaled -1 to +1 top to bottom (counterintuively)
        Robot.myDrive.arcadeDrive(Robot.driverGamepad.getThresholdAxis(BionicF310.LY) * 0.5,
                Robot.driverGamepad.getThresholdAxis(BionicF310.RX) * 0.5);

    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}