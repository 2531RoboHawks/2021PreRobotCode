// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.ClimbSystem;

public class ClimbCommand extends CommandBase {
  private ClimbSystem climbSystem;

  public ClimbCommand(ClimbSystem climbSystem) {
    addRequirements(climbSystem);
    this.climbSystem = climbSystem;
  }

  @Override
  public void execute() {
    if (Robot.oi.leftJoy.getRawButton(6)) {
      climbSystem.moveUp();
    } else if (Robot.oi.leftJoy.getRawButton(7)) {
      climbSystem.moveDown();
    } else {
      climbSystem.stop();
    }
  }

  @Override
  public void end(boolean interrupted) {
    climbSystem.stop();
  }
}
