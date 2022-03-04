    // Copyright (c) FIRST and other WPILib contributors.
    // Open Source Software; you can modify and/or share it under the terms of
    // the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.indexer;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Indexer; 

import frc.robot.Constants;

public class IndexerIntake extends CommandBase {
    private Indexer indexer;
    private boolean done = false;

    /** Creates a new IndexerIntake. */
    public IndexerIntake(Indexer indexer) {
        this.indexer = indexer;

        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(indexer);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        // indexer.runMotorOne(Constants.IndexerConstants.IndexerMotionParameters.STAGE_ONE_PERCENT_OUTPUT_FORWARD);
        // indexer.runMotorTwo(Constants.IndexerConstants.IndexerMotionParameters.STAGE_TWO_PERCENT_OUTPUT_FORWARD);
        done = false;
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        if (indexer.getSensorOne() && indexer.getSensorTwo()) {
            SmartDashboard.putString("Indexer command", "Stopped");
            done = true;
        } else {
            if (indexer.getSensorTwo()) {
                SmartDashboard.putString("Indexer command","Running Stage One");
                indexer.runMotorOne(Constants.IndexerConstants.IndexerMotionParameters.STAGE_ONE_PERCENT_OUTPUT_FORWARD);
                indexer.runMotorTwo(0);
            } else {
                SmartDashboard.putString("Indexer command","Running Both Stages");
                indexer.runMotorOne(Constants.IndexerConstants.IndexerMotionParameters.STAGE_ONE_PERCENT_OUTPUT_FORWARD);
                indexer.runMotorTwo(Constants.IndexerConstants.IndexerMotionParameters.STAGE_TWO_PERCENT_OUTPUT_FORWARD);
            }
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
