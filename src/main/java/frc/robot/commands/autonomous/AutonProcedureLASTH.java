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
public class AutonProcedureLASTH extends SequentialCommandGroup {
    /** Creates a new AutonProcedure. */
    public AutonProcedureLASTH(Drivetrain drivetrain, Intake intake, Indexer indexer, Shooter shooter) {
        // Add your commands in the addCommands() call, e.g.
        // addCommands(new FooCommand(), new BarCommand());
        //addCommands(new InstantCommand(() -> { drivetrain.zeroCurrentPosition(); }, drivetrain));//zero the drivetrain
        addCommands(new Turn(drivetrain, Constants.DrivetrainConstants.Autonomous.Turning.HHH_TURN));//turn to be pointed the right way

        //addCommands(new ParallelDeadlineGroup(new WaitCommand(0.6), new IntakeIntake(intake)));//drop the intake
        addCommands(new StepOne(intake, indexer, drivetrain)); //drive back and grab another ball
        addCommands(new ParallelDeadlineGroup(new ReturnLastH(drivetrain, 0), new BetterIntakeStop(intake), new IndexerStop(indexer), new ShootHigh(shooter)));
        //addCommands(new ParallelDeadlineGroup(new StepTwo(drivetrain, Constants.DrivetrainConstants.Autonomous.Offsets.AUTO_H_H_OFFSET), new BetterIntakeStop(intake), new IndexerStop(indexer), new ShootHigh(shooter))); //drive back to correct point to sink two high shots   
        addCommands(new ParallelDeadlineGroup(new Turn(drivetrain, -Constants.DrivetrainConstants.Autonomous.Turning.HHH_TURN)),new ShootHigh(shooter));//turn to be pointed the right way

        
        addCommands(new ParallelDeadlineGroup(new WaitCommand(3), new ShootHigh(shooter),  new IndexerFeedHigh(indexer, shooter))); //hold down the shoot high button for the same number of seconds as the wait command
        addCommands(new ParallelCommandGroup(new IndexerStop(indexer), new ShootStop(shooter))); //stop everything
    }
}
