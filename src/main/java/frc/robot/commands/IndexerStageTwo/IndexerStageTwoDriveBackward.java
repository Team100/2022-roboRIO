// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.IndexerStageTwo;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.Constants;
//import frc.robot.subsystems.IndexerStageTwo.ActionState;
import frc.robot.subsystems.IndexerStageTwo;

public class IndexerStageTwoDriveBackward extends CommandBase {
    IndexerStageTwo indexer;

    /**
     * Creates a new IndexerStageTwoDriveBackward.
     */
    public IndexerStageTwoDriveBackward(IndexerStageTwo indexer) {
        // Use addRequirements() here to declare subsystem dependencies.
        this.indexer = indexer;
        addRequirements(this.indexer);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        //this.indexer.actionState = ActionState.MOVE_BACKWARDS;
        this.indexer.indexerStageTwo.drivePercentOutput(Constants.IndexerConstants.IndexerMotionParameters.STAGE_TWO_PERCENT_OUTPUT_BACKWARD);
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
