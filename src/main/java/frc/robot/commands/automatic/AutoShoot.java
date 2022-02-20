// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.automatic;

import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import frc.robot.commands.shooter.Shoot;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Shooter;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutoShoot extends ParallelDeadlineGroup {
    /** Creates a new AutoShoot. */
    public AutoShoot(Indexer indexer, Shooter shooter) {
        // Add the deadline command in the super() call. Add other commands using
        // addCommands().
        super(new FeedOnCriteria(indexer, shooter));
        
        addCommands(new Shoot(shooter));
    }
}
