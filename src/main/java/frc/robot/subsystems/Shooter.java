// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.FRCLib.Motors.FRCNEO;


public class Shooter extends SubsystemBase {
  /** Creates a new Shooter. */
  public FRCNEO shootleft;
  public FRCNEO shootright;
  public Shooter() {
    shootleft = new FRCNEO.FRCNEOBuilder(Constants.ShooterConstants.ShooterMotors.ShooterLeft.CAN_ID)
          .withInverted(Constants.ShooterConstants.ShooterMotors.ShooterLeft.INVERT)
          .withFeedbackPort(Constants.ShooterConstants.ShooterMotors.ShooterLeft.FEEDBACK_PORT)
          .withSensorPhase(Constants.ShooterConstants.ShooterMotors.ShooterLeft.SENSOR_PHASE)
          .withTimeout(Constants.ShooterConstants.ShooterMotors.ShooterLeft.TIMEOUT)
          .withCurrentLimitEnabled(Constants.ShooterConstants.ShooterMotors.ShooterLeft.ENABLE_CURRENT_LIMIT)
          .withCurrentLimit(Constants.ShooterConstants.ShooterMotors.ShooterLeft.CURRENT_LIMIT)
          .withOpenLoopRampRate(Constants.ShooterConstants.ShooterMotors.ShooterLeft.OPEN_LOOP_RAMP)
          .withPeakOutputForward(Constants.ShooterConstants.ShooterMotors.ShooterLeft.PEAK_OUTPUT_FORWARD)
          .withPeakOutputReverse(Constants.ShooterConstants.ShooterMotors.ShooterLeft.PEAK_OUTPUT_REVERSE).build();

    shootright = new FRCNEO.FRCNEOBuilder(Constants.ShooterConstants.ShooterMotors.ShooterRight.CAN_ID)
          .withInverted(Constants.ShooterConstants.ShooterMotors.ShooterRight.INVERT)
          .withFeedbackPort(Constants.ShooterConstants.ShooterMotors.ShooterRight.FEEDBACK_PORT)
          .withSensorPhase(Constants.ShooterConstants.ShooterMotors.ShooterRight.SENSOR_PHASE)
          .withTimeout(Constants.ShooterConstants.ShooterMotors.ShooterRight.TIMEOUT)
          .withCurrentLimitEnabled(Constants.ShooterConstants.ShooterMotors.ShooterRight.ENABLE_CURRENT_LIMIT)
          .withCurrentLimit(Constants.ShooterConstants.ShooterMotors.ShooterRight.CURRENT_LIMIT)
          .withOpenLoopRampRate(Constants.ShooterConstants.ShooterMotors.ShooterRight.OPEN_LOOP_RAMP)
          .withPeakOutputForward(Constants.ShooterConstants.ShooterMotors.ShooterRight.PEAK_OUTPUT_FORWARD)
          .withPeakOutputReverse(Constants.ShooterConstants.ShooterMotors.ShooterRight.PEAK_OUTPUT_REVERSE).build();
          
          addChild("ShooterLeft", shootleft); 
          addChild("ShooterRight", shootleft);  
  }
  public void set(double left, double right) {
    this.shootleft.drivePercentOutput(left);
    this.shootright.drivePercentOutput(right);
}


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
