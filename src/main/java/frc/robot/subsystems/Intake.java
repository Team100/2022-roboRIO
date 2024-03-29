// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.ProfiledPIDSubsystem;
import frc.robot.Constants;
import frc.robot.FRCLib.Motors.FRCNEO;
import frc.robot.FRCLib.Motors.FRCTalonFX;
import frc.robot.FRCLib.Motors.FRCTalonSRX;

import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.math.trajectory.TrapezoidProfile.State;

public class Intake extends ProfiledPIDSubsystem {
    private FRCNEO spin;
    private FRCTalonFX pivot;
    private AnalogPotentiometer pot;
    private int cycleCount;

    /**
     * Creates a new Intake.
     */
    public Intake() {
        super(new ProfiledPIDController(
            Constants.IntakeConstants.IntakeMotionParameters.KP,
            Constants.IntakeConstants.IntakeMotionParameters.KI,
            Constants.IntakeConstants.IntakeMotionParameters.KD,
            new TrapezoidProfile.Constraints(
                Constants.IntakeConstants.IntakeMotionParameters.TRAPAZOID_PROFILE_MAX_VEL,
                Constants.IntakeConstants.IntakeMotionParameters.TRAPAZOID_PROFILE_MAX_ACL)),
            Constants.IntakeConstants.PivotConstants.UP_POSITION);
        getController().setTolerance(Constants.IntakeConstants.IntakeMotionParameters.PID_TOLERANCE);

        pot = new AnalogPotentiometer(Constants.IntakeConstants.IntakeSensors.IntakePot.ID,Constants.IntakeConstants.IntakeSensors.IntakePot.POT_ADJUSTMENT_FACTOR,Constants.IntakeConstants.IntakeSensors.IntakePot.POT_OFFSET);

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


        pivot = new FRCTalonFX.FRCTalonFXBuilder(Constants.IntakeConstants.IntakeMotors.IntakePivot.CAN_ID)
            .withInverted(Constants.IntakeConstants.IntakeMotors.IntakePivot.INVERT)
            .withSensorPhase(Constants.IntakeConstants.IntakeMotors.IntakePivot.SENSOR_PHASE)
            .withTimeout(Constants.IntakeConstants.IntakeMotors.IntakePivot.TIMEOUT)
            .withCurrentLimitEnabled(Constants.IntakeConstants.IntakeMotors.IntakePivot.ENABLE_CURRENT_LIMIT)
            .withCurrentLimit(Constants.IntakeConstants.IntakeMotors.IntakePivot.CURRENT_LIMIT)
            .withPeakOutputForward(Constants.IntakeConstants.IntakeMotors.IntakePivot.PEAK_OUTPUT_FORWARD)
            .withPeakOutputReverse(Constants.IntakeConstants.IntakeMotors.IntakePivot.PEAK_OUTPUT_REVERSE)
            .withNeutralMode(Constants.IntakeConstants.IntakeMotors.IntakePivot.NEUTRAL_MODE)
            .build();
        addChild("intakeSpin", spin);
        addChild("intakePivot", pivot);

        setGoal(Constants.IntakeConstants.PivotConstants.UP_POSITION);
    }

    @Override
    public void useOutput(double output, State setpoint) {
        if(pot.get() < Constants.IntakeConstants.PivotConstants.UP_SETPOINT){
            pivot.motor.setVoltage(output);
        } else {
            cycleCount++;
            if (cycleCount >= Constants.IntakeConstants.PivotConstants.CYCLE_COUNT) {
                pivot.motor.setVoltage(0);
                cycleCount = 0;
            }
        }
    }

    @Override
    public void disable() {
        cycleCount = 0;
        super.disable();
    }

    @Override
    public double getMeasurement() {
        double p = pot.get();
        return Math.round(1000*p)/1000;
    }

    public boolean atSetpoint() {
        return getController().atSetpoint();
    }

    public void pivotUp() {
        getController().setGoal(Constants.IntakeConstants.PivotConstants.UP_POSITION);
        super.enable();
    }

    public void pivotDown() {
        getController().setGoal(Constants.IntakeConstants.PivotConstants.DOWN_POSITION);
        super.enable();
    }

    @Override
    public void periodic() {
        if (m_enabled) {
            useOutput(m_controller.calculate(getMeasurement(), getController().getGoal()), m_controller.getSetpoint());
        }
        // if (isEnabled()) pivot.motor.setVoltage(getController().calculate(getMeasurement()));
        //onInit(); // Oh no no no no no
        
        // SmartDashboard.putNumber("Intake Cycle Count", cycleCount);
        SmartDashboard.putNumber("Intake Pivot Angle", getMeasurement());
        // SmartDashboard.putNumber("Intake Pivot Motor Output", pivot.motor.get());
        // SmartDashboard.putNumber("Intake Pivot Applied Output", pivot.motor.getMotorOutputPercent());
        // SmartDashboard.putNumber("Errorrororor", getController().getPositionError());
        // SmartDashboard.putNumber("setpointtttttttttt", getController().getGoal().position);
        // SmartDashboard.putBoolean("At Setpoint", atSetpoint());
        // SmartDashboard.putBoolean("enabled", super.isEnabled());
        // SmartDashboard.putNumber("current current current", pivot.motor.getStatorCurrent());
        // SmartDashboard.putNumber("wattage wattege wattege", (pivot.motor.getStatorCurrent()*pivot.motor.getMotorOutputVoltage()));
    }

    public void onInit() {
    }

    public void runPivot(double percentOutput) {
        pivot.drivePercentOutput(percentOutput);
    }

    public void runSpinner(double percentOutput) {
        spin.drivePercentOutput(percentOutput);
    }
}
    
