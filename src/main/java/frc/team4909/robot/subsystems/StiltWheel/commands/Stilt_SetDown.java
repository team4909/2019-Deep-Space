package frc.team4909.robot.subsystems.StiltWheel.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4909.robot.Robot;
import frc.team4909.robot.RobotConstants;
import frc.team4909.robot.operator.controllers.BionicF310;
import frc.team4909.robot.operator.generic.BionicAxis;

public class Stilt_SetDown extends Command {

    public Stilt_SetDown()
    {
        requires(Robot.stiltWheelSubsystem);
=    }

    protected void initDefaultCommand()
    {
        setDefaultCommand(new MoveStiltWheels());
    }

    public boolean canClimb()
    {
        if (getPosition()>=climberLiftMaster.getClosedLoopError())//returns boolean indicating if position is greater than or equal to threshold
        {
            return false;       
        }
        else {
            return true;
        }
    }

    protected void execute() {
        if (canClimb==true)
        {
            this.initDefaultCommand();
        }
        else {
            while (getPosition<=climberLiftMaster.getClosedLoopError())
            {
                Robot.stiltWheelSubsystem.setSpeed(climbVelocityMultiplier*climbSpeedMultiplier);
            }
        }
    }

}