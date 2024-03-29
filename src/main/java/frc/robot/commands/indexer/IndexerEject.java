// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.indexer;

import frc.robot.Constants.IndexerConstants.*;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;

public class IndexerEject extends CommandBase {
    /** Creates a new IndexerEject. */
    private Indexer indexer;
    // private Intake intake;

    public IndexerEject(Indexer indexer) {
        // Use addRequirements() here to declare subsystem dependencies.
        this.indexer = indexer;
        // this.intake = intake;
        addRequirements(indexer);
        // addRequirements(intake);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        indexer.runMotorOne(IndexerMotionParameters.STAGE_ONE_PERCENT_OUTPUT_BACKWARD);
        indexer.runMotorTwo(IndexerMotionParameters.STAGE_TWO_PERCENT_OUTPUT_BACKWARD);  

        // intake.runSpinner(-Constants.IntakeConstants.IntakeMotionParameters.INTAKE_SPINNER_PERCENT_OUTPUT);
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
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
