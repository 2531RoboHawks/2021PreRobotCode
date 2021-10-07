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
public class ShootIntakeSystem extends Subsystem {
  TalonSRX sweep = new TalonSRX(9);
  TalonSRX shooter = new TalonSRX(10);
  TalonSRX bottomIntake = new TalonSRX(12);
  TalonSRX topIntake = new TalonSRX(13);

  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void shoot(double pow) {
    shooter.set(ControlMode.PercentOutput, pow);
  }

  public void stopShoot() {
    shoot(0);
  }

}
