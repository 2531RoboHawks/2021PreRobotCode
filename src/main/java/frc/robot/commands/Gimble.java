/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
// import frc.robot.OI;
import frc.robot.Robot;

public class Gimble extends Command {
  public Gimble() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.servoSystem);

  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  boolean press = false;
  boolean gone = false;

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    if (OI.rightJoy.getRawButton(2) && !gone) {
      press = !press;
      gone = true;

    } else if (!OI.rightJoy.getRawButton(2) && gone) {
      gone = false;
    }

    if (press) {
      Robot.servoSystem.toDegree(180, 180);
    } else {
      Robot.servoSystem.toDegree(180, 10);
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
