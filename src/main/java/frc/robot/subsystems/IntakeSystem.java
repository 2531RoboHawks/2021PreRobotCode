/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class IntakeSystem extends Subsystem {
  TalonSRX sweep = new TalonSRX(9);
  TalonSRX bottomIntake = new TalonSRX(12);
  TalonSRX topIntake = new TalonSRX(13);

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void intake(double pow0, double pow1) {
    bottomIntake.set(ControlMode.PercentOutput, pow0);
    topIntake.set(ControlMode.PercentOutput, pow1);
  }

  public void activateSweeper(boolean run) {
    if (run) {
      sweep.set(ControlMode.PercentOutput, 0.1);
    } else {
      sweep.set(ControlMode.PercentOutput, 0);
    }
  }

  public void stopIntake() {
    intake(0, 0);
  }
}
