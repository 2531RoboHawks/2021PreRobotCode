package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Robot;

public class ShootAllBalls extends SequentialCommandGroup {
  public ShootAllBalls() {
    // addRequirements(Robot.intakeSystem);
    for (int i = 0; i < 3; i++) {
      addCommands(new InstantCommand(() -> {
        Robot.intakeSystem.stopAll();
        Robot.intakeSystem.bottomWheel(0.5);
      }));
      addCommands(new WaitCommand(2.2));
      addCommands(new InstantCommand(() -> {
        Robot.intakeSystem.bottomWheel(-0.75);
      }));
      addCommands(new WaitCommand(0.1));
      addCommands(new InstantCommand(() -> {
        Robot.intakeSystem.stopAll();
      }));
    }
    addCommands(new WaitCommand(1));
  }
}
