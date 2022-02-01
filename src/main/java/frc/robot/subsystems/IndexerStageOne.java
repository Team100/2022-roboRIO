// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.FRCLib.Motors.FRCNEO;

public class IndexerStageOne extends SubsystemBase {

  public FRCNEO indexerStageOne;
  public DigitalInput sensor;

  /**
   * Creates a new Indexer.
   */
  public IndexerStageOne() {
    sensor = new DigitalInput(Constants.IndexerConstants.IndexerSensors.FrontSensor.ID);
    // Construct Motor Objects
    indexerStageOne = new FRCNEO.FRCNEOBuilder(
        Constants.IndexerConstants.IndexerMotors.IndexerStageOne.CAN_ID)
            .withInverted(Constants.IndexerConstants.IndexerMotors.IndexerStageOne.INVERT)
            .withFeedbackPort(Constants.IndexerConstants.IndexerMotors.IndexerStageOne.FEEDBACK_PORT)
            .withSensorPhase(Constants.IndexerConstants.IndexerMotors.IndexerStageOne.SENSOR_PHASE)
            .withTimeout(Constants.IndexerConstants.IndexerMotors.IndexerStageOne.TIMEOUT)
            .withCurrentLimitEnabled(Constants.IndexerConstants.IndexerMotors.IndexerStageOne.ENABLE_CURRENT_LIMIT)
            .withCurrentLimit(Constants.IndexerConstants.IndexerMotors.IndexerStageOne.CURRENT_LIMIT)
            .withOpenLoopRampRate(Constants.IndexerConstants.IndexerMotors.IndexerStageOne.OPEN_LOOP_RAMP)
            .withPeakOutputForward(Constants.IndexerConstants.IndexerMotors.IndexerStageOne.PEAK_OUTPUT_FORWARD)
            .withPeakOutputReverse(Constants.IndexerConstants.IndexerMotors.IndexerStageOne.PEAK_OUTPUT_REVERSE)
            .build();

            addChild("frontSensor", sensor);
            addChild("indexerStageOne", indexerStageOne);

  }

  /**
   * Update any states
   */
  public void updateState() {

  }

  //public boolean getSensorValue() {
    //return true; //sensor.get();
  //}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    //updateState();
  }
}
