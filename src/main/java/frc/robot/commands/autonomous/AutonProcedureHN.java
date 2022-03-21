// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.commands.indexer.IndexerFeedHigh;
import frc.robot.commands.indexer.IndexerStop;
import frc.robot.commands.intake.IntakeIntake;
import frc.robot.commands.intake.IntakeStop;
import frc.robot.commands.shooter.ShootHigh;
import frc.robot.commands.shooter.ShootStop;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutonProcedureHN extends SequentialCommandGroup {
    /** Creates a new AutonProcedure. */
    public AutonProcedureHN(Drivetrain drivetrain, Intake intake, Indexer indexer, Shooter shooter) {
        // Add your commands in the addCommands() call, e.g.
        // addCommands(new FooCommand(), new BarCommand());
        addCommands(new InstantCommand(() -> { drivetrain.zeroCurrentPosition(); }, drivetrain));//zero the drivetrain
        addCommands(new RunCommand(() -> 
                drivetrain.driveWithRamp(Constants.DrivetrainConstants.Autonomous.Speeds.DRIVE_REVERSE_SPEED, Constants.DrivetrainConstants.Autonomous.Speeds.DRIVE_REVERSE_SPEED), drivetrain)
                    .until(drivetrain::getAutoEnd)
                    .andThen(new InstantCommand(() -> { drivetrain.driveWithoutRamp(0, 0); }, drivetrain)));
        addCommands(new ParallelDeadlineGroup(new WaitCommand(3), new ShootHigh(shooter),  new IndexerFeedHigh(indexer, shooter), new IntakeIntake(intake))); //shoot one loaded ball into high goal
        addCommands(new ParallelCommandGroup(new IndexerStop(indexer), new IntakeStop(intake), new ShootStop(shooter))); //stop everything
    }
}
