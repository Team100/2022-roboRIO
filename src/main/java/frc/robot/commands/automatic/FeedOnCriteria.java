// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.automatic;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.automatic.criteria.ShootEndCriteria;
import frc.robot.commands.automatic.criteria.ShootStartCriteria;
import frc.robot.commands.indexer.IndexerFeed;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Shooter;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class FeedOnCriteria extends SequentialCommandGroup {
    /** Creates a new FeedWithDelay. */
    public FeedOnCriteria(Indexer indexer, Shooter shooter) {
        // Add your commands in the addCommands() call, e.g.
        // addCommands(new FooCommand(), new BarCommand());
        addCommands(new ShootStartCriteria(shooter));
        addCommands(new IndexerFeed(indexer));
        addCommands(new WaitCommand(2)); // no
        addCommands(new ShootEndCriteria(shooter));
    }
}
