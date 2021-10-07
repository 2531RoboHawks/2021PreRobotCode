/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import org.photonvision.PhotonCamera;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.AutoScore;
import frc.robot.commands.ClimberCommand;
import frc.robot.commands.ControlPanel;
import frc.robot.commands.Drive;
import frc.robot.commands.Gimble;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.ShootCommand;
import frc.robot.commands.CrossInitLine;
import frc.robot.commands.VisionCommand;
import frc.robot.subsystems.ClimberSystem;
import frc.robot.subsystems.ControlPanelSystem;
import frc.robot.subsystems.DriveSystem;
import frc.robot.subsystems.IntakeSystem;
import frc.robot.subsystems.ServoSystem;
import frc.robot.subsystems.ShootIntakeSystem;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  // Init Subsystems for use in other classes
  public static DriveSystem driveSystem = new DriveSystem();
  public static ControlPanelSystem canSystem = new ControlPanelSystem();
  public static ServoSystem servoSystem = new ServoSystem();
  public static ShootIntakeSystem shootSystem = new ShootIntakeSystem();
  public static IntakeSystem intakeSystem = new IntakeSystem();
  public static ClimberSystem climberSystem = new ClimberSystem();
  public static PhotonCamera camera = new PhotonCamera("HD_USB_Camera");
  public static OI m_oi;

  public ControlPanel panel = new ControlPanel();
  public ShootCommand shootCommand = new ShootCommand();
  public IntakeCommand intakeCommand = new IntakeCommand();
  public Gimble gim = new Gimble();
  public ClimberCommand cc = new ClimberCommand();
  public VisionCommand vc = new VisionCommand();

  Command m_autonomousCommand;
  SendableChooser<Command> auto = new SendableChooser<Command>();

  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {
    m_oi = new OI();
    initSmartDashboard();

    // RobotMap.gyro.calibrate();
    // startTime = System.currentTimeMillis();
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for
   * items like diagnostics that you want ran during disabled, autonomous,
   * teleoperated and test.
   *
   * <p>
   * This runs after the mode specific periodic functions, but before LiveWindow
   * and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    updateSmartDashboard();
    panel.start();
    shootCommand.start();
  }

  /**
   * This function is called once each time the robot enters Disabled mode. You
   * can use it to reset any subsystem information you want to clear when the
   * robot is disabled.
   */
  @Override
  public void disabledInit() {
    // RobotMap.gyro.calibrate();
    // startTime = System.currentTimeMillis();
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();

    panel.close();
    shootCommand.close();
    intakeCommand.close();
    gim.close();
    cc.close();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable chooser
   * code works with the Java SmartDashboard. If you prefer the LabVIEW Dashboard,
   * remove all of the chooser code and uncomment the getString code to get the
   * auto name from the text box below the Gyro
   *
   * <p>
   * You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons to
   * the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = auto.getSelected();
    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
    vc.start();
  }

  // private double startTime;

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }

  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();

    gim.start();
    intakeCommand.start();
    cc.start();

  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }

  public void initSmartDashboard() {
    auto.setDefaultOption("Cross Init Line - 5points", new CrossInitLine());
    auto.addOption("Not auto", null);
    auto.addOption("Auto score NEW", new AutoScore());
    SmartDashboard.putData("Auto", auto);
  }
  

  public void updateSmartDashboard() {

  }

}