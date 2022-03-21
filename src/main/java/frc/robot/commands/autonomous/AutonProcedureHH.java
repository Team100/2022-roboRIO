// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.commands.indexer.IndexerFeedHigh;
import frc.robot.commands.indexer.IndexerStop;
import frc.robot.commands.intake.BetterIntakeStop;
import frc.robot.commands.intake.IntakeIntake;
import frc.robot.commands.shooter.ShootHigh;
import frc.robot.commands.shooter.ShootStop;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutonProcedureHH extends SequentialCommandGroup {
    /** Creates a new AutonProcedure. */
    public AutonProcedureHH(Drivetrain drivetrain, Intake intake, Indexer indexer, Shooter shooter) {
        // Add your commands in the addCommands() call, e.g.
        // addCommands(new FooCommand(), new BarCommand());7
        addCommands(new InstantCommand(() -> { drivetrain.zeroCurrentPosition(); }, drivetrain));//zero the drivetrain

        //addCommands(new ParallelDeadlineGroup(new WaitCommand(3), new ShootLow(shooter),  new IndexerFeedLow(indexer, shooter), new IntakeIntake(intake))); //shoot one loaded ball into high goal
        addCommands(new ParallelDeadlineGroup(new WaitCommand(1), new IntakeIntake(intake)));
        addCommands(new InstantCommand(() -> { shooter.set(0); }, shooter)); //stops the shoot
        addCommands(new StepOne(intake, indexer, drivetrain)); //drive back and grab another ball
        addCommands(new ParallelDeadlineGroup(new StepTwo(drivetrain, -56000), new BetterIntakeStop(intake), new IndexerStop(indexer))); //drive back to start point(maybe just put drivetrain falcons in brake?)
        addCommands(new ParallelDeadlineGroup(new WaitCommand(6), new ShootHigh(shooter),  new IndexerFeedHigh(indexer, shooter))); //shoot your next loaded ball into high goal(needs to be tested)
        
        addCommands(new ParallelCommandGroup(new IndexerStop(indexer), new ShootStop(shooter))); //stop everything
    }
}
