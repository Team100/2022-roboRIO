// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override 
    public void execute() {
        SmartDashboard.putBoolean("sensorLeft", drivetrain.getSensorLeft());
        SmartDashboard.putBoolean("sensorRight", drivetrain.getSensorRight());
        SmartDashboard.putString("Port and Starboard status:", "we are not using port and starboad :(");

        if(!drivetrain.getSensorLeft() && !drivetrain.getSensorRight()){
            drivetrain.set(Constants.DrivetrainConstants.DrivetrainMotion.AlignSpeed, Constants.DrivetrainConstants.DrivetrainMotion.AlignSpeed);
        } else if (drivetrain.getSensorLeft() && !drivetrain.getSensorRight()){
            drivetrain.set(0, Constants.DrivetrainConstants.DrivetrainMotion.AlignSpeed);
        } else if (drivetrain.getSensorRight() && !drivetrain.getSensorLeft()){
            drivetrain.set(Constants.DrivetrainConstants.DrivetrainMotion.AlignSpeed, 0);
        } else if (drivetrain.getSensorLeft() && drivetrain.getSensorRight()){
            drivetrain.set(0, 0);
            done = true;
        }
        SmartDashboard.putBoolean("climber allgined?", done);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        drivetrain.set(0, 0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return done;
    }
}
