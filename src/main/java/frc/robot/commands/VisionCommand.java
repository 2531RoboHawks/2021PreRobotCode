/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class VisionCommand extends Command {
  public VisionCommand() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    boolean targets = Robot.camera.hasTargets();

    if(targets) {
      double yaw = Robot.camera.getLatestResult().getBestTarget().getYaw();
      double pitch = Robot.camera.getLatestResult().getBestTarget().getPitch();
      double area = Robot.camera.getLatestResult().getBestTarget().getArea();

      System.out.println(yaw + " " + Math.sqrt(Math.abs(yaw)));

      if(area > 0.4) {
        if(area > 0.75) {
          Robot.driveSystem.tankDrive(0.3, 0.3);
        } else if(area < 5) {
          Robot.driveSystem.tankDrive(-0.3, -0.3);
        }

        if(yaw < -1) {
          Robot.driveSystem.tankDrive(0.5, -0.5);
        } else if (yaw > 1){
          Robot.driveSystem.tankDrive(-0.5, 0.5);
        }

      } else {
        Robot.driveSystem.stop();
      }
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
