    // Copyright (c) FIRST and other WPILib contributors.
    // Open Source Software; you can modify and/or share it under the terms of
    // the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.indexer;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Indexer;  

public class Intake extends CommandBase {
    private Indexer indexer;
    private boolean done = false;

    /** Creates a new Indexer. */
    public Intake(Indexer indexer) {
        // Use addRequirements() here to declare subsystem dependencies.
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {}

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        if (indexer.getSensorOne() && indexer.getSensorTwo()) {
            done = true;
            return;
        }
        if (indexer.getSensorTwo()) {
            indexer.stopMotor(2);
            indexer.runMotor(1);
        } else {
            indexer.runMotor(1);
            indexer.runMotor(2);
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
