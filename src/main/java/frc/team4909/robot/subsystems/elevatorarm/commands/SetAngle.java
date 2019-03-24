// package frc.team4909.robot.subsystems.elevatorarm.commands;

// import edu.wpi.first.wpilibj.command.InstantCommand;
// import frc.team4909.robot.subsystems.elevatorarm.ElevatorArmSubsystem;

// public class SetAngle extends InstantCommand {
//     private final int setpoint;
//     private final ElevatorArmSubsystem elevatorArm;

//     public SetAngle(int setpoint, ElevatorArmSubsystem elevatorArm) {
//         this.setpoint = setpoint;
//         this.elevatorArm = elevatorArm;
//     }

//     @Override
//     public void initialize() {
//         elevatorArm.holdingPosition = setpoint;
//     }

// }