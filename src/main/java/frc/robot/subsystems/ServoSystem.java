/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class ServoSystem extends Subsystem {
  edu.wpi.first.wpilibj.Servo plateServo0 = new edu.wpi.first.wpilibj.Servo(0);

  edu.wpi.first.wpilibj.Servo plateServo1 = new edu.wpi.first.wpilibj.Servo(2);

  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new ServoCommand());
  }

  public void toDegree(double deg0, double deg1) {
    plateServo0.setAngle(deg0);
    plateServo1.setAngle(deg1);
  }
  public double getDegree(int servo) {
    if(servo == 0) {
      return plateServo0.getAngle();
    } else {
      return plateServo1.getAngle();
    }
  }
}