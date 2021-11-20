/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class TimeDrive extends CommandBase {
  private double time;
  private double startTime = System.currentTimeMillis();
  private double power1;
  private double power2;
  private boolean end = false;

  public TimeDrive(double tTime, double p1, double p2) {
    // requires(Robot.driveSystem);
    this.time = tTime * 1000;
    this.power1 = p1;
    this.power2 = p2;
  }

  @Override
  public void initialize() {
    startTime = System.currentTimeMillis();
    end = false;
  }

  @Override
  public void execute() {
    double currentTime = System.currentTimeMillis();

    if (currentTime - startTime < time) {
      Robot.driveSystem.tankDrive(power1, power2);
    } else {
      Robot.driveSystem.stop();
      end = true;
    }

  }

  @Override
  public boolean isFinished() {
    return end;
  }

  @Override
  public void end(boolean interrupted) {
    Robot.driveSystem.stop();
  }
}
