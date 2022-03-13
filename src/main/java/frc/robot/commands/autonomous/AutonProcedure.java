// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
//import frc.robot.commands.automatic.AutoShoot;
import frc.robot.commands.shooter.ShootHigh;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutonProcedure extends SequentialCommandGroup {
    /** Creates a new AutonProcedure. */
    public AutonProcedure(Drivetrain drivetrain, Intake intake, Indexer indexer, Shooter shooter) {
        // Add your commands in the addCommands() call, e.g.
        // addCommands(new FooCommand(), new BarCommand());
        addCommands(new InstantCommand(() -> { drivetrain.zeroCurrentPosition(); }, drivetrain));//zero the drivetrain
        addCommands(new ParallelCommandGroup(new ShootHigh(shooter), new WaitCommand(2)));       //shoot one loaded ball into high goal
        addCommands(new StepOne(intake, indexer, drivetrain));                                   //drive back and grab another ball
        addCommands(new StepTwo(drivetrain));                                                    //drive back to start point(maybe just put drivetrain falcons in brake?)
        //addCommands(new AutoShoot(indexer, shooter));
        addCommands(new ParallelCommandGroup(new ShootHigh(shooter), new WaitCommand(2)));       //shoot your next loaded ball into high goal(needs to be tested)
    }
}