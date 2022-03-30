// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.climber;

import org.opencv.features2d.FastFeatureDetector;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Constants.ClimberConstants;
import frc.robot.commands.drivetrain.Drive;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Drivetrain;
public class ClimberCenter extends CommandBase {
    /** Creates a new Climb. */
    private final Climber climber;
    private boolean done;

    public ClimberCenter(Climber climber) {
        this.climber = climber;
        done = false;

        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(climber);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        if(climber.tiltAngle()>0){
            climber.setTilt(-ClimberConstants.ClimberMotionParameters.CLIMBER_TILT_ZERO_SPEED);
        }else if(climber.tiltAngle()<0){
            climber.setTilt(ClimberConstants.ClimberMotionParameters.CLIMBER_TILT_ZERO_SPEED);
        }else{
            climber.setTilt(0);
            done = true;
        }
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {

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
        return done;
    }
}
