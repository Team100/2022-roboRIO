// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.FRCLib.Motors.FRCNEO;

public class IndexerStageTwo extends SubsystemBase {
  public FRCNEO indexerStageTwo;
  public DigitalInput sensor;
  /**
   * Creates a new Indexer.
   */
  public IndexerStageTwo() {
    sensor = new DigitalInput(Constants.IndexerConstants.IndexerSensors.RearSensor.ID);


    indexerStageTwo = new FRCNEO.FRCNEOBuilder(
        Constants.IndexerConstants.IndexerMotors.IndexerStageTwo.CAN_ID)
            .withInverted(Constants.IndexerConstants.IndexerMotors.IndexerStageTwo.INVERT)
            .withFeedbackPort(Constants.IndexerConstants.IndexerMotors.IndexerStageTwo.FEEDBACK_PORT)
            .withSensorPhase(Constants.IndexerConstants.IndexerMotors.IndexerStageTwo.SENSOR_PHASE)
            .withTimeout(Constants.IndexerConstants.IndexerMotors.IndexerStageTwo.TIMEOUT)
            .withCurrentLimitEnabled(Constants.IndexerConstants.IndexerMotors.IndexerStageTwo.ENABLE_CURRENT_LIMIT)
            .withCurrentLimit(Constants.IndexerConstants.IndexerMotors.IndexerStageTwo.CURRENT_LIMIT)
            .withOpenLoopRampRate(Constants.IndexerConstants.IndexerMotors.IndexerStageTwo.OPEN_LOOP_RAMP)
            .withPeakOutputForward(Constants.IndexerConstants.IndexerMotors.IndexerStageTwo.PEAK_OUTPUT_FORWARD)
            .withPeakOutputReverse(Constants.IndexerConstants.IndexerMotors.IndexerStageTwo.PEAK_OUTPUT_REVERSE)
            .build();

            addChild("rearSensor", sensor);
            addChild("indexerStageTwo", indexerStageTwo);
  }

  /**
   * Update any states
   */
  public void updateState() {

  }

  public boolean getSensorValue() {
    return false; //sensor.get();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    updateState();
  }
}