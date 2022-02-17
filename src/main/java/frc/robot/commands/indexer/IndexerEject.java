// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.indexer;

import frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Indexer;

public class IndexerEject extends CommandBase {
    private Indexer indexer;

    /** Creates a new IndexerEject. */
    public IndexerEject(Indexer indexer) {
        this.indexer = indexer;

        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(indexer);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {}

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        indexer.runMotorOne(Constants.IndexerConstants.IndexerMotionParameters.STAGE_ONE_PERCENT_OUTPUT_BACKWARD);
        indexer.runMotorTwo(Constants.IndexerConstants.IndexerMotionParameters.STAGE_TWO_PERCENT_OUTPUT_BACKWARD);  
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
        return false;
    }
}
