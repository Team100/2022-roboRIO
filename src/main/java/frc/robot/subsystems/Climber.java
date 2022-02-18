// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.FRCLib.Motors.FRCTalonFX;

public class Climber extends SubsystemBase {
    private FRCTalonFX tilt, winch;
    /** Creates a new Climber. */
    public Climber() {
        winch = new FRCTalonFX.FRCTalonFXBuilder(Constants.ClimberConstants.ClimberMotors.Winch.CAN_ID)
            .withInverted(Constants.ClimberConstants.ClimberMotors.Winch.INVERT)
            .withFeedbackPort(Constants.ClimberConstants.ClimberMotors.Winch.FEEDBACK_PORT)
            .withSensorPhase(Constants.ClimberConstants.ClimberMotors.Winch.SENSOR_PHASE)
            .withTimeout(Constants.ClimberConstants.ClimberMotors.Winch.TIMEOUT)
            .withCurrentLimitEnabled(Constants.ClimberConstants.ClimberMotors.Winch.ENABLE_CURRENT_LIMIT)
            .withCurrentLimit(Constants.ClimberConstants.ClimberMotors.Winch.CURRENT_LIMIT)
            .withOpenLoopRampRate(Constants.ClimberConstants.ClimberMotors.Winch.OPEN_LOOP_RAMP)
            .withPeakOutputForward(Constants.ClimberConstants.ClimberMotors.Winch.PEAK_OUTPUT_FORWARD)
            .withPeakOutputReverse(Constants.ClimberConstants.ClimberMotors.Winch.PEAK_OUTPUT_REVERSE)
            .withNeutralMode(Constants.ClimberConstants.ClimberMotors.Winch.NEUTRAL_MODE)
            .build();

        tilt = new FRCTalonFX.FRCTalonFXBuilder(Constants.ClimberConstants.ClimberMotors.Tilt.CAN_ID)
            .withInverted(Constants.ClimberConstants.ClimberMotors.Tilt.INVERT)
            .withFeedbackPort(Constants.ClimberConstants.ClimberMotors.Tilt.FEEDBACK_PORT)
            .withSensorPhase(Constants.ClimberConstants.ClimberMotors.Tilt.SENSOR_PHASE)
            .withTimeout(Constants.ClimberConstants.ClimberMotors.Tilt.TIMEOUT)
            .withCurrentLimitEnabled(Constants.ClimberConstants.ClimberMotors.Tilt.ENABLE_CURRENT_LIMIT)
            .withCurrentLimit(Constants.ClimberConstants.ClimberMotors.Tilt.CURRENT_LIMIT)
            .withOpenLoopRampRate(Constants.ClimberConstants.ClimberMotors.Tilt.OPEN_LOOP_RAMP)
            .withPeakOutputForward(Constants.ClimberConstants.ClimberMotors.Tilt.PEAK_OUTPUT_FORWARD)
            .withPeakOutputReverse(Constants.ClimberConstants.ClimberMotors.Tilt.PEAK_OUTPUT_REVERSE)
            .withNeutralMode(Constants.ClimberConstants.ClimberMotors.Tilt.NEUTRAL_MODE)
            .build();

        addChild("winch", winch);
        addChild("tilt", tilt);
    }
    public void setTilt(double percentOutput) {
        tilt.drivePercentOutput(percentOutput);
    }
    

    public void setWinch(double percentOutput) {
        // double limiter = (-joystick.getZ() + 1) / 2;
        // winch.drivePercentOutput(percentOutput*limiter);
        winch.drivePercentOutput(percentOutput);
    }



    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
}