/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShootSystem extends SubsystemBase {
  private TalonSRX sweep = new TalonSRX(9);
  private TalonSRX shooter = new TalonSRX(10);
  private TalonSRX bottomIntake = new TalonSRX(12);
  private TalonSRX topIntake = new TalonSRX(13);

  public void shoot(double pow) {
    // SmartDashboard.putString("Revwheel Diagnostic", "" + shooter.getMotorOutputVoltage());
    // SmartDashboard.putString("Revwheel Current", shooter.getSupplyCurrent());
    // SmartDashboard.putString("Revwheel Current", shooter.getSupplyCurrent());
    // shooter.getSupplyCurrent();
    shooter.set(ControlMode.PercentOutput, pow);
  }

  public void stop() {
    shoot(0);
  }
}
