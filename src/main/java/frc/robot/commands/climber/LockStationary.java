// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.climber;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Climber;
import frc.robot.Constants.ClimberConstants;
//import frc.robot.Constants.ClimberConstants.*;



public class LockStationary extends CommandBase {
  public boolean done;

  public Climber climber;
  /** Creates a new LockStationary. */
  public LockStationary(Climber climber) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.climber = climber;
    addRequirements(this.climber);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(!(climber.mainPosition()>=ClimberConstants.ClimberMotionParameters.CLIMBER_TOP)&&climber.mainLocked()){
      climber.setWinch(ClimberConstants.ClimberMotionParameters.CLIMBER_PERCENT_OUTPUT);
      if(climber.mainPosition()>=ClimberConstants.ClimberMotionParameters.TILT_START&&!climber.stationaryLocked()){
        climber.setTilt(ClimberConstants.ClimberMotionParameters.TILT_PERCENT_OUTPUT);
      }
    }else if(climber.mainPosition()>=ClimberConstants.ClimberMotionParameters.CLIMBER_TOP&&climber.mainLocked()&&!climber.stationaryLocked()&&climber.tiltAngle()>ClimberConstants.ClimberMotionParameters.STATIONARY_LOCK_ANGLE){
      climber.setWinch(0);
      climber.setTilt(ClimberConstants.ClimberMotionParameters.TILT_PERCENT_OUTPUT);
    }else if(!climber.mainLocked()){
      climber.setWinch(-ClimberConstants.ClimberMotionParameters.CLIMBER_PERCENT_OUTPUT);
    }

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return climber.stationaryLocked();
  }
}
