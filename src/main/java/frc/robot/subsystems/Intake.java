// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.FRCLib.Motors.FRCNEO;

public class Intake extends SubsystemBase {

    private FRCNEO spin, pivot;


    /**
     * Creates a new Intake.
     */
    public Intake() {
    
        spin = new FRCNEO.FRCNEOBuilder(Constants.IntakeConstants.IntakeMotors.IntakeSpin.CAN_ID)
            .withFeedbackPort(Constants.IntakeConstants.IntakeMotors.IntakeSpin.FEEDBACK_PORT)
            .withSensorPhase(Constants.IntakeConstants.IntakeMotors.IntakeSpin.SENSOR_PHASE)
            .withTimeout(Constants.IntakeConstants.IntakeMotors.IntakeSpin.TIMEOUT)
            .withCurrentLimitEnabled(Constants.IntakeConstants.IntakeMotors.IntakeSpin.ENABLE_CURRENT_LIMIT)
            .withCurrentLimit(Constants.IntakeConstants.IntakeMotors.IntakeSpin.CURRENT_LIMIT)
            .withOpenLoopRampRate(Constants.IntakeConstants.IntakeMotors.IntakeSpin.OPEN_LOOP_RAMP)
            .withPeakOutputForward(Constants.IntakeConstants.IntakeMotors.IntakeSpin.PEAK_OUTPUT_FORWARD)
            .withPeakOutputReverse(Constants.IntakeConstants.IntakeMotors.IntakeSpin.PEAK_OUTPUT_REVERSE)
            .withNeutralMode(Constants.IntakeConstants.IntakeMotors.IntakeSpin.NEUTRAL_MODE)
            .build();
    

        pivot = new FRCNEO.FRCNEOBuilder(Constants.IntakeConstants.IntakeMotors.IntakePivot.CAN_ID)
            .withFeedbackPort(Constants.IntakeConstants.IntakeMotors.IntakePivot.FEEDBACK_PORT)
            .withSensorPhase(Constants.IntakeConstants.IntakeMotors.IntakePivot.SENSOR_PHASE)
            .withTimeout(Constants.IntakeConstants.IntakeMotors.IntakePivot.TIMEOUT)
            .withCurrentLimitEnabled(Constants.IntakeConstants.IntakeMotors.IntakePivot.ENABLE_CURRENT_LIMIT)
            .withCurrentLimit(Constants.IntakeConstants.IntakeMotors.IntakePivot.CURRENT_LIMIT)
            .withOpenLoopRampRate(Constants.IntakeConstants.IntakeMotors.IntakePivot.OPEN_LOOP_RAMP)
            .withPeakOutputForward(Constants.IntakeConstants.IntakeMotors.IntakePivot.PEAK_OUTPUT_FORWARD)
            .withPeakOutputReverse(Constants.IntakeConstants.IntakeMotors.IntakePivot.PEAK_OUTPUT_REVERSE)
            .withNeutralMode(Constants.IntakeConstants.IntakeMotors.IntakePivot.NEUTRAL_MODE)
            .withAnalogSensorMode(Constants.IntakeConstants.IntakeMotors.IntakePivot.ANALOG_MODE)
            .build();

            addChild("intakePivot", pivot);
            addChild("intakeSpin", spin);
        }

        @Override
        public void periodic() {
            // This method will be called once per scheduler run
            SmartDashboard.putNumber("Intake Pivot Encoder Value", getCurrentPosition());
        }

        public double getCurrentPosition(){
            return pivot.getAnalogSensorPosition();
        }

        public void runPivot(double percentOutput) {
            pivot.drivePercentOutput(percentOutput);
        }

        public void runSpinner(double percentOutput) {
            spin.drivePercentOutput(percentOutput);
        }
    }
    
