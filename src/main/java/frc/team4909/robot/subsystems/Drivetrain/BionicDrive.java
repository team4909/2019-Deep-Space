package frc.team4909.robot.subsystems.drivetrain;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team4909.robot.subsystems.drivetrain.commands.DriveOI;
import frc.team4909.robot.operator.controllers.BionicF310;
import frc.team4909.robot.operator.generic.BionicAxis;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class BionicDrive extends Subsystem {

    public final CANSparkMax leftSpark = new CANSparkMax(1, MotorType.kBrushed);
    public final CANSparkMax rightSpark = new CANSparkMax(2, MotorType.kBrushed);

    public final DriveOI defaultCommand;
    
    /**
     * @param leftSide              Left Drivetrain SRX
     * @param rightSide             Right Drivetrain SRX
     * @param speedInputGamepad    Speed Input Gamepad/Joystick
     * @param speedInputAxis       Speed Input Axis
     * @param rotationInputGamepad Rotation Input Gamepad/Joystick
     * @param rotationInputAxis    Rotation Input Axis
     */

    public BionicDrive(CANSparkMax leftSpark, CANSparkMax rightSpark, BionicF310 speedInputGamepad, BionicAxis speedInputAxis) {
        super();
        
        this.leftSpark =  leftSide;
        this.rightSpark = rightSide;


        this.defaultCommand = new DriveOI(this, leftSide, rightSide,
        speedInputGamepad, speedInputAxis);
    }

    protected void initDefaultCommand() {
        setDefaultCommand(defaultCommand);
    }

}