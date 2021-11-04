package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Robot;

public class ShootAllBalls extends SequentialCommandGroup {
  public ShootAllBalls() {
    for (int i = 0; i < 3; i++) {
      addCommands(new InstantCommand(() -> {
        Robot.intakeSystem.stopAll();
      }));
      addCommands(new WaitCommand(2));
      addCommands(new InstantCommand(() -> {
        Robot.intakeSystem.bottomWheel(-0.75);
      }));
      addCommands(new WaitCommand(0.25));
      addCommands(new InstantCommand(() -> {
        Robot.intakeSystem.stopAll();
      }));
    }
  }
}
