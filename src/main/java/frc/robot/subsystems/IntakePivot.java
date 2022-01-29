// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.FRCLib.Motors.FRCTalonSRX;


public class IntakePivot extends SubsystemBase {
  public FRCTalonSRX pivot;

  public static enum LocationState {
    MOVING, STATIONARY
  }

  public LocationState locationState;

  public static enum ValidAngles {
    DOWN, UP, UNCERTAIN
  }

  public ValidAngles currentAngle;

  /**
   * Creates a new IntakePivot.
   */
  public IntakePivot() {
    pivot = new FRCTalonSRX.FRCTalonSRXBuilder(Constants.IntakeConstants.IntakeMotors.IntakePivot.CAN_ID)
        .withInverted(Constants.IntakeConstants.IntakeMotors.IntakePivot.INVERT)
        .withFeedbackPort(Constants.IntakeConstants.IntakeMotors.IntakePivot.FEEDBACK_PORT)
        .withSensorPhase(Constants.IntakeConstants.IntakeMotors.IntakePivot.SENSOR_PHASE)
        .withTimeout(Constants.IntakeConstants.IntakeMotors.IntakePivot.TIMEOUT)
        .withCurrentLimitEnabled(Constants.IntakeConstants.IntakeMotors.IntakePivot.ENABLE_CURRENT_LIMIT)
        .withCurrentLimit(Constants.IntakeConstants.IntakeMotors.IntakePivot.CURRENT_LIMIT)
        .withOpenLoopRampRate(Constants.IntakeConstants.IntakeMotors.IntakePivot.OPEN_LOOP_RAMP)
        .withNominalOutputForward(Constants.IntakeConstants.IntakeMotors.IntakePivot.NOMINAL_OUTPUT_FORWARD)
        .withNominalOutputReverse(Constants.IntakeConstants.IntakeMotors.IntakePivot.NOMINAL_OUTPUT_REVERSE)
        .withPeakOutputForward(Constants.IntakeConstants.IntakeMotors.IntakePivot.PEAK_OUTPUT_FORWARD)
        .withPeakOutputReverse(Constants.IntakeConstants.IntakeMotors.IntakePivot.PEAK_OUTPUT_REVERSE)
        .withNeutralMode(Constants.IntakeConstants.IntakeMotors.IntakePivot.NEUTRAL_MODE).build();

        addChild("intakePivot", pivot);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
