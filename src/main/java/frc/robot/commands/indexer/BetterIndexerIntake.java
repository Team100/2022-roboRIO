// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.indexer;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Indexer;

public class BetterIndexerIntake extends CommandBase {
    private Indexer indexer;
    private boolean sensorTwo = false;
    private boolean done = false;

    /** Creates a new BetterIndexerIntake. */
    public BetterIndexerIntake(Indexer indexer) {
        this.indexer = indexer;

        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(indexer);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        done = false;
        sensorTwo = indexer.getSensorTwo();
    }

    private void stop() {
        done = true;
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        if (sensorTwo) {
            if (indexer.getSensorOne() && indexer.getSensorTwo()) stop();
            else {
                indexer.runMotorOne(Constants.IndexerConstants.IndexerMotionParameters.STAGE_ONE_PERCENT_OUTPUT_FORWARD);
                // indexer.runMotorTwo(0);
            }
        } else {
            if (indexer.getSensorOne() && indexer.getSensorTwo()) stop();
            if (indexer.getSensorTwo()) {
                indexer.runMotorTwo(0);
                stop();
            }
        }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {}

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return done;
    }
}
