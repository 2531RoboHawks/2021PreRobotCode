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

  public VisionCommand(DriveSystem driveSystem) {
    this.rotatePidController = new PIDController(0.03, 0.04, 0.005);
    this.driveSystem = driveSystem;
  }

  @Override
  public void initialize() {
    rotatePidController.reset();
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

    double xOutput = rotatePidController.calculate(tx);

    if (Robot.limelight.hasValidTargets()) {
      driveSystem.arcadeDrive(0, xOutput);
    }
  }

  @Override
  public boolean isFinished() {
    // rotatePidController.setTolerance(0.1);
    if (Robot.limelight.hasValidTargets()) {
      return rotatePidController.atSetpoint();
    } else {
      return false;
    }
  }

  @Override
  public void end(boolean interrupted) {
    driveSystem.stop();
  }
}
