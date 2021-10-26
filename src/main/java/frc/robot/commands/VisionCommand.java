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
  private final DriveSystem driveSystem;
  private final PIDController rotatePidController;
  private final PIDController drivePidController;

  public VisionCommand(DriveSystem driveSystem) {
    this.rotatePidController = new PIDController(0.5, 1, 0.01);
    this.drivePidController = new PIDController(0.5, 1, 0.01);
    this.driveSystem = driveSystem;
    // requires(driveSystem);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    drivePidController.reset();
    rotatePidController.reset();
    drivePidController.setSetpoint(1);
    rotatePidController.setSetpoint(0);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double area = Robot.limelight.getArea();
    double tx = Robot.limelight.getX();

    SmartDashboard.putNumber("area", area);
    SmartDashboard.putNumber("tx", tx);
    // SmartDashboard.putNumber("Pitch", 0);
    // SmartDashboard.putNumber("Area", 0);

    double areaOutput = drivePidController.calculate(area);
    double xOutput = rotatePidController.calculate(tx);

    SmartDashboard.putNumber("output", areaOutput);

    if (Robot.limelight.hasValidTargets()) {
      driveSystem.arcadeDrive(0, xOutput / 10.0);
    } else {
      driveSystem.stop();
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return rotatePidController.atSetpoint();
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
