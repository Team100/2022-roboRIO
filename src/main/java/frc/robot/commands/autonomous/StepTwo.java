// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Drivetrain;

public class StepTwo extends CommandBase {
    private Drivetrain drivetrain;
    private double stopPosition;

    /** Creates a new StepTwo. */
    public StepTwo(Drivetrain drivetrain, double stopPosition) {
        this.drivetrain = drivetrain;
        this.stopPosition = stopPosition;
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(drivetrain);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        drivetrain.setBrakeMode(true);
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        drivetrain.driveWithRamp(Constants.DrivetrainConstants.Autonomous.Speeds.DRIVE_FORWARD_SPEED, Constants.DrivetrainConstants.Autonomous.Speeds.DRIVE_FORWARD_SPEED);
    }


    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        drivetrain.setBrakeMode(false);
        drivetrain.driveWithoutRamp(0, 0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return drivetrain.getCurrentEncoderPosition() >= stopPosition;
    }
}
