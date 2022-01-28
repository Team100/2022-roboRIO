// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.FRCLib.Motors.FRCTalonSRX;


public class Intake extends SubsystemBase {

    public FRCTalonSRX spin;
  
    public static enum ActionState {
      INTAKING, NOT_INTAKING
    }
  
    public ActionState actionState;
  
    /**
     * Creates a new Intake.
     */
    public Intake() {
  
      spin = new FRCTalonSRX.FRCTalonSRXBuilder(Constants.IntakeConstants.IntakeMotors.IntakeSpin.CAN_ID)
          .withInverted(Constants.IntakeConstants.IntakeMotors.IntakeSpin.INVERT)
          .withFeedbackPort(Constants.IntakeConstants.IntakeMotors.IntakeSpin.FEEDBACK_PORT)
          .withSensorPhase(Constants.IntakeConstants.IntakeMotors.IntakeSpin.SENSOR_PHASE)
          .withTimeout(Constants.IntakeConstants.IntakeMotors.IntakeSpin.TIMEOUT)
          .withCurrentLimitEnabled(Constants.IntakeConstants.IntakeMotors.IntakeSpin.ENABLE_CURRENT_LIMIT)
          .withCurrentLimit(Constants.IntakeConstants.IntakeMotors.IntakeSpin.CURRENT_LIMIT)
          .withOpenLoopRampRate(Constants.IntakeConstants.IntakeMotors.IntakeSpin.OPEN_LOOP_RAMP)
          .withNominalOutputForward(Constants.IntakeConstants.IntakeMotors.IntakeSpin.NOMINAL_OUTPUT_FORWARD)
          .withNominalOutputReverse(Constants.IntakeConstants.IntakeMotors.IntakeSpin.NOMINAL_OUTPUT_REVERSE)
          .withPeakOutputForward(Constants.IntakeConstants.IntakeMotors.IntakeSpin.PEAK_OUTPUT_FORWARD)
          .withPeakOutputReverse(Constants.IntakeConstants.IntakeMotors.IntakeSpin.PEAK_OUTPUT_REVERSE).build();
  
          addChild("intakeSpin", spin);
    }
  
    /**
     * Update any states
     */
    public void updateState() {
  
    }
  
    @Override
    public void periodic() {
      // This method will be called once per scheduler run
      updateState();
    }
  }
  
