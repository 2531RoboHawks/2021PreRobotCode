/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.Robot;

public class AutoScore extends CommandGroup {
  public AutoScore() {
    // addSequential(new TimeDrive(2.8, 0.5, 0.5));
    // addSequential(new InstantCommand(() -> {
    //   Robot.shootSystem.shoot(0.9);
    // }));
    addSequential(new InstantCommand(() -> {
      Robot.driveSystem.tankDrive(2.8, 0.5);
    }));
    addSequential(new WaitCommand(5));
    addSequential(new InstantCommand(() -> {
      Robot.driveSystem.stop();
    }));
    addParallel(new TimeShoot(0.9));
    addSequential(new WaitCommand(7));
    for (int i = 0; i < 3; i++) {
      addSequential(new WaitCommand(2));
      addSequential(new InstantCommand(() -> {
        Robot.intakeSystem.intake(-0.5, 0);
      }));
      addSequential(new WaitCommand(0.25));
      addSequential(new InstantCommand(() -> {
        Robot.intakeSystem.stopIntake();
      }));
    }
    addSequential(new InstantCommand(() -> {
      Robot.shootSystem.stopShoot();
      Robot.intakeSystem.stopIntake();
    }));
  }
}
