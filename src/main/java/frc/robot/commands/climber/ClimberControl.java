// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.climber;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.commands.drivetrain.Drive;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Drivetrain;
public class ClimberControl extends CommandBase {
    /** Creates a new Climb. */
    private final Climber climber;
    private final Drivetrain drivetrain;
    private final Joystick leftJoystick;
    private final Joystick rightJoystick;

    private double limiter;

    public ClimberControl(Climber climber, Joystick l, Joystick r, Drivetrain drivetrain) {
        this.climber = climber;
        this.drivetrain = drivetrain;
        leftJoystick = l;
        rightJoystick = r;

        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(climber, drivetrain);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        drivetrain.driveWithoutRamp(0, 0);
        limiter = (-leftJoystick.getZ()+1)/2;
        if (Math.abs(leftJoystick.getX()) > Constants.ClimberConstants.ClimberControls.TILT_CONTROL_DEADZONE) {
            climber.setTilt(-leftJoystick.getX()*limiter);
        } else {
            climber.setTilt(0);
        }

        if (Math.abs(rightJoystick.getX()) > Constants.ClimberConstants.ClimberControls.WINCH_CONTROL_DEADZONE) {
            climber.setWinch(rightJoystick.getX()*limiter);
        } else {
            climber.setWinch(0);
        }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        climber.setTilt(0);
        climber.setWinch(0);
        drivetrain.driveWithoutRamp(0, 0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
