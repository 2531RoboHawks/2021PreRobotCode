/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.util.ArrayList;
import java.util.List;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.AutoScore;
import frc.robot.commands.TeleopGroup;
import frc.robot.commands.VisionCommand;
import frc.robot.commands.CrossInitLine;
import frc.robot.subsystems.ClimbSystem;
import frc.robot.subsystems.DriveSystem;
import frc.robot.subsystems.IntakeSystem;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.ShootSystem;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  // Init Subsystems for use in other classes
  public static final DriveSystem driveSystem = new DriveSystem();
  public static final ShootSystem shootSystem = new ShootSystem();
  public static final IntakeSystem intakeSystem = new IntakeSystem();
  public static final Limelight limelight = new Limelight();
  public static final ClimbSystem climbSystem = new ClimbSystem();

  private TeleopGroup teleopCommand = new TeleopGroup();
  private Command autonomousCommand;
  private SendableChooser<Command> autoChooser = new SendableChooser<>();

  private List<Double> lastAreas = new ArrayList<>();

  @Override
  public void robotInit() {
    CameraServer.getInstance().startAutomaticCapture();
    CameraServer.getInstance().getVideo
    ("USB Camera 0").getSource().setFPS(30);

    autoChooser.addOption("No auto", null);
    autoChooser.setDefaultOption("Cross Init Line", new CrossInitLine());
    autoChooser.addOption("Auto score", new AutoScore());
    autoChooser.addOption("Vision Test", new VisionCommand(driveSystem));
    SmartDashboard.putData("Auto", autoChooser);
    lastAreas.clear();
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();

    SmartDashboard.putBoolean("Sees Target", limelight.hasValidTargets());

    lastAreas.add(limelight.getArea());
    while (lastAreas.size() > 100) {
      lastAreas.remove(0);
    }
    double sum = 0;
    for (Double i : lastAreas) {
      sum += i;
    }
    double average = sum / lastAreas.size();
    SmartDashboard.putNumber("area", average);
  }

  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {

  }

  @Override
  public void autonomousInit() {
    autonomousCommand = autoChooser.getSelected();
    if (autonomousCommand != null) {
      autonomousCommand.schedule();
    }
    teleopCommand.cancel();
  }

  @Override
  public void autonomousPeriodic() {

  }

  @Override
  public void teleopInit() {
    ToggleButton.resetAllbuttons();
    if (autonomousCommand != null) {
      autonomousCommand.cancel();
    }
    teleopCommand.schedule();
  }

  @Override
  public void teleopPeriodic() {
  }

  @Override
  public void testPeriodic() {
  }
}
