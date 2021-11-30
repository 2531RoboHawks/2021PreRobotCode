/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Robot;

public class AutoScore extends SequentialCommandGroup {
  public AutoScore() {
    addRequirements(Robot.driveSystem);
    // addSequential(new TimeDrive(2.8, 0.5, 0.5));
    // addSequential(new InstantCommand(() -> {
    //   Robot.shootSystem.shoot(0.9);
    // }));
    addCommands(new InstantCommand(() -> {
      Robot.shootSystem.shoot(0.75);
    }));
    addCommands(new TimeDrive(2, 0.5, 0.5));
    addCommands(new InstantCommand(() -> {
      Robot.driveSystem.stop();
      Robot.intakeSystem.bottomWheel(0.5);
    }));
    // addParallel(new TimeShoot(0.9));
    addCommands(new WaitCommand(4));
    addCommands(new ShootAllBalls());
    addCommands(new InstantCommand(() -> {
      end(false);
    }));
  }

  @Override
  public void end(boolean interrupted) {
    Robot.shootSystem.stop();
    Robot.intakeSystem.stopAll();
  }
}
