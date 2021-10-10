/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import javax.lang.model.type.ErrorType;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class VisionCommand extends Command {
final double kP = 0.5;
final double kI = 0.5;
final double kD = 0.01;

final double iLimit = 1; 

private double setPoint = 0;
private double errorSum = 0;
private double lastTimeStamp = 0;
private double lastError = 0;

  public VisionCommand() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    errorSum = 0;
    lastError = 0;
    lastTimeStamp = Timer.getFPGATimestamp();
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

      //Maths
      double error = setPoint - yaw;
      double dt = Timer.getFPGATimestamp() - lastTimeStamp;

      if(Math.abs(error) < iLimit) {
        errorSum += error * dt;
      }

      double errorRate = (error - lastError) / dt;
      
      double outputSpeed = kP * error + kI * errorSum + kD * errorRate;

      //Output to motor
      Robot.driveSystem.arcadeDrive(0, outputSpeed);

      //Update Last Variables
      lastTimeStamp = Timer.getFPGATimestamp();
      lastError = error;
      } else {
        Robot.driveSystem.stop();
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
