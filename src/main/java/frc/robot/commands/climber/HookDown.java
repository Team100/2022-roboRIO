// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.climber;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Symphony;
import frc.robot.Constants.ClimberConstants;

public class HookDown extends CommandBase {
  /** Creates a new HooksUp. */
private final Climber climber;
private final Symphony symphony;
boolean done, first;
  public HookDown(Climber climber, Symphony symphony) {  
    // Use addRequirements() here to declare subsystem dependencies.
    this.climber = climber;
    this.symphony = symphony;
    addRequirements(this.climber, this.symphony);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() { 
    done=false;  
    first = true; 
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(climber.mainPosition()>=ClimberConstants.ClimberMotionParameters.CLIMBER_BOTTOM){
      climber.setWinch(0);
      if(first){
        first = false;
        symphony.play();
      }
    }else{
      climber.setWinch(ClimberConstants.ClimberMotionParameters.CLIMBER_PERCENT_OUTPUT);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    climber.setWinch(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return done;
  }
}
