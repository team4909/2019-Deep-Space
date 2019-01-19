package frc.team4909.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4909.robot.Robot;
import frc.team4909.robot.operator.controllers.BionicF310;
import frc.team4909.robot.subsystems.drivetrain.DriveTrainSubsystem;;

public class DrivetrainCommand extends Command{

    public DrivetrainCommand() {
        requires(Robot.drivetrainsub);

        
    }
    protected void intialize(){

    }

    protected void execute(){
        Robot.drivetrainsub.arcadeDrive(Robot.driverGamepad.getX(), Robot.driverGamepad.getY());
    }

    protected boolean isFinished(){
        return false;
    }

    protected void end(){

    }

    protected void interrupted(){

    }
}