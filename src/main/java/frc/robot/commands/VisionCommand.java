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
import frc.robot.subsystems.Limelight;

public class VisionCommand extends Command {
boolean finished = false;
  private final DriveSystem driveSystem;
  private final PIDController pidController;

  public VisionCommand(DriveSystem driveSystem) {
    this.pidController = new PIDController(0.5, 0.5, 0.01);
    this.driveSystem = driveSystem;
    // requires(driveSystem);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    pidController.reset();
    pidController.setSetpoint(0);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double tx = Robot.limelight.getX();
    
    
    // SmartDashboard.putNumber("Yaw", tx);
    // SmartDashboard.putNumber("Pitch", 0);
    // SmartDashboard.putNumber("Area", 0);
      
    double output = pidController.calculate(tx);
    driveSystem.arcadeDrive(0, output / 10.0);
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
