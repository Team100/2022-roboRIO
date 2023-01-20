// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.drivetrain;


import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
import  edu.wpi.first.wpilibj.SerialPort;

public class autoLevel extends CommandBase {
  private final Drivetrain drivetrain;
  /** Creates a new autoLevel. */
  public autoLevel(Drivetrain we) {
    drivetrain = we;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double driveAmount = 0.02 * drivetrain.getRoll();
    drivetrain.driveWithoutRamp(driveAmount, driveAmount);
    System.out.println("running");
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
