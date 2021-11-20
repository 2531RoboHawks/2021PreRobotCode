package frc.robot.commands;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Robot;

public class AimAndShootCommand extends SequentialCommandGroup {
  public AimAndShootCommand() {
    addRequirements(Robot.driveSystem);
    addCommands(new InstantCommand(() -> {
      Robot.driveSystem.stop();
      Robot.shootSystem.stop();
      Robot.intakeSystem.stopAll();
      SmartDashboard.putString(IntakeAndShootCommand.INTAKE_STATUS, "AUTO - AIMING");
      SmartDashboard.putString(IntakeAndShootCommand.SHOOT_STATUS, "AUTO - AIMING");
    }));
    addCommands(new VisionCommand(Robot.driveSystem));
    addCommands(new InstantCommand(() -> {
      SmartDashboard.putString(IntakeAndShootCommand.INTAKE_STATUS, "AUTO - REVVING");
      SmartDashboard.putString(IntakeAndShootCommand.SHOOT_STATUS, "AUTO - REVVING");
      // TODO: configure based on distance
      double area = Robot.limelight.getArea();
      Robot.shootSystem.shoot(Preferences.getInstance().getDouble("Rev", 0.0));
    }));
    addCommands(new WaitCommand(5));
    addCommands(new InstantCommand(() -> {
      SmartDashboard.putString(IntakeAndShootCommand.INTAKE_STATUS, "AUTO - SHOOTING");
      SmartDashboard.putString(IntakeAndShootCommand.SHOOT_STATUS, "AUTO - SHOOTING");
    }));
    addCommands(new ShootAllBalls());
    addCommands(new InstantCommand(() -> {
      end(false);
    }));
  }

  @Override
  public void end(boolean interrupted) {
    Robot.driveSystem.stop();
    Robot.shootSystem.stop();
    Robot.intakeSystem.stopAll();
    IntakeAndShootCommand.autoButton.reset();
  }
}
