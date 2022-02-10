// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.Joystick;

public class Drive extends CommandBase {
    private final Drivetrain drivetrain;
    private final Joystick leftJoystick;
    private final Joystick rightJoystick;

    public Drive(Drivetrain dt, Joystick l, Joystick r) {
        drivetrain = dt;
        leftJoystick = l;
        rightJoystick = r;
        
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(drivetrain);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {}

    public double ramp(double motorOutput) {
        double change = motorOutput - limitedJoystick;
        if (change>Constants.DrivetrainConstants.DrivetrainControls.RAMP_LIMIT){
            change = Constants.DrivetrainConstants.DrivetrainControls.RAMP_LIMIT;
        }
        else (if change<-limit) change = -limit;
        limitedJoystick += change;
        return limitedJoystick;
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        double left = ramp(leftJoystick.getY() - rightJoystick.getX());
        double right = ramp(leftJoystick.getY() + rightJoystick.getX());
        double limiter = (-rightJoystick.getZ()+1)/2;
        drivetrain.set((left*limiter), (right*limiter));
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
