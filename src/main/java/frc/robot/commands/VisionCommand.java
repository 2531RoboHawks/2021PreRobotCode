/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.DriveSystem;

public class VisionCommand extends CommandBase {
  private final DriveSystem driveSystem;
  private final PIDController rotatePidController;
  private final PIDController drivePidController;

  public VisionCommand(DriveSystem driveSystem) {
    this.rotatePidController = new PIDController(0.5, 1, 0.01);
    this.drivePidController = new PIDController(0.5, 1, 0.01);
    this.driveSystem = driveSystem;
  }

  @Override
  public void initialize() {
    drivePidController.reset();
    rotatePidController.reset();
    drivePidController.setSetpoint(1);
    rotatePidController.setSetpoint(0);
  }

  @Override
  public void execute() {
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

  @Override
  public boolean isFinished() {
    return rotatePidController.atSetpoint();
  }

  @Override
  public void end(boolean interrupted) {
    driveSystem.stop();
  }
}
