package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Robot;

public class AimAndShootCommand extends SequentialCommandGroup {
  public AimAndShootCommand() {
    addCommands(new InstantCommand(() -> {
      Robot.driveSystem.stop();
      Robot.shootSystem.stopShoot();
      Robot.intakeSystem.stopAll();
    }));
    addCommands(new VisionCommand(Robot.driveSystem));
    addCommands(new InstantCommand(() -> {
      // TODO: configure based on distance
      double area = Robot.limelight.getArea();
      Robot.shootSystem.shoot(0.8);
    }));
    addCommands(new WaitCommand(5));
    addCommands(new ShootAllBalls());
    addCommands(new InstantCommand(() -> {
      Robot.driveSystem.stop();
      Robot.shootSystem.stopShoot();
      Robot.intakeSystem.stopAll();
    }));
  }
}
