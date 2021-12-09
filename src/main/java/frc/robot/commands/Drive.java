/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.OI;
import frc.robot.ToggleButton;
import frc.robot.subsystems.DriveSystem;

/**
 * An example command. You can replace me with your own command.
 */
public class Drive extends CommandBase {
  private DriveSystem driveSystem;
  private ToggleButton button = new ToggleButton(OI.gamepad, 1);

  public Drive(DriveSystem driveSystem) {
    addRequirements(driveSystem);
    this.driveSystem = driveSystem;
    driveSystem.shiftGear(false);
  }

  @Override
  public void initialize() {

  }

  @Override
  public void execute() {
    // if (OI.rightJoy.getRawButton(1)) {
    //   driveSystem.shiftGear(true);
    // } else {
    // }
    boolean fast = button.isToggled();
    // driveSystem.shiftGear(fast);

    // double leftX = OI.leftJoy.getRawAxis(1);
    // double leftY = OI.rightJoy.getRawAxis(1);

    double mult = fast ? 1.0 : 0.6;
    driveSystem.arcadeDrive(OI.gamepad.getY() * mult, -OI.gamepad.getX() * mult);
  }

  @Override
  public boolean isFinished() {
    return false;
  }

  @Override
  public void end(boolean interrupted) {
    driveSystem.stop();
  }
}
