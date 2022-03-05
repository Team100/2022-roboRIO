// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.climber;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.ClimberConstants;
import frc.robot.subsystems.Climber;



public class NextBar extends CommandBase {
  public boolean done, behindBar;

  public Climber climber;
  /** Creates a new LockStationary. */
  public NextBar(Climber climber) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.climber = climber;
    addRequirements(this.climber);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    done = false;
    behindBar = false;

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(climber.tiltAngle()<ClimberConstants.ClimberMotionParameters.NEXT_BAR_ANGLE){
      climber.setTilt(-ClimberConstants.ClimberMotionParameters.TILT_PERCENT_OUTPUT);//tilt back until angled with the hooks behind the next bar
      if(climber.tiltAngle()>ClimberConstants.ClimberMotionParameters.EXTEND_START_ANGLE){//if you are sufficiently tilted that you can start reaching back and still end up behind the next bar
        climber.setWinch(-ClimberConstants.ClimberMotionParameters.CLIMBER_PERCENT_OUTPUT);//extend main hooks
      }
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return done;
  }
}
