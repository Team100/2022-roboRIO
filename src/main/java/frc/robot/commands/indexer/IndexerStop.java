// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.indexer;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Indexer; 

public class IndexerStop extends CommandBase {
    private Indexer indexer;

    /** Creates a new IndexerStop. */
    public IndexerStop(Indexer indexer) {
        // Use addRequirements() here to declare subsystem dependencies.
        this.indexer = indexer;

        addRequirements(indexer);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        indexer.runMotorOne(0);
        indexer.runMotorTwo(0);
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
