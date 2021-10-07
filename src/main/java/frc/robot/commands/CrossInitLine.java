/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrossInitLine extends CommandGroup {
  public CrossInitLine() {
    //Move forward so we can get 5 points for leaving the line in auto
    addSequential(new TimeDrive(2, -0.5, -0.5));
  }

}
