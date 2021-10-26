/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.ToggleButton;

public class IntakeAndShootCommand extends Command {
  private ToggleButton revButton = new ToggleButton(OI.leftJoy, 3);
  private ToggleButton manualButton = new ToggleButton(OI.leftJoy, 8);
  private ToggleButton autoButton = new ToggleButton(OI.leftJoy, 9);
  private long revWillBeReadyAt = -1;

  private Command autoCommand = new AimAndShootCommand();

  private String SHOOT_STATUS = "Shoot Status ";
  private String INTAKE_STATUS = "Intake Status ";

  public IntakeAndShootCommand() {
    requires(Robot.intakeSystem);
    requires(Robot.shootSystem);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    long now = System.currentTimeMillis();

    boolean isRevving = revButton.isToggled();
    boolean isManualControl = manualButton.isToggled();
    boolean isAuto = autoButton.isToggled();
    boolean isRevReady = isRevving && now > revWillBeReadyAt;

    if (isAuto) {
      if (!autoCommand.isRunning()) {
        autoCommand.start();
      }
      SmartDashboard.putString(INTAKE_STATUS, "AUTO");
      SmartDashboard.putString(SHOOT_STATUS, "AUTO");
    } else {
      autoCommand.cancel();

      if (isRevving) {
        if (revWillBeReadyAt == -1) {
          revWillBeReadyAt = now + 10000;
        }
        Robot.shootSystem.shoot(0.85);
        Robot.intakeSystem.bottomWheel(-0.5);
      } else {
        revWillBeReadyAt = -1;
        Robot.shootSystem.stopShoot();
      }
  
      if (OI.leftJoy.getTrigger()) {
        // Shoot
        Robot.intakeSystem.bottomWheel(-0.5);
        if (isRevReady) {
          revWillBeReadyAt = now + 2000;
        }
        SmartDashboard.putString(INTAKE_STATUS, "Shooting");
      } else if (OI.leftJoy.getRawButton(2)) {
        // Intake
        Robot.intakeSystem.bottomWheel(0.3);
        Robot.intakeSystem.intake(0.2);
        SmartDashboard.putString(INTAKE_STATUS, "Intaking");
      } else if (isManualControl) {
        // Manual control
        double power = OI.leftJoy.getZ() / 2;
        Robot.intakeSystem.bottomWheel(power);
        Robot.intakeSystem.intake(power);
        SmartDashboard.putString(INTAKE_STATUS, String.format("Power: %d", (int) (power * 100)));
      } else if (isRevving) {
        // When revving, default to keeping balls in
        Robot.intakeSystem.bottomWheel(0.5);
        SmartDashboard.putString(INTAKE_STATUS, "Keeping balls in");
      } else {
        Robot.intakeSystem.stopAll();
        SmartDashboard.putString(INTAKE_STATUS, "Off");
      }

      if (isRevving) {
        if (isRevReady) {
          SmartDashboard.putString(SHOOT_STATUS, "READY TO SHOOT");
        } else {
          long seconds = (revWillBeReadyAt - now) / 1000;
          SmartDashboard.putString(SHOOT_STATUS, String.format("Ready in %s...", seconds + 1));
        }
      } else {
        SmartDashboard.putString(SHOOT_STATUS, "Not revving");
      }
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
