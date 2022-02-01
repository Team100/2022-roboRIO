// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.IndexerStageOne;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.IndexerStageOne;
//import frc.robot.subsystems.IndexerStageOne.ActionState;


public class IndexerStageOneDriveForward extends CommandBase {
    public IndexerStageOne indexer;

    /**
     * Creates a new IndexerStageOneDriveForward.
     */
    public IndexerStageOneDriveForward(IndexerStageOne indexer) {
        this.indexer = indexer;
        addRequirements(this.indexer);
        // Use addRequirements() here to declare subsystem dependencies.
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        //this.indexer.actionState = ActionState.MOVE_FOWARD;

        indexer.indexerStageOne.drivePercentOutput(Constants.IndexerConstants.IndexerMotionParameters.STAGE_ONE_PERCENT_OUTPUT_FORWARD);
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
