// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.climber;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Climber;

public class HomeAlgorithm extends CommandBase {
  /** Creates a new homeAlgorithm. */
  public Climber climber;
  // public DigitalInput limit;
  public Boolean done, zeroed = false;


  public HomeAlgorithm(Climber climber) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.climber = climber;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    done = false;
    zeroed = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(!climber.getHomeSwitch()){
      climber.setTilt(-Constants.ClimberConstants.ClimberSensors.Homing.HomingSpeed);
    }else{
      climber.setTilt(0);
      zeroed = true;
      climber.zeroTilt();

    }

    if(zeroed){
      if(climber.tiltAngle() < 20){
        climber.setTilt(Constants.ClimberConstants.ClimberSensors.Homing.HomingSpeed);
      }else{
        climber.setTilt(0);
        done = true;
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
