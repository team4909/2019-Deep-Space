package frc.team4909.robot.subsystems.StiltWheel.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4909.robot.Robot;
import frc.team4909.robot.RobotConstants;
import frc.team4909.robot.operator.controllers.BionicF310;
import frc.team4909.robot.operator.generic.BionicAxis;

public class Stilt_SetDown extends Command {
    private double height;

    public Stilt_SetDown (double height)
    {
        requires(Robot.stiltWheelSubsystem);
    }

    public boolean isStiltsDown()
    {
        if (height)
    }
}