/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

public class ControlPanelSystem extends Subsystem {
  TalonSRX panelWheel = new TalonSRX(11);

  public ControlPanelSystem() {

  }

  @Override
  protected void initDefaultCommand() {

  }

  public void spinControlPanel(double pow) {

    panelWheel.set(ControlMode.PercentOutput, pow);
  }

  public void stopControlPanel() {
    panelWheel.set(ControlMode.PercentOutput, 0);
  }

  // public void activateLifter()
  // {
  // panelLifter.set(ControlMode.PercentOutput, 0.5);
  // }

  // public void deactivateLifter()
  // {
  // panelLifter.set(ControlMode.PercentOutput, 0);
  // }

}
