package frc.team4909.robot.subsystems.drivetrain;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team4909.robot.subsystems.drivetrain.commands.DriveOI;
import frc.team4909.robot.operator.controllers.BionicF310;
import frc.team4909.robot.operator.generic.BionicAxis;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class BionicDrive extends Subsystem {

    public final CANSparkMax leftSpark;
    public final CANSparkMax rightSpark;

    public double speedDeltaLimit, rotationDeltaLimit;

    public final DriveOI defaultCommand;
    
    /**
     * @param leftSpark              Left Drivetrain SRX
     * @param rightSpark             Right Drivetrain SRX
     * @param speedInputGamepad    Speed Input Gamepad/Joystick
     * @param speedInputAxis       Speed Input Axis
     * @param rotationInputGamepad Rotation Input Gamepad/Joystick
     * @param rotationInputAxis    Rotation Input Axis
     */

    public BionicDrive(CANSparkMax leftSpark, CANSparkMax rightSpark,
    BionicF310 speedInputGamepad, BionicAxis speedInputAxis, double speedMultiplier, double speedDeltaLimit,
    BionicF310 rotationInputGamepad, BionicAxis rotationInputAxis, double rotationMultiplier, double rotationDeltaLimit) {
        super();
        
        this.leftSpark =  leftSpark;
        this.rightSpark = rightSpark;

        this.speedDeltaLimit = speedDeltaLimit;
        this.rotationDeltaLimit = rotationDeltaLimit;


        this.defaultCommand = new DriveOI(this, leftSpark, rightSpark,
        speedInputGamepad, speedInputAxis, speedMultiplier,
        rotationInputGamepad, rotationInputAxis, rotationMultiplier);
    }

    @Override
    protected void initDefaultCommand() {

    }

//     protected void initDefaultCommand() {
//         setDefaultCommand(getDefaultCommand());
//     }
}