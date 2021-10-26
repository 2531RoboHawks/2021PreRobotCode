package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.Robot;

public class AimAndShootCommand extends CommandGroup {
  public AimAndShootCommand() {
    addSequential(new InstantCommand(() -> {
      Robot.driveSystem.stop();
      Robot.shootSystem.stopShoot();
      Robot.intakeSystem.stopAll();
    }));
    addSequential(new VisionCommand(Robot.driveSystem));
    addSequential(new InstantCommand(() -> {
      // TODO: configure based on distance
      double area = Robot.limelight.getArea();
      Robot.shootSystem.shoot(0.8);
    }));
    addSequential(new WaitCommand(5));
    addSequential(new ShootAllBalls());
    addSequential(new InstantCommand(() -> {
      Robot.driveSystem.stop();
      Robot.shootSystem.stopShoot();
      Robot.intakeSystem.stopAll();
    }));
  }
}
