// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.climber.simpleCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;
import frc.robot.Constants.ClimberConstants;

public class HookUpLow extends CommandBase {
  private final Climber climber;
  boolean done, first;
  /** Creates a new HooksUp. */
  public HookUpLow(Climber climber) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.climber = climber;
    addRequirements(this.climber);
    

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() { 
    done=false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(climber.mainPosition()<=ClimberConstants.ClimberMotionParameters.CLIMBER_LOW_BAR_TOP){
      climber.setWinch(0);
      done = true;
    }else{
      climber.setWinch(-ClimberConstants.ClimberMotionParameters.CLIMBER_PERCENT_OUTPUT);
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
