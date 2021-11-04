/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.OI;
import frc.robot.Robot;

/**
 * An example command. You can replace me with your own command.
 */
public class Drive extends CommandBase {
  public Drive() {
    addRequirements(Robot.driveSystem);
    Robot.driveSystem.shiftGear(false);
  }

  @Override
  public void initialize() {

  }

  @Override
  public void execute() {
    if (OI.rightJoy.getRawButton(1)) {
      Robot.driveSystem.shiftGear(true);
    } else {
      Robot.driveSystem.shiftGear(false);
    }

    double leftX = OI.leftJoy.getRawAxis(1);
    double leftY = OI.rightJoy.getRawAxis(1);

    Robot.driveSystem.tankDrive(leftX, leftY);
  }

  @Override
  public boolean isFinished() {
    return false;
  }

  @Override
  public void end(boolean interrupted) {
    Robot.driveSystem.stop();
  }
}
