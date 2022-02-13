// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.FRCLib.Motors.FRCTalonSRX;


public class Intake extends SubsystemBase {

    private FRCTalonSRX spin, pivot;

    private ActionState actionState;
    public static enum ActionState {
        INTAKING, NOT_INTAKING
    }
    public ActionState getActionState() { return actionState; }
    public void setActionState(ActionState as) { actionState = as; }

    private LocationState locationState;
    public static enum LocationState {
        MOVING, STATIONARY
    }
    public LocationState getLocationState() { return locationState; }
    public void setLocationState(LocationState ls) { locationState = ls; }

    private ValidAngles currentAngle;
    public static enum ValidAngles {
        DOWN, UP, UNCERTAIN
    }
    public ValidAngles getCurrentAngle() { return currentAngle; }
    public void setActionState(ValidAngles ca) { currentAngle = ca; }

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
            .withPeakOutputReverse(Constants.IntakeConstants.IntakeMotors.IntakeSpin.PEAK_OUTPUT_REVERSE)
            .withNeutralMode(Constants.IntakeConstants.IntakeMotors.IntakeSpin.NEUTRAL_MODE)
            .build();
    

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
            .withNeutralMode(Constants.IntakeConstants.IntakeMotors.IntakePivot.NEUTRAL_MODE)
            .build();

            addChild("intakePivot", pivot);
            addChild("intakeSpin", spin);
        }

        @Override
        public void periodic() {
            // This method will be called once per scheduler run
        }

        public void runPivot(double percentOutput) {
            pivot.drivePercentOutput(percentOutput);
        }

        public void runSpinner(double percentOutput) {
            spin.drivePercentOutput(percentOutput);
        }
    }
    
