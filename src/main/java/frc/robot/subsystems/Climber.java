// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.FRCLib.Motors.FRCNEO;

public class Climber extends SubsystemBase {
  private FRCNEO leftWinch, rightWinch, elbow;
  /** Creates a new Climber. */
  public Climber() {
    leftWinch = new FRCNEO.FRCNEOBuilder(Constants.ClimberConstants.ClimberMotors.LeftWinch.CAN_ID)
    .withInverted(Constants.ClimberConstants.ClimberMotors.LeftWinch.INVERT)
    .withFeedbackPort(Constants.ClimberConstants.ClimberMotors.LeftWinch.FEEDBACK_PORT)
    .withSensorPhase(Constants.ClimberConstants.ClimberMotors.LeftWinch.SENSOR_PHASE)
    .withTimeout(Constants.ClimberConstants.ClimberMotors.LeftWinch.TIMEOUT)
    .withCurrentLimitEnabled(Constants.ClimberConstants.ClimberMotors.LeftWinch.ENABLE_CURRENT_LIMIT)
    .withCurrentLimit(Constants.ClimberConstants.ClimberMotors.LeftWinch.CURRENT_LIMIT)
    .withOpenLoopRampRate(Constants.ClimberConstants.ClimberMotors.LeftWinch.OPEN_LOOP_RAMP)
    .withPeakOutputForward(Constants.ClimberConstants.ClimberMotors.LeftWinch.PEAK_OUTPUT_FORWARD)
    .withPeakOutputReverse(Constants.ClimberConstants.ClimberMotors.LeftWinch.PEAK_OUTPUT_REVERSE).build();

rightWinch = new FRCNEO.FRCNEOBuilder(Constants.ClimberConstants.ClimberMotors.RightWinch.CAN_ID)
    .withInverted(Constants.ClimberConstants.ClimberMotors.RightWinch.INVERT)
    .withFeedbackPort(Constants.ClimberConstants.ClimberMotors.RightWinch.FEEDBACK_PORT)
    .withSensorPhase(Constants.ClimberConstants.ClimberMotors.RightWinch.SENSOR_PHASE)
    .withTimeout(Constants.ClimberConstants.ClimberMotors.RightWinch.TIMEOUT)
    .withCurrentLimitEnabled(Constants.ClimberConstants.ClimberMotors.RightWinch.ENABLE_CURRENT_LIMIT)
    .withCurrentLimit(Constants.ClimberConstants.ClimberMotors.RightWinch.CURRENT_LIMIT)
    .withOpenLoopRampRate(Constants.ClimberConstants.ClimberMotors.RightWinch.OPEN_LOOP_RAMP)
    .withPeakOutputForward(Constants.ClimberConstants.ClimberMotors.RightWinch.PEAK_OUTPUT_FORWARD)
    .withPeakOutputReverse(Constants.ClimberConstants.ClimberMotors.RightWinch.PEAK_OUTPUT_REVERSE)
    .withMaster(leftWinch).build();

    elbow = new FRCNEO.FRCNEOBuilder(Constants.ClimberConstants.ClimberMotors.Elbow.CAN_ID)
    .withInverted(Constants.ClimberConstants.ClimberMotors.Elbow.INVERT)
    .withFeedbackPort(Constants.ClimberConstants.ClimberMotors.Elbow.FEEDBACK_PORT)
    .withSensorPhase(Constants.ClimberConstants.ClimberMotors.Elbow.SENSOR_PHASE)
    .withTimeout(Constants.ClimberConstants.ClimberMotors.Elbow.TIMEOUT)
    .withCurrentLimitEnabled(Constants.ClimberConstants.ClimberMotors.Elbow.ENABLE_CURRENT_LIMIT)
    .withCurrentLimit(Constants.ClimberConstants.ClimberMotors.Elbow.CURRENT_LIMIT)
    .withOpenLoopRampRate(Constants.ClimberConstants.ClimberMotors.Elbow.OPEN_LOOP_RAMP)
    .withPeakOutputForward(Constants.ClimberConstants.ClimberMotors.Elbow.PEAK_OUTPUT_FORWARD)
    .withPeakOutputReverse(Constants.ClimberConstants.ClimberMotors.Elbow.PEAK_OUTPUT_REVERSE).build();
  }

  public void setWinch(double percentOutput) {
    leftWinch.drivePercentOutput(percentOutput);
  }
  public void setElbow(double percentOutput) {
    elbow.drivePercentOutput(percentOutput);
  }
  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
