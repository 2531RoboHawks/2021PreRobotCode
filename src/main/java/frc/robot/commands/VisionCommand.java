/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.subsystems.DriveSystem;

public class VisionCommand extends Command {
boolean finished = false;
  private final DriveSystem driveSystem;
  private final PIDController pidController;

  public VisionCommand(DriveSystem driveSystem) {
    this.pidController = new PIDController(0.5, 0.5, 0.01);
    this.driveSystem = driveSystem;
    requires(driveSystem);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    pidController.reset();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    boolean targets = Robot.camera.getLatestResult().hasTargets();

    if(targets) {
      //Get positions of the camera
      double yaw = Robot.camera.getLatestResult().getBestTarget().getYaw();
      double pitch = Robot.camera.getLatestResult().getBestTarget().getPitch();
      double area = Robot.camera.getLatestResult().getBestTarget().getArea();
      SmartDashboard.putNumber("Yaw", yaw);
      SmartDashboard.putNumber("Pitch", pitch);
      SmartDashboard.putNumber("Area", area);

      //PID
      pidController.setSetpoint(0);
      double output = pidController.calculate(yaw);
      driveSystem.arcadeDrive(0, output);

    } else {
      driveSystem.stop();
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return pidController.atSetpoint();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    driveSystem.stop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    driveSystem.stop();
  }
}
