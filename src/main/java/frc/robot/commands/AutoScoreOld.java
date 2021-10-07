/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoScoreOld extends CommandGroup {
  long currentTime = System.currentTimeMillis();

  public AutoScoreOld() {

    addSequential(new TimeDrive(2.8, 0.5, 0.5));
    // addParallel(new TimeShoot(), 10);
    addSequential(new IntakeAuto(-0.5, 0, 5000), 10);
    


  }

}
