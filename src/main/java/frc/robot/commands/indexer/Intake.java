    // Copyright (c) FIRST and other WPILib contributors.
    // Open Source Software; you can modify and/or share it under the terms of
    // the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.indexer;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Indexer; 

import frc.robot.Constants;

public class Intake extends CommandBase {
    private Indexer indexer;
    private boolean done = false;

    /** Creates a new Indexer. */
    public Intake(Indexer indexer) {
        this.indexer = indexer;

        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(indexer);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        System.out.println("-----------------------made it--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("SensorOne: " + indexer.getSensorOne());
        System.out.println("SensorTwo: " + indexer.getSensorTwo());
        if (indexer.getSensorOne() && indexer.getSensorTwo()) {
            System.out.println("Stopped");
            done = true;
        } else {
            if (indexer.getSensorTwo()) {
                System.out.println("Running Stage One");
                indexer.runMotorOne(Constants.IndexerConstants.IndexerMotionParameters.STAGE_ONE_PERCENT_OUTPUT_FORWARD);
                indexer.runMotorTwo(0);
            } else {
                System.out.println("Running Both Stages");
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
