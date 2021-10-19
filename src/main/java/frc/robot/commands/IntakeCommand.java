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
import frc.robot.ToggleButton;

public class IntakeCommand extends Command {
  ToggleButton shootButton = new ToggleButton(OI.leftJoy, 3);
  ToggleButton manualButton = new ToggleButton(OI.leftJoy, 8);

  public IntakeCommand() {
    requires(Robot.intakeSystem);
    requires(Robot.shootSystem);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    // Rev up Flywheel
    boolean revving = shootButton.isToggled();
    if (revving) {
      Robot.shootSystem.shoot(0.85);
      Robot.intakeSystem.bottomWheel(-0.5);
    } else {
      Robot.shootSystem.stopShoot();
    }

    boolean manual = manualButton.isToggled();
    if (OI.leftJoy.getTrigger()) {
      // Shoot
      Robot.intakeSystem.bottomWheel(-0.5);
    } else if (OI.leftJoy.getRawButton(2)) {
      // Intake
      Robot.intakeSystem.bottomWheel(0.3);
      Robot.intakeSystem.intake(0.2);
    } else if (manual) {
      Robot.intakeSystem.bottomWheel(OI.leftJoy.getZ() / 2);
      Robot.intakeSystem.intake(OI.leftJoy.getZ() / 2);
    } else if (revving) {
      Robot.intakeSystem.bottomWheel(0.5);
    } else {
      Robot.intakeSystem.stopAll();
    }
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.shootSystem.stopShoot();
    Robot.intakeSystem.stopAll();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
