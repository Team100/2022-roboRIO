// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.AnalogInput;
import com.revrobotics.CANSparkMax;
import com.revrobotics.MotorFeedbackSensor;
import com.revrobotics.CANSparkMax.SoftLimitDirection;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxAnalogSensor.Mode;

import edu.wpi.first.wpilibj.AnalogEncoder;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.AnalogTrigger;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.FRCLib.Motors.FRCNEO;
import frc.robot.FRCLib.Motors.FRCTalonFX;
import frc.robot.FRCLib.Motors.FRCTalonSRX;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.*;

public class Intake extends PIDSubsystem {
    private FRCNEO spin, pivot;
    private double pivotSetpoint;
    private AnalogPotentiometer pot;

    /**
     * Creates a new Intake.
     */
    public Intake() {
        super(new PIDController(Constants.IntakeConstants.IntakeMotionParameters.KP, Constants.IntakeConstants.IntakeMotionParameters.KI, Constants.IntakeConstants.IntakeMotionParameters.KD));
        getController().setTolerance(0.1);
        //setSetpoint(Constants.IntakeConstants.PivotConstants.UP_POSITION);

        pot = new AnalogPotentiometer(Constants.IntakeConstants.IntakeSensors.IntakePot.ID,100,-37);

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
            // .withOpenLoopRampRate(Constants.IntakeConstants.IntakeMotors.IntakePivot.OPEN_LOOP_RAMP)
            .withPeakOutputForward(Constants.IntakeConstants.IntakeMotors.IntakePivot.PEAK_OUTPUT_FORWARD)
            .withPeakOutputReverse(Constants.IntakeConstants.IntakeMotors.IntakePivot.PEAK_OUTPUT_REVERSE)
            .withNeutralMode(Constants.IntakeConstants.IntakeMotors.IntakePivot.NEUTRAL_MODE)
            .withMotorType(Constants.IntakeConstants.IntakeMotors.IntakePivot.MOTOR_TYPE)
            .build();
        addChild("intakeSpin", spin);
        addChild("intakePivot", pivot);
    }

    @Override
    public void useOutput(double output, double setpoint) {
        // pivot.motor.setVoltage(output);
        // System.out.println("output is  " + output);
    }

    @Override
    public double getMeasurement() {
        return pot.get();
    }

    public boolean atSetpoint() {
        return getController().atSetpoint();
    }

    public void pivotUp() {
        // super.disable();
        getController().setSetpoint(Constants.IntakeConstants.PivotConstants.UP_POSITION);
        super.enable();
    }

    public void pivotDown() {
        // super.disable();
        getController().setSetpoint(Constants.IntakeConstants.PivotConstants.DOWN_POSITION);
        super.enable();
    }

    @Override
    public void periodic() {
        // getController().calculate(pot.get());
        if (isEnabled()) pivot.motor.setVoltage(getController().calculate(getMeasurement()));
        //onInit(); // Oh no no no no no
        
        SmartDashboard.putNumber("Intake Pivot raw analog", getMeasurement());
        SmartDashboard.putNumber("Intake Pivot Motor Output", pivot.motor.get());
        SmartDashboard.putNumber("Intake Pivot Applied Output", pivot.motor.getAppliedOutput());
        SmartDashboard.putNumber("Errorrororor", getController().getPositionError());
        SmartDashboard.putNumber("setpointtttttttttt", getController().getSetpoint());
        SmartDashboard.putBoolean("enabled", super.isEnabled());
        SmartDashboard.putNumber("current current current", pivot.motor.getOutputCurrent());
    }

    public void onInit() {
    }

    // public double getPot(){
    //     return pot.get();
    // }

    // public void runPivot(double percentOutput) {
    //     pivot.drivePercentOutput(percentOutput);
    //    // pivot.set(percentOutput);
    // }

    public void runSpinner(double percentOutput) {
        if (getSetpoint() == Constants.IntakeConstants.PivotConstants.UP_POSITION && atSetpoint())
        spin.drivePercentOutput(percentOutput);
    }
}
    
