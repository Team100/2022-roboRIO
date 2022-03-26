// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;

public class StepOneCommand extends CommandBase {
    private Drivetrain drivetrain;
    private Intake intake;
    private Indexer indexer;
    private double stopPosition;

    /** Creates a new StepTwo. */
    public StepOneCommand(Intake intake, Indexer indexer, Drivetrain drivetrain, double stopPosition) {
        this.drivetrain = drivetrain;
        this.indexer = indexer;
        this.intake = intake;
        this.stopPosition = stopPosition;
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(drivetrain, indexer, intake);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        drivetrain.setBrakeMode(true);
        //returnDistance = drivetrain.getCurrentEncoderPosition();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        drivetrain.driveWithError(Constants.DrivetrainConstants.Autonomous.Speeds.DRIVE_REVERSE_SPEED, Constants.DrivetrainConstants.Autonomous.Speeds.DRIVE_REVERSE_SPEED, stopPosition);
    }


    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        drivetrain.setBrakeMode(true);
        drivetrain.driveWithoutRamp(0, 0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return drivetrain.getCurrentEncoderPosition() >= stopPosition;
    }
}
