/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;

/**
 * An example command. You can replace me with your own command.
 */
public class Drive extends Command {
  public Drive() {
    requires(Robot.driveSystem);
    Robot.driveSystem.shiftGear(false);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
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
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.driveSystem.stop();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
