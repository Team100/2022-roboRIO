// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.climber;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Climber;
import frc.robot.Constants.ClimberConstants;
//import frc.robot.Constants.ClimberConstants.*;



public class LockStationary extends CommandBase {
  public boolean done, mainLocked;

  public Climber climber;
  /** Creates a new LockStationary. */
  public LockStationary(Climber climber) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.climber = climber;
    mainLocked = climber.mainLocked();
    addRequirements(this.climber);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    SmartDashboard.putString("Climber Command","Locking Stationaries");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {    //climber is hanging on or lined up with a bar
    if(!mainLocked){//is climber main hooks locked on a bar?
      SmartDashboard.putString("Rnjnsknfds","mains not locked yet");
      climber.setWinch(ClimberConstants.ClimberMotionParameters.CLIMBER_PERCENT_OUTPUT);  //if not pull until you grab it 
      if(climber.mainLocked()){
        mainLocked = true;
        SmartDashboard.putString("Rnjnsknfds","mains now locked");
      }
    }                                                                     
    if(!(climber.mainPosition()>=ClimberConstants.ClimberMotionParameters.CLIMBER_TOP)&&climber.mainLocked()){//if climber is not all the way retracted the hooks are locked on
      climber.setWinch(ClimberConstants.ClimberMotionParameters.CLIMBER_PERCENT_OUTPUT);                      //retract the hooks
      if(climber.mainPosition()>=ClimberConstants.ClimberMotionParameters.TILT_START&&!climber.stationaryLocked()){//while your doing that, if your close enough to the top
        climber.setTilt(ClimberConstants.ClimberMotionParameters.TILT_PERCENT_OUTPUT);                        //start tilting the stationary hooks into position
      }
    }else if(climber.mainPosition()>=ClimberConstants.ClimberMotionParameters.CLIMBER_TOP&&climber.mainLocked()&&!climber.stationaryLocked()&&climber.tiltAngle()>ClimberConstants.ClimberMotionParameters.STATIONARY_LOCK_ANGLE){ //if you are sufficiently retracted and the main hooks are locked and the stationaries are not and your tilt angle is insufficient to drop the stationary hooks onto the bar
      climber.setWinch(0);                                                                                    //stop retracting
      climber.setTilt(ClimberConstants.ClimberMotionParameters.TILT_PERCENT_OUTPUT);                          //tilt until your in position to drop the stationary hooks onto the bar
    }else if(!climber.stationaryLocked()&&mainLocked){                                                                    //if its just the stationary hooks that need to be droped
      climber.setWinch(-ClimberConstants.ClimberMotionParameters.CLIMBER_PERCENT_OUTPUT);                     //drop down to lock 'em
    }

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    climber.setWinch(0);
    climber.setTilt(0);
    SmartDashboard.putString("Climber Command","Stationaries Locked");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return climber.stationaryLocked(); //if stationary hooks locked command done
  }
}
