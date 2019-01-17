package frc.team4909.robot.subsystems.drivetrain.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team4909.robot.subsystems.drivetrain.BionicDrive;
import frc.team4909.robot.operator.controllers.BionicF310;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.team4909.robot.operator.generic.BionicAxis;  ;


public class DriveOI extends Command {
    private final BionicDrive bionicDrive;
    private final CANSparkMax leftSRX, rightSRX;

    private final BionicF310 speedInputGamepad, rotationInputGamepad;
    private final BionicAxis speedInputAxis, rotationInputAxis;
    public double speedMultiplier, rotationMultiplier;
    private double limitedSpeed, limitedRotation;

    public DriveOI(BionicDrive bionicDrive, CANSparkMax leftSRX, CANSparkMax rightSRX,
                   BionicF310 speedInputGamepad, BionicAxis speedInputAxis, double speedMultiplier,
                   BionicF310 rotationInputGamepad, BionicAxis rotationInputAxis, double rotationMultiplier) {
        requires(bionicDrive);
        this.bionicDrive = bionicDrive;

        this.leftSRX = leftSRX;
        this.rightSRX = rightSRX;

        this.speedInputGamepad = speedInputGamepad;
        this.speedInputAxis = speedInputAxis;
        this.speedMultiplier = speedMultiplier;

        this.rotationInputGamepad = rotationInputGamepad;
        this.rotationInputAxis = rotationInputAxis;
        this.rotationMultiplier = rotationMultiplier;
    }

    @Override
    protected void execute() {
        // Calculate Change Limited Speed Value
        double maxVelocity = 1,
                speed = speedInputGamepad.getSensitiveAxis(speedInputAxis) * speedMultiplier,
                speedDelta = speed - limitedSpeed,
                speedDeltaLimit = bionicDrive.speedDeltaLimit;

        if (Math.abs(speedDelta) > speedDeltaLimit)
            speedDelta = Math.copySign(speedDeltaLimit, speedDelta);
        limitedSpeed += speedDelta;

        // Calculate Change Limited Rotation Value
        double rotation = rotationInputGamepad.getSensitiveAxis(rotationInputAxis) * rotationMultiplier,
                rotationDelta = rotation - limitedRotation,
                rotationDeltaLimit = bionicDrive.rotationDeltaLimit;

        if (Math.abs(rotationDelta) > rotationDeltaLimit)
            rotationDelta = Math.copySign(rotationDeltaLimit, rotationDelta);
        limitedRotation += rotationDelta;


        // Calculate Left/Right Percentage Output Values
        double leftMotorOutput, rightMotorOutput;

        if (limitedSpeed > 0.0) {
            if (limitedRotation > 0.0) {
                leftMotorOutput = limitedSpeed - limitedRotation;
                rightMotorOutput = Math.max(limitedSpeed, limitedRotation);
            } else {
                leftMotorOutput = Math.max(limitedSpeed, -limitedRotation);
                rightMotorOutput = limitedSpeed + limitedRotation;
            }
        } else {
            if (limitedRotation > 0.0) {
                leftMotorOutput = -Math.max(-limitedSpeed, limitedRotation);
                rightMotorOutput = limitedSpeed + limitedRotation;
            } else {
                leftMotorOutput = limitedSpeed - limitedRotation;
                rightMotorOutput = -Math.max(-limitedSpeed, -limitedRotation);
            }
        }

        // Limit Left/Right Percentage Output to -100% to 100%
        leftMotorOutput = limit(leftMotorOutput);
        rightMotorOutput = limit(rightMotorOutput);

        // Set Output to Motors
//        if(!bionicDrive.encoderOverride) {
//            leftSRX.set(ControlMode.Velocity, maxVelocity * leftMotorOutput);
//            rightSRX.set(ControlMode.Velocity, maxVelocity * rightMotorOutput);
//        } else {
            leftSRX.set(leftMotorOutput);
            rightSRX.set(rightMotorOutput);
//        }
    }


    private double limit(double value) {
        return Math.copySign(Math.abs(value) > 1.0 ? 1.0 : value, value);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}