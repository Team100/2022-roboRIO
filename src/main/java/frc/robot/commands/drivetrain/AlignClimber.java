// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Drivetrain;

public class AlignClimber extends CommandBase {
    private Drivetrain drivetrain;
    private boolean done = false;
    /** Creates a new AlignClimber. */
    public AlignClimber(Drivetrain drivetrain) {
        this.drivetrain = drivetrain;
        addRequirements(drivetrain);
        // Use addRequirements() here to declare subsystem dependencies.
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        done = false;
        drivetrain.setBrakeMode(true);
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override 
    public void execute() {
        // SmartDashboard.putBoolean("sensorLeft", drivetrain.getSensorLeft());
        // SmartDashboard.putBoolean("sensorRight", drivetrain.getSensorRight());
        //SmartDashboard.putString("Port and Starboard status:", "we are not using port and starboad :(");

        if(!drivetrain.getSensorLeft() && !drivetrain.getSensorRight()){
            // SmartDashboard.putString("climber turning?", "0");
            drivetrain.driveWithoutRamp(Constants.DrivetrainConstants.DrivetrainMotion.ALIGN_SPEED, Constants.DrivetrainConstants.DrivetrainMotion.ALIGN_SPEED);
        } else if (drivetrain.getSensorLeft() && !drivetrain.getSensorRight()){
            // SmartDashboard.putString("climber turning?", "first");
            drivetrain.driveWithoutRamp(0, Constants.DrivetrainConstants.DrivetrainMotion.ALIGN_SPEED);
        } else if (drivetrain.getSensorRight() && !drivetrain.getSensorLeft()){
            // SmartDashboard.putString("climber turning?", "2");
            drivetrain.driveWithoutRamp(Constants.DrivetrainConstants.DrivetrainMotion.ALIGN_SPEED, 0);
        } else if (drivetrain.getSensorLeft() && drivetrain.getSensorRight()){
            // SmartDashboard.putString("climber turning?", "4");

            drivetrain.driveWithoutRamp(0, 0);
            done = true;
        }
        // SmartDashboard.putBoolean("climber allgined?", done);
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
        return done;
    }
}
