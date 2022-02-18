// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.XboxController;

public class Drive extends CommandBase {
    private final Drivetrain drivetrain;
    private final XboxController xboxController;

    public Drive(Drivetrain dt, XboxController x) {
        drivetrain = dt;
        xboxController = x;
        
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(drivetrain);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {}

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        double longVel = xboxController.getRightTriggerAxis() - xboxController.getLeftTriggerAxis();

        double left = longVel - xboxController.getLeftX();
        double right = longVel + xboxController.getLeftX();
        System.out.println(left + "-----" + right);
        drivetrain.driveWithRamp(left*0.5, right*0.5);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {}

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
