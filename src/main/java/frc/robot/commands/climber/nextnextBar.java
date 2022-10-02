// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.climber;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;

public class nextnextBar extends CommandBase {
  public Climber climber;
  public boolean done;
  /** Creates a new nextnextBar. */
  public nextnextBar(Climber climber) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.climber = climber;
    addRequirements(this.climber);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    done = false;
    climber.setTilt(-0.8);
    climber.setWinch(-0.4);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(climber.tiltAngle() <= 1){
      climber.setTilt(0);
    }
    if(climber.mainPosition() <= -202500){
      climber.setWinch(0);
      if(climber.mainLocked() == false){
        climber.setTilt(0.4);
      }else{
        done = true;
      }
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
    // return (climber.tiltAngle() <= 1) && climber.mainPosition() <= -200000 ;
    return done;
  }
}
