// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.automatic;

import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import frc.robot.commands.indexer.IndexerIntake;
import frc.robot.commands.intake.IntakeIntake;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class IntakeCargo extends ParallelDeadlineGroup {
    /** Creates a new IntakeCargo. */
    public IntakeCargo(IntakeIntake intake, IndexerIntake index) {
        // Add your commands in the addCommands() call, e.g.
        // addCommands(new FooCommand(), new BarCommand());
        super(index);
        addCommands(intake);
    }
}
