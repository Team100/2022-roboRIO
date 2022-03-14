// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.AnalogInput;
import com.revrobotics.MotorFeedbackSensor;
import com.revrobotics.CANSparkMax.SoftLimitDirection;
import com.revrobotics.SparkMaxAnalogSensor.Mode;

import edu.wpi.first.wpilibj.AnalogEncoder;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.AnalogTrigger;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.FRCLib.Motors.FRCNEO;
import frc.robot.FRCLib.Motors.FRCTalonFX;
import frc.robot.FRCLib.Motors.FRCTalonSRX;

public class Intake extends SubsystemBase {
    private FRCNEO spin, pivot;
    private double pivotSetpoint;

    public AnalogPotentiometer pot;

    //edu.wpi.first.wpilibj.AnalogInput pot = new edu.wpi.first.wpilibj.AnalogInput(3);

    /**
     * Creates a new Intake.
     */
    public Intake() {        
        pot = new AnalogPotentiometer(Constants.IntakeConstants.IntakeSensors.IntakePot.ID,100,-47);

        spin = new FRCNEO.FRCNEOBuilder(Constants.IntakeConstants.IntakeMotors.IntakeSpin.CAN_ID)
            .withInverted(Constants.IntakeConstants.IntakeMotors.IntakeSpin.INVERT)
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
            .withInverted(Constants.IntakeConstants.IntakeMotors.IntakePivot.INVERT)
            .withSensorPhase(Constants.IntakeConstants.IntakeMotors.IntakePivot.SENSOR_PHASE)
            .withTimeout(Constants.IntakeConstants.IntakeMotors.IntakePivot.TIMEOUT)
            .withCurrentLimitEnabled(Constants.IntakeConstants.IntakeMotors.IntakePivot.ENABLE_CURRENT_LIMIT)
            .withCurrentLimit(Constants.IntakeConstants.IntakeMotors.IntakePivot.CURRENT_LIMIT)
            .withOpenLoopRampRate(Constants.IntakeConstants.IntakeMotors.IntakePivot.OPEN_LOOP_RAMP)
            .withPeakOutputForward(Constants.IntakeConstants.IntakeMotors.IntakePivot.PEAK_OUTPUT_FORWARD)
            .withPeakOutputReverse(Constants.IntakeConstants.IntakeMotors.IntakePivot.PEAK_OUTPUT_REVERSE)
            .withNeutralMode(Constants.IntakeConstants.IntakeMotors.IntakePivot.NEUTRAL_MODE)
            .build();

        addChild("intakePivot", pivot);
        addChild("intakeSpin", spin);
    }

    @Override
    public void periodic() {
        //onInit(); // Oh no no no no no
        SmartDashboard.putNumber("Intake Pivot raw analog", getPot());
        SmartDashboard.putNumber("Intake Pivot Motor Output", pivot.motor.get());
    }

    public void onInit() {
    }

    public double getPot(){
        return pot.get();
    }

    public void pivotWithRamp(double pivot) {
        double ramp = ramp(pivot, pivotSetpoint);

        this.pivot.drivePercentOutput(ramp);
        this.pivotSetpoint = ramp;
    }

    private double ramp(double input, double currentSpeed) {
        double dv = input - currentSpeed;
        if (dv > 0) {
            // forwards, speeding up
            if (dv > Constants.DrivetrainConstants.DrivetrainControls.RAMP_LIMIT) {
                return currentSpeed + Constants.DrivetrainConstants.DrivetrainControls.RAMP_LIMIT;
            }
        } else if (dv < 0) {
            // forwards, slowing down
            if (dv < -Constants.DrivetrainConstants.DrivetrainControls.RAMP_LIMIT) {
                return currentSpeed - Constants.DrivetrainConstants.DrivetrainControls.RAMP_LIMIT;
            }
        }
        return input;
    }

    public void runPivot(double percentOutput) {
        pivot.drivePercentOutput(percentOutput);
    }

    public void runSpinner(double percentOutput) {
        spin.drivePercentOutput(percentOutput);
    }
    public void setPivot(double setpoint) {
        pivot.drivePosition(setpoint);
    }
}
    
