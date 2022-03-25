// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
//import frc.robot.commands.automatic.IntakeCargo;
import frc.robot.commands.indexer.BetterIndexerIntake;
import frc.robot.commands.indexer.FirstSensorIntake;
import frc.robot.commands.intake.IntakeIntake;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class StepThree extends ParallelRaceGroup {
    // private int distanceBack;
    // private Drivetrain drivetrain;
    /** Creates a new StepOne. */
    public StepThree(Intake intake, Indexer indexer, Drivetrain drivetrain) {
        // Add the deadline command in the super() call. Add other commands using
        // addCommands().
        //super(new StepOneEndCriteria(indexer));
        super(new SequentialCommandGroup(new ParallelDeadlineGroup(new FirstSensorIntake(indexer), new IntakeIntake(intake)), new WaitCommand(0.2))); //run the standerd intake command and stop step one once a ball has been intaken
        
        // this.drivetrain = drivetrain;
    
        addCommands(new RunCommand(() -> drivetrain.driveWithError(Constants.DrivetrainConstants.Autonomous.Speeds.DRIVE_REVERSE_SPEED, Constants.DrivetrainConstants.Autonomous.Speeds.DRIVE_REVERSE_SPEED, Constants.DrivetrainConstants.Autonomous.Distance.THIRD_BALL_OFFSET_FROM_SHOOT_POSITION), drivetrain)
                    .until(drivetrain::getAutoEnd)
                    .andThen(new InstantCommand(() -> { drivetrain.driveWithoutRamp(0, 0); }, drivetrain))
        );
    }

    // private boolean getAutoEnd() {
    //     return drivetrain.getAutoEnd(this.distanceBack);
    // }
}
