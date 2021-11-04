/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class TurnToAngle extends CommandBase {
  double deg;
  private boolean end = false;

  public TurnToAngle(double deg) {
    this.deg = deg;
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    double angle = RobotMap.gyro.getAngle();
    if (angle - deg > 0.5 || angle - deg < -0.5) {
      if (angle >= deg) {
        if (Math.abs(angle - deg) / 360 < 0.1) {
          Robot.driveSystem.tankDrive(-(Math.abs(angle - deg) / 360) - 0.3, 0);
        } else {
          Robot.driveSystem.tankDrive(-0.75, 0);
        }
      }

      if (RobotMap.gyro.getAngle() <= deg) {
        if (Math.abs(angle - deg) / 360 < 0.1) {
          Robot.driveSystem.tankDrive((Math.abs(angle - deg) / 360) + 0.3, 0);
        } else {
          Robot.driveSystem.tankDrive(0.75, 0);
        }
      }
    } else {
      end = true;
    }
  }

  @Override
  public void end(boolean interrupted) {
    end = true;
  }

  @Override
  public boolean isFinished() {
    return end;
  }
}
