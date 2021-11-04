/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class TimeShoot extends CommandBase {
  boolean finished = false;
  double power;

  public TimeShoot(double power) {
    this.power = power;
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    Robot.shootSystem.shoot(power);
  }

  @Override
  public boolean isFinished() {
    return finished;
  }

  @Override
  public void end(boolean interrupted) {
    Robot.shootSystem.stopShoot();
    finished = true;
  }
}
