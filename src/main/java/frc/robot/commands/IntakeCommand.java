/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;

public class IntakeCommand extends Command {

  public IntakeCommand() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.intakeSystem);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    boolean intakeJoy = OI.leftJoy.getRawButton(2);

    boolean intakeJoy2 = OI.leftJoy.getTrigger();
    boolean intakeJoy3 = OI.rightJoy.getRawButton(3);
    boolean intakeJoy4 = OI.leftJoy.getRawButton(3);


    if (intakeJoy) {
      Robot.intakeSystem.intake(0.2, 0.2);
      Robot.shootSystem.shoot(-0.2);
      Robot.canSystem.spinControlPanel(0.5);

    } else if (intakeJoy2) {
      Robot.intakeSystem.intake(-0.5, 0);

    } else if(intakeJoy3) {
      Robot.intakeSystem.intake(0.5, 0.2);
      Robot.shootSystem.shoot(-0.4);
    } else if(intakeJoy4) {
      Robot.intakeSystem.intake(0.5, 0);
    
    } else {
      Robot.intakeSystem.intake(0, 0);
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
