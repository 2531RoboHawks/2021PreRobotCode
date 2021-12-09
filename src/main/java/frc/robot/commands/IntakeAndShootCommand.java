/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.ToggleButton;

public class IntakeAndShootCommand extends CommandBase {
  // private ToggleButton revButton = new ToggleButton(OI.leftJoy, 3);
  // private ToggleButton manualButton = new ToggleButton(OI.leftJoy, 8);
  // public static ToggleButton autoButton = new ToggleButton(OI.leftJoy, 9);
  // private long revWillBeReadyAt = -1;

  // private AimAndShootCommand autoCommand = new AimAndShootCommand();

  public static String SHOOT_STATUS = "Shoot Status ";
  public static String INTAKE_STATUS = "Intake Status ";

  // public IntakeAndShootCommand() {
  //   // addRequirements(Robot.intakeSystem);
  //   // addRequirements(Robot.shootSystem);
  // }

  // @Override
  // public void initialize() {
  // }

  // @Override
  // public void execute() {
  //   long now = System.currentTimeMillis();

  //   boolean isRevving = revButton.isToggled();
  //   boolean isManualControl = manualButton.isToggled();
  //   boolean isAuto = autoButton.isToggled();
  //   boolean isRevReady = isRevving && now > revWillBeReadyAt;

  //   if (isAuto) {
  //     if (!autoCommand.isScheduled()) {
  //       SmartDashboard.putString(INTAKE_STATUS, "AUTO");
  //       SmartDashboard.putString(SHOOT_STATUS, "AUTO");
  //       autoCommand.schedule();
  //     }
  //   } else {
  //     autoCommand.cancel();

  //     if (isRevving) {
  //       if (revWillBeReadyAt == -1) {
  //         revWillBeReadyAt = now + 10000;
  //       }
  //       Robot.shootSystem.shoot(Preferences.getInstance().getDouble("Rev", 0.0));
  //       Robot.intakeSystem.bottomWheel(-0.5);
  //       Robot.intakeSystem.intake(0);
  //     } else {
  //       revWillBeReadyAt = -1;
  //       Robot.shootSystem.stop();
  //     }
  
  //     if (OI.leftJoy.getTrigger()) {
  //       // Shoot
  //       Robot.intakeSystem.bottomWheel(-0.5);
  //       if (isRevReady) {
  //         revWillBeReadyAt = now + 2000;
  //       }
  //       SmartDashboard.putString(INTAKE_STATUS, "Shooting");
  //     } else if (OI.leftJoy.getRawButton(2)) {
  //       // Intake
  //       Robot.intakeSystem.bottomWheel(0.4);
  //       Robot.intakeSystem.intake(0.2);
  //       SmartDashboard.putString(INTAKE_STATUS, "Intaking");
  //     } else if (isManualControl) {
  //       // Manual control
  //       double power = OI.leftJoy.getZ() / 2;
  //       Robot.intakeSystem.bottomWheel(power);
  //       Robot.intakeSystem.intake(power);
  //       SmartDashboard.putString(INTAKE_STATUS, String.format("Power: %d", (int) (power * 100)));
  //     } else if (isRevving) {
  //       // When revving, default to keeping balls in
  //       Robot.intakeSystem.bottomWheel(0.5);
  //       SmartDashboard.putString(INTAKE_STATUS, "Keeping balls in");
  //     } else {
  //       Robot.intakeSystem.stopAll();
  //       SmartDashboard.putString(INTAKE_STATUS, "Off");
  //     }

  //     if (isRevving) {
  //       if (isRevReady) {
  //         SmartDashboard.putString(SHOOT_STATUS, "READY TO SHOOT");
  //       } else {
  //         long seconds = (revWillBeReadyAt - now) / 1000;
  //         SmartDashboard.putString(SHOOT_STATUS, String.format("Ready in %s...", seconds + 1));
  //       }
  //     } else {
  //       SmartDashboard.putString(SHOOT_STATUS, "Not revving");
  //     }
  //   }
  // }

  // @Override
  // public boolean isFinished() {
  //   return false;
  // }

  // @Override
  // public void end(boolean interrupted) {
  //   if (!interrupted) {
  //     Robot.shootSystem.stop();
  //     Robot.intakeSystem.stopAll();
  //   }
  // }
}
