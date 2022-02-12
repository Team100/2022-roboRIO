// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.climb.winch;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.climber.ClimberWinch;
public class WinchStop extends CommandBase {
  /** Creates a new Climb. */
  private final ClimberWinch climberWinch;
  private final Joystick gamepad;

  public WinchStop(ClimberWinch climberWinch, Joystick gamepad) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.climberWinch = climberWinch;
    this.gamepad = gamepad;
    addRequirements(climberWinch);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(Math.abs(gamepad.getRawAxis(3)) < 0.05){
      climberWinch.setWinch(0);
    }else{
      climberWinch.setWinch(-gamepad.getRawAxis(3));
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
