package frc.team4909.robot.subsystems.drivetrain.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4909.robot.Robot;
import frc.team4909.robot.operator.controllers.BionicF310;
import frc.team4909.robot.subsystems.drivetrain.DriveTrainSubsystem;

public class Drive extends Command {
    
    private final double kP = 0.15;
    private final double kD = 0.5;
    private double lastError;

    public Drive() {
        requires(Robot.drivetrainSubsystem);
        requires(Robot.vision);
    }
    @Override
    protected void initialize() {
        lastError = Robot.vision.getXOffset();
    }

    public void execute() {
        Robot.vision.updateVisionDashboard();

        // if(Robot.driverGamepad.getRawButton(2))
        // Robot.vision.setLights(3);
        // else
        // Robot.vision.setLights(1);
        
        if(Robot.driverGamepad.getRawButton(5))
        {
            Robot.vision.setLights(3);
            Robot.drivetrainSubsystem.arcadeDrive(
                -Robot.driverGamepad.getThresholdAxis(BionicF310.LY),
                Robot.vision.getXOffset() * kP + (Robot.vision.getXOffset() - lastError) * kD
            // false
          );
          lastError = Robot.vision.getXOffset();
          
        } 
        else if(Robot.driverGamepad.getRawButton(6)){
            Robot.vision.setLights(3);
            Robot.drivetrainSubsystem.preciseModeTrue();
            Robot.drivetrainSubsystem.arcadeDrive(
                -Robot.driverGamepad.getThresholdAxis(BionicF310.LY),
                Robot.vision.getXOffset() * kP + (Robot.vision.getXOffset() - lastError) * kD
                );
            lastError = Robot.vision.getXOffset();

        }
        else {
            Robot.vision.setLights(1);

            Robot.drivetrainSubsystem.arcadeDrive(
            -Robot.driverGamepad.getThresholdAxis(BionicF310.LY),
            Robot.driverGamepad.getThresholdAxis(BionicF310.RX)
          );
        }
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}