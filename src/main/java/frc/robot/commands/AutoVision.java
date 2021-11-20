package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Robot;

public class AutoVision extends SequentialCommandGroup {
    public AutoVision() {
        addRequirements(Robot.driveSystem);
        addCommands(new TimeDrive(.5, -0.5, -0.5));
        addCommands(new AimAndShootCommand());
    }
}
