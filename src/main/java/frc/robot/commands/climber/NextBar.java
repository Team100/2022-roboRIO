// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.climber;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
    SmartDashboard.putString("Climber Command","Grabbing Next Bar");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(climber.mainLocked()&&climber.stationaryLocked()&&!behindBar){
      climber.setTilt(0);
      climber.setWinch(-ClimberConstants.ClimberMotionParameters.CLIMBER_PERCENT_OUTPUT);
    }
    if(climber.tiltAngle()>ClimberConstants.ClimberMotionParameters.NEXT_BAR_ANGLE&&!climber.stationaryLocked()&&!behindBar){
      climber.setWinch(0);
      climber.setTilt(-ClimberConstants.ClimberMotionParameters.TILT_PERCENT_OUTPUT);//tilt back until angled with the hooks behind the next bar
      // if(climber.tiltAngle()>ClimberConstants.ClimberMotionParameters.EXTEND_START_ANGLE&&climber.mainPosition()>ClimberConstants.ClimberMotionParameters.NEXT_BAR_DISTANCE){//if you are sufficiently tilted that you can start reaching back and still end up behind the next bar and you haven't reached back enough
      //   climber.setWinch(-ClimberConstants.ClimberMotionParameters.CLIMBER_PERCENT_OUTPUT);//extend main hooks
      // }
    }else if(climber.tiltAngle()<ClimberConstants.ClimberMotionParameters.NEXT_BAR_ANGLE&&!climber.stationaryLocked()&&!behindBar){
      climber.setTilt(0);
      climber.setWinch(-ClimberConstants.ClimberMotionParameters.CLIMBER_PERCENT_OUTPUT);//extend main hooks
    }
    if(climber.mainPosition()<ClimberConstants.ClimberMotionParameters.NEXT_BAR_DISTANCE&&!behindBar){//if main hooks are far back enough
      climber.setWinch(0);//stop extending
      behindBar = true;//you are now behind the bar
    }
    if(behindBar&&climber.tiltAngle()<=ClimberConstants.ClimberMotionParameters.NEXT_BAR_GRAB_ANGLE){//if you are behind the bar
      climber.setTilt(ClimberConstants.ClimberMotionParameters.TILT_PERCENT_OUTPUT);//tilt forward until you can grab the bar
    }else if(behindBar&&climber.tiltAngle()>=ClimberConstants.ClimberMotionParameters.NEXT_BAR_GRAB_ANGLE){
      climber.setTilt(0);
    }
    if(behindBar&&climber.tiltAngle()>=ClimberConstants.ClimberMotionParameters.NEXT_BAR_GRAB_ANGLE&&!climber.mainLocked()){//if you are in position to grab the bar and haven't yet
      climber.setTilt(0);//stop tilting
      climber.setWinch(ClimberConstants.ClimberMotionParameters.CLIMBER_PERCENT_OUTPUT);//retract main hooks
    }
    if(behindBar&&climber.mainLocked()&&climber.mainPosition()>=ClimberConstants.ClimberMotionParameters.CLIMBER_BOTTOM){//if locked on next bar and not retracted sufficiently
      climber.setWinch(ClimberConstants.ClimberMotionParameters.CLIMBER_PERCENT_OUTPUT);//retract main hooks
    }
    if(behindBar&&climber.mainLocked()&&climber.mainPosition()<ClimberConstants.ClimberMotionParameters.CLIMBER_BOTTOM){//if locked on next bar and  retracted sufficiently
      climber.setWinch(0);//stop winch
      climber.setTilt(0);//stop tilting
      SmartDashboard.putString("Climber Command","Next Bar Command finished");
      done = true;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    climber.setWinch(0);
    climber.setTilt(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return done;
  }
}
