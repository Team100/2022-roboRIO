// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
import frc.robot.Constants.DrivetrainConstants;
// import frc.robot.commands.drivetrain.Drive;


public class Turn extends CommandBase {
  private final Drivetrain drivetrain;
  private int degrees;
  private Boolean done;

  /** Creates a new Turn. */
  public Turn(Drivetrain drivetrain, int degrees) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.drivetrain = drivetrain;
    this.degrees = degrees;
    addRequirements(drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    drivetrain.zeroCurrentPosition();
    drivetrain.setBrakeMode(true);
    done = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    System.out.println("encoders............... " + -((drivetrain.getRight()-drivetrain.getLeft())/2));
        if(degrees>0){
          if(-((drivetrain.getRight()-drivetrain.getLeft())/2)<(degrees*DrivetrainConstants.Autonomous.Turning.TURN_ADJUSMENT)){
            drivetrain.driveWithoutRamp(DrivetrainConstants.Autonomous.Turning.TURN_SPEED,-DrivetrainConstants.Autonomous.Turning.TURN_SPEED);
          }else if(-((drivetrain.getRight()-drivetrain.getLeft())/2)>=(degrees*DrivetrainConstants.Autonomous.Turning.TURN_ADJUSMENT)){
            drivetrain.driveWithoutRamp(0,0);
            done = true;
          }
        }else if(degrees<0){
          if(((drivetrain.getRight()-drivetrain.getLeft())/2)<(-degrees*DrivetrainConstants.Autonomous.Turning.TURN_ADJUSMENT)){
            drivetrain.driveWithoutRamp(DrivetrainConstants.Autonomous.Turning.TURN_SPEED,-DrivetrainConstants.Autonomous.Turning.TURN_SPEED);
          }else if(((drivetrain.getRight()-drivetrain.getLeft())/2)>=(-degrees*DrivetrainConstants.Autonomous.Turning.TURN_ADJUSMENT)){
            drivetrain.driveWithoutRamp(0,0);
            done = true;
          }
        }else{
          done = true;
        }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drivetrain.driveWithoutRamp(0, 0);
    // drivetrain.setBrakeMode(status);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return done;
  }
}
