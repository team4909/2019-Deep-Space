package frc.team4909.robot.vision.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4909.robot.Robot;
import frc.team4909.robot.operator.controllers.BionicF310;

public class LimeLightFollow extends Command{

    private final double kP = 0.15;
    private final double kD = 0.5;
    private double lastError;

    public LimeLightFollow(){
        requires(Robot.drivetrainSubsystem);
        requires(Robot.vision);
        
    }
    @Override
    protected void initialize() {
        lastError = Robot.vision.getXOffset();
    }
    @Override
    protected void execute() {
        Robot.vision.updateVisionDashboard();

        Robot.vision.setLights(3);
        Robot.drivetrainSubsystem.arcadeDrive(
            -Robot.driverGamepad.getThresholdAxis(BionicF310.LY),
            Robot.vision.getXOffset() * kP + (Robot.vision.getXOffset() - lastError) * kD
      );

      lastError = Robot.vision.getXOffset();    
    }
    @Override
    protected void end() {
        Robot.vision.setLights(1);
        Robot.drivetrainSubsystem.setSpeed(0, 0);
        }

    @Override
    protected boolean isFinished() {
        // return Robot.lidar.getDistance() < val;
        return false;
    }

}