package frc.team4909.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax;

public class BionicDrive extends Subsystem {

    public final CANSparkMax leftSide = new CANSparkMax(1, MotorType.kBrushed);
    public final CANSparkMax rightSide = new CANSparkMax(2, MotorType.kBrushed);

    public BionicDrive(CANSparkMax leftSide, CANSparkMax rightSRX) {
        super();

        this.leftSide = leftSpark;
        this.rightSide = rightSpark;

        this.defaultCommand = new DriveOI(this, leftSpark, rightSpark)
    }

    protected void initDefaultCommand() {
        setDefaultCommand(defaultCommand);
    }

}