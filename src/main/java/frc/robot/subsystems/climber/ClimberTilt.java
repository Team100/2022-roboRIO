// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.climber;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.FRCLib.Motors.FRCNEO;

public class ClimberTilt extends SubsystemBase {
  private FRCNEO tilt;
  /** Creates a new Climber. */
  public ClimberTilt() {
    tilt = new FRCNEO.FRCNEOBuilder(Constants.ClimberConstants.ClimberMotors.Tilt.CAN_ID)
    .withInverted(Constants.ClimberConstants.ClimberMotors.Tilt.INVERT)
    .withFeedbackPort(Constants.ClimberConstants.ClimberMotors.Tilt.FEEDBACK_PORT)
    .withSensorPhase(Constants.ClimberConstants.ClimberMotors.Tilt.SENSOR_PHASE)
    .withTimeout(Constants.ClimberConstants.ClimberMotors.Tilt.TIMEOUT)
    .withCurrentLimitEnabled(Constants.ClimberConstants.ClimberMotors.Tilt.ENABLE_CURRENT_LIMIT)
    .withCurrentLimit(Constants.ClimberConstants.ClimberMotors.Tilt.CURRENT_LIMIT)
    .withOpenLoopRampRate(Constants.ClimberConstants.ClimberMotors.Tilt.OPEN_LOOP_RAMP)
    .withPeakOutputForward(Constants.ClimberConstants.ClimberMotors.Tilt.PEAK_OUTPUT_FORWARD)
    .withPeakOutputReverse(Constants.ClimberConstants.ClimberMotors.Tilt.PEAK_OUTPUT_REVERSE).build();
  }
  public void setTilt(double percentOutput) {
    tilt.drivePercentOutput(percentOutput);
  }
  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
