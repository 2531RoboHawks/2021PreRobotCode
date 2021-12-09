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

    String controls = Robot.controlChooser.getSelected();
    if (controls == null) controls = "airplane-tank";
    if (controls.equals("airplane-tank")) {
      driveSystem.tankDrive(
        OI.leftJoy.getY() * (fast ? 1.0 : 0.6),
        OI.rightJoy.getY() * (fast ? 1.0 : 0.6)
      );
    } else if (controls.equals("airplane-arade")) {
      driveSystem.arcadeDrive(
        OI.leftJoy.getY() * (fast ? 1.0 : 0.6),
        -OI.leftJoy.getX() * (fast ? 1.0 : 0.6)
      );
    } else if (controls.equals("joystick-arcade")) {
      driveSystem.arcadeDrive(
        OI.gamepad.getY() * (fast ? 1.0 : 0.6),
        -OI.gamepad.getX() * (fast ? 1.0 : 0.6)
      );
    } else if (controls.equals("joystick-car")) {
      driveSystem.arcadeDrive(
        (-OI.gamepad.getRawAxis(3) + OI.gamepad.getRawAxis(2)) * (fast ? 1.0 : 0.6),
        -OI.gamepad.getX() * (fast ? 1.0 : 0.6)
      );
    } else {
      System.out.println("Unknown mode " + controls);
    }
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
