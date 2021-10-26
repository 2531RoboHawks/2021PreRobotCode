package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.Robot;

public class ShootAllBalls extends CommandGroup {
    public ShootAllBalls() {
        for (int i = 0; i < 3; i++) {
            addSequential(new InstantCommand(() -> {
              Robot.intakeSystem.stopAll();
            }));
            addSequential(new WaitCommand(2));
            addSequential(new InstantCommand(() -> {
              Robot.intakeSystem.bottomWheel(-0.75);
            }));
            addSequential(new WaitCommand(0.25));
            addSequential(new InstantCommand(() -> {
              Robot.intakeSystem.stopAll();
            }));
        }      
    }
}
