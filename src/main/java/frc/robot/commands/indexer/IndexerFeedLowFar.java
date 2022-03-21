// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.indexer;

import static frc.robot.Constants.IndexerConstants.IndexerMotionParameters.*;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Shooter;

public class IndexerFeedLowFar extends CommandBase {
    private Indexer indexer;
    private Shooter shooter;
    private boolean done = false;

    /** Creates a new IndexerFeed. */
    public IndexerFeedLowFar(Indexer indexer, Shooter shooter) {
        this.indexer = indexer;
        this.shooter = shooter;

        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(indexer);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        done = false;
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        if (shooter.speed()>Constants.ShooterConstants.ShooterMotionParameters.NOMINAL_LOW_FAR_VELOCITY) {
            indexer.runMotorOne(STAGE_ONE_PERCENT_OUTPUT_FORWARD);
            indexer.runMotorTwo(STAGE_TWO_PERCENT_OUTPUT_FORWARD);
        } else {
            indexer.runMotorOne(0);
            indexer.runMotorTwo(0);
        }
    }
        
    

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        indexer.runMotorOne(0);
        indexer.runMotorTwo(0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return done;
    }
}
