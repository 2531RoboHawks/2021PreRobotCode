/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class ControlPanel extends Command {

  edu.wpi.first.wpilibj.Servo plateServo2 = new edu.wpi.first.wpilibj.Servo(3);

  double red = (Math.round(RobotMap.m_colorSensor.getColor().red * 10.0)) / 10.0;
  double green = (Math.round(RobotMap.m_colorSensor.getColor().green * 10.0)) / 10.0;
  double blue = (Math.round(RobotMap.m_colorSensor.getColor().blue * 10.0)) / 10.0;

  public ControlPanel() {
    requires(Robot.canSystem);
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

    if (OI.rightJoy.getRawButton(4) && !gone) {
      press = !press;
      gone = true;

    } else if (!OI.rightJoy.getRawButton(4) && gone) {
      gone = false;
    }

    if (press) {
      plateServo2.setAngle(40);
      String gameData;
      gameData = DriverStation.getInstance().getGameSpecificMessage();
      if (gameData.length() > 0) {
        switch (gameData.charAt(0)) {
          case 'R':
            // Blue
            if (red == 0.2 && green == 0.4 && blue == 0.4) {
              Robot.canSystem.stopControlPanel();
            } else {
              Robot.canSystem.spinControlPanel(0.5);
            }

            break;
          case 'Y':
            // Green
            if (red == 0.2 && green == 0.6 && blue == 0.2) {
              Robot.canSystem.stopControlPanel();
            } else {
              Robot.canSystem.spinControlPanel(0.5);
            }

            break;
          case 'B':
            // Red
            if (red == 0.5 && green == 0.4 && blue == 0.1) {
              Robot.canSystem.stopControlPanel();
            } else {
              Robot.canSystem.spinControlPanel(0.5);
            }

            break;
          case 'G':
            // Yellow
            if (red == 0.3 && green == 0.6 && blue == 0.1) {
              Robot.canSystem.stopControlPanel();
            } else {
              Robot.canSystem.spinControlPanel(0.5);
            }

            break;
          default:
            System.out.println("Invalid");
            break;
        }
      }

    } else if ((OI.gamePad.getRawButton(1) || OI.gamePad.getRawButton(2) || OI.gamePad.getRawButton(3)
    || OI.gamePad.getRawButton(4))) {

      // switch (OI.gamePad.getRawButton()) {
      // case 'R':
      // Blue
      if (OI.gamePad.getRawButton(1)) {
        if (red == 0.2 && green == 0.4 && blue == 0.4) {
          Robot.canSystem.stopControlPanel();
        } else {
          Robot.canSystem.spinControlPanel(1);
        }
      }
      // break;
      // case 'Y':
      // Green
      if (OI.gamePad.getRawButton(2)) {
        if (red == 0.2 && green == 0.6 && blue == 0.2) {
          Robot.canSystem.stopControlPanel();
        } else {
          Robot.canSystem.spinControlPanel(1);
        }
      }
      // break;
      // case 'B':
      // Red
      if (OI.gamePad.getRawButton(3)) {
        if (red == 0.5 && green == 0.4 && blue == 0.1) {
          Robot.canSystem.stopControlPanel();
        } else {
          Robot.canSystem.spinControlPanel(1);
        }
      }
      // break;
      // case 'G':
      // Yellow
      if (OI.gamePad.getRawButton(4)) {
        if (red == 0.3 && green == 0.6 && blue == 0.1) {
          Robot.canSystem.stopControlPanel();
        } else {
          Robot.canSystem.spinControlPanel(1);
        }
      }

    } else {
      Robot.canSystem.stopControlPanel();
      plateServo2.setAngle(180);
    }
    // System.out.println(red + " " + green + " " + blue);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  public void end() {
    Robot.canSystem.stopControlPanel();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
