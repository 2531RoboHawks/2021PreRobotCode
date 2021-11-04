// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.ClimbCommand;

public class ClimbSystem extends SubsystemBase {
  private Solenoid down = new Solenoid(2);
  private Solenoid up = new Solenoid(3);

  public ClimbSystem() {
    super();
    setDefaultCommand(new ClimbCommand(this));
  }

  public void moveUp() {
    up.set(true);
    down.set(false);
  }

  public void moveDown() {
    down.set(true);
    up.set(false);
  }

  public void stop() {
    down.set(false);
    up.set(false);
  }
}
