// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.Joystick;

public class DriveFurious extends CommandBase {
    private final Drivetrain drivetrain;
    private final Joystick leftJoystick;
    private final Joystick rightJoystick;

    public DriveFurious(Drivetrain dt, Joystick l, Joystick r) {
        drivetrain = dt;
        leftJoystick = l;
        rightJoystick = r;
        
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(drivetrain);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {}

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        double left = leftJoystick.getY() - rightJoystick.getX();
        double right = leftJoystick.getY() + rightJoystick.getX();
        drivetrain.driveWithoutRamp(left, right);
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
