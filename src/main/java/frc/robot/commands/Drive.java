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
  boolean lastgear = false;

  // TurnToAngle turn = new TurnToAngle(RobotMap.gyro.getAngle() + 90);
  public Drive() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.driveSystem);
    Robot.driveSystem.shiftGear(false);
    lastgear = Robot.driveSystem.isHighGear();
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
      if (lastgear != true) {
        System.out.println("high gear");
        lastgear = true;
      }
    } else {
      Robot.driveSystem.shiftGear(false);
      if (lastgear != false) {
        System.out.println("low gear");
        lastgear = false;
      }
    }

    double leftX = OI.leftJoy.getRawAxis(1);
    double leftY = OI.rightJoy.getRawAxis(1);

    Robot.driveSystem.tankDrive(leftX, leftY);

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.driveSystem.stop();
    // turn.close();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
