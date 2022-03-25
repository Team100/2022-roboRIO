// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.indexer;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Indexer;

public class FirstSensorIntake extends CommandBase {
    private Indexer indexer;
    // private boolean sensorOne = false;
    // private boolean done = false;

    /** Creates a new BetterIndexerIntake. */
    public FirstSensorIntake(Indexer indexer) {
        this.indexer = indexer;

        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(indexer);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        // done = false;
        // sensorTwo = indexer.getSensorOne();
        indexer.runMotorOne(Constants.IndexerConstants.IndexerMotionParameters.STAGE_ONE_PERCENT_OUTPUT_FORWARD);
        // if (!sensorTwo) indexer.runMotorTwo(Constants.IndexerConstants.IndexerMotionParameters.STAGE_TWO_PERCENT_OUTPUT_FORWARD);
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {

    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        indexer.runMotorOne(0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return indexer.getSensorOne();
    }
}
