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
    addSequential(new TimeDrive(2.5, 0.5, 0.5));
    addSequential(new InstantCommand(() -> {
      Robot.driveSystem.stop();
      Robot.intakeSystem.bottomWheel(0.5);
    }));
    addParallel(new TimeShoot(0.9));
    addSequential(new WaitCommand(5));
    for (int i = 0; i < 3; i++) {
      addSequential(new InstantCommand(() -> {
        Robot.intakeSystem.stopAll();
      }));
      addSequential(new WaitCommand(2));
      addSequential(new InstantCommand(() -> {
        Robot.intakeSystem.bottomWheel(-0.75);
      }));
      addSequential(new WaitCommand(0.25));
      addSequential(new InstantCommand(() -> {
        Robot.intakeSystem.stopAll();
      }));
    }
    addSequential(new InstantCommand(() -> {
      Robot.shootSystem.stopShoot();
      Robot.intakeSystem.stopAll();
    }));
  }
}
