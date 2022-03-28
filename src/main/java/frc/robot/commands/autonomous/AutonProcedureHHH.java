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
import frc.robot.commands.indexer.IndexerFeedWayDowntown;
import frc.robot.commands.indexer.IndexerStop;
import frc.robot.commands.indexer.BetterIndexerIntake;
import frc.robot.commands.indexer.IndexerEject;
import frc.robot.commands.intake.BetterIntakeStop;
import frc.robot.commands.intake.IntakeIntake;
import frc.robot.commands.intake.IntakeEject;
import frc.robot.commands.shooter.ShootHigh;
import frc.robot.commands.shooter.ShootStop;
import frc.robot.commands.shooter.ShootWayDowntown;
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
        // addCommands(new FooCommand(), new BarCommand());
        addCommands(new InstantCommand(() -> { drivetrain.zeroCurrentPosition(); }, drivetrain));//zero the drivetrain
        addCommands(new InstantCommand(() -> { drivetrain.setBrakeMode(true); }, drivetrain));//brake mode the drivetrain

        addCommands(new ParallelDeadlineGroup(
            new SequentialCommandGroup(new ParallelDeadlineGroup(new BetterIndexerIntake(indexer), new IntakeIntake(intake)), new WaitCommand(0.2)), //could save 0.3 or so seconds by moving it to keep intaking well driving
            new StepOneCommand(drivetrain, Constants.DrivetrainConstants.Autonomous.Distance.FIRST_BALL_EXPECTED_STOP_FROM_TARMACK_EDGE)));//(new StepOneCommand(intake, indexer, drivetrain, Constants.DrivetrainConstants.Autonomous.Distance.FIRST_BALL_EXPECTED_STOP_FROM_TARMACK_EDGE)));//optimized


        // addCommands(new ParallelDeadlineGroup(new WaitCommand(0.1), new IntakeIntake(intake)));//drop the intake
        // addCommands(new StepOne(intake, indexer, drivetrain)); //drive back and grab another ball
        addCommands(new ParallelDeadlineGroup(new StepTwo(drivetrain, Constants.DrivetrainConstants.Autonomous.Offsets.AUTO_H_H_OFFSET), new BetterIntakeStop(intake), new IndexerStop(indexer), new ShootHigh(shooter))); //drive back to correct point to sink two high shots
        addCommands(new ParallelDeadlineGroup(new WaitCommand(1.4), new ShootHigh(shooter),  new IndexerFeedHigh(indexer, shooter))); //hold down the shoot high button for the same number of seconds as the wait command
        //addCommands(new ParallelDeadlineGroup(new WaitCommand(6), new IntakeEject(intake), new IndexerEject(indexer)));

        addCommands(new ParallelDeadlineGroup(new WaitCommand(0.05), new IndexerStop(indexer), new ShootStop(shooter))); //stop everything
        addCommands(new Turn(drivetrain, -Constants.DrivetrainConstants.Autonomous.Turning.HHH_TURN));//turn to be pointed the right way

        //addCommands(new ParallelDeadlineGroup(new WaitCommand(0.6), new IntakeIntake(intake)));//drop the intake
        addCommands(new StepThree(intake, indexer, drivetrain)); //drive back and grab another ball
        
        
        //method for drive back and shoot
        
        
            // addCommands(new ParallelDeadlineGroup(new StepTwo(drivetrain, 0), new BetterIndexerIntake(indexer), new IntakeStop(intake), new ShootHigh(shooter)));
            // //addCommands(new ParallelDeadlineGroup(new StepTwo(drivetrain, Constants.DrivetrainConstants.Autonomous.Offsets.AUTO_H_H_OFFSET), new BetterIntakeStop(intake), new IndexerStop(indexer), new ShootHigh(shooter))); //drive back to correct point to sink two high shots   
            // addCommands(new ParallelDeadlineGroup(new Turn(drivetrain, -Constants.DrivetrainConstants.Autonomous.Turning.HHH_TURN+Constants.DrivetrainConstants.Autonomous.Turning.SECOND_HHH_OFFSET),new ShootHigh(shooter)));//turn to be pointed the right way

        
            // addCommands(new ParallelDeadlineGroup(new WaitCommand(2), new ShootHigh(shooter),  new IndexerFeedHigh(indexer, shooter))); //hold down the shoot high button for the same number of seconds as the wait command
            // //addCommands(new ParallelDeadlineGroup(new WaitCommand(3), new IndexerEject(indexer), new IntakeEject(intake)));
            // //addCommands(new ParallelDeadlineGroup(new WaitCommand(3), new IntakeEject(intake), new IndexerEject(indexer)));

            // addCommands(new ParallelCommandGroup(new IndexerStop(indexer), new ShootStop(shooter))); //stop everything



        //method for turn and shoot
        
        
        addCommands(new ParallelDeadlineGroup(new Turn(drivetrain, Constants.DrivetrainConstants.Autonomous.Turning.HHH_TURN-Constants.DrivetrainConstants.Autonomous.Turning.SECOND_HHH_OFFSET),new ShootWayDowntown(shooter)));//turn to be pointed the right way
        addCommands(new ParallelDeadlineGroup(new WaitCommand(2), new ShootWayDowntown(shooter),  new IndexerFeedWayDowntown(indexer, shooter))); //hold down the shoot high button for the same number of seconds as the wait command
        addCommands(new ParallelCommandGroup(new IndexerStop(indexer), new ShootStop(shooter))); //stop everything

    }
}
//write so starts shooting as still pulling up to shoot position?(might have to do the stupid have we shot thing using the current of shooter to end that sooner)