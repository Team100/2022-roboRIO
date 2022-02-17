// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.climb;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Climber;
public class ClimberControl extends CommandBase {
    /** Creates a new Climb. */
    private final Climber climber;
    private final Joystick gamepad;

    public ClimberControl(Climber climber, Joystick gamepad) {
        this.climber = climber;
        this.gamepad = gamepad;

        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(climber);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        // Winch
        if (Math.abs(gamepad.getRawAxis(3)) < 0.05){
            climber.setWinch(0);
        } else {
            climber.setWinch(-gamepad.getRawAxis(3));
        }

        //Tilt
        if (gamepad.getRawButton(Constants.ClimberConstants.ClimberControls.TILT_FORWARD_GAMEPAD_BUTTON)) {
            climber.setTilt(Constants.ClimberConstants.ClimberMotionParameters.TILT_PERCENT_OUTPUT);
        } else if (gamepad.getRawButton(Constants.ClimberConstants.ClimberControls.TILT_REVERSE_GAMEPAD_BUTTON)) {
            climber.setTilt(-Constants.ClimberConstants.ClimberMotionParameters.TILT_PERCENT_OUTPUT);
        }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        climber.setTilt(0);
        climber.setWinch(0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
