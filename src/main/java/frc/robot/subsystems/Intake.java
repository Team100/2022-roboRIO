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
import edu.wpi.first.math.MathUtil;
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
            Constants.IntakeConstants.PivotConstants.USE_PIVOT_MOTOR_ENCODER
                ? Constants.IntakeConstants.IntakeMotionParameters.Falcon.KP
                : Constants.IntakeConstants.IntakeMotionParameters.Analog.KP,
            Constants.IntakeConstants.PivotConstants.USE_PIVOT_MOTOR_ENCODER
                ? Constants.IntakeConstants.IntakeMotionParameters.Falcon.KI
                : Constants.IntakeConstants.IntakeMotionParameters.Analog.KI,
            Constants.IntakeConstants.PivotConstants.USE_PIVOT_MOTOR_ENCODER
                ? Constants.IntakeConstants.IntakeMotionParameters.Falcon.KD
                : Constants.IntakeConstants.IntakeMotionParameters.Analog.KD,
            new TrapezoidProfile.Constraints(
                Constants.IntakeConstants.PivotConstants.USE_PIVOT_MOTOR_ENCODER
                    ? Constants.IntakeConstants.IntakeMotionParameters.Falcon.TRAPAZOID_PROFILE_MAX_ACL
                    : Constants.IntakeConstants.IntakeMotionParameters.Analog.TRAPAZOID_PROFILE_MAX_VEL,
                    Constants.IntakeConstants.PivotConstants.USE_PIVOT_MOTOR_ENCODER
                    ? Constants.IntakeConstants.IntakeMotionParameters.Falcon.TRAPAZOID_PROFILE_MAX_ACL
                    : Constants.IntakeConstants.IntakeMotionParameters.Analog.TRAPAZOID_PROFILE_MAX_ACL)),
            Constants.IntakeConstants.PivotConstants.USE_PIVOT_MOTOR_ENCODER
                ? Constants.IntakeConstants.PivotConstants.Falcon.UP_POSITION
                : Constants.IntakeConstants.PivotConstants.Analog.UP_POSITION);
        getController().setTolerance(Constants.IntakeConstants.PivotConstants.USE_PIVOT_MOTOR_ENCODER
                                        ? Constants.IntakeConstants.IntakeMotionParameters.Falcon.PID_TOLERANCE
                                        : Constants.IntakeConstants.IntakeMotionParameters.Analog.PID_TOLERANCE);

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
            .withNominalOutputForward(0.01)
            .withPeakOutputForward(Constants.IntakeConstants.IntakeMotors.IntakePivot.PEAK_OUTPUT_FORWARD)
            .withPeakOutputReverse(Constants.IntakeConstants.IntakeMotors.IntakePivot.PEAK_OUTPUT_REVERSE)
            .withNeutralMode(Constants.IntakeConstants.IntakeMotors.IntakePivot.NEUTRAL_MODE)
            .build();

        pivot.setSensorPosition(0);

        addChild("intakeSpin", spin);
        addChild("intakePivot", pivot);

        SmartDashboard.setDefaultBoolean("ZERO PIVOT", false);

        setGoal(Constants.IntakeConstants.PivotConstants.USE_PIVOT_MOTOR_ENCODER
                    ? Constants.IntakeConstants.PivotConstants.Falcon.UP_POSITION
                    : Constants.IntakeConstants.PivotConstants.Analog.UP_POSITION);
    }

    @Override
    public void useOutput(double output, State setpoint) {
        SmartDashboard.putNumber("OUTPUT", MathUtil.clamp(output, -2.5, 2.5));
        if (Constants.IntakeConstants.PivotConstants.USE_PIVOT_MOTOR_ENCODER
                ? pivot.getSelectedSensorPosition() < Constants.IntakeConstants.PivotConstants.Falcon.UP_SETPOINT
                : pot.get() < Constants.IntakeConstants.PivotConstants.Analog.UP_SETPOINT
        ){
            pivot.drivePercentOutput(MathUtil.clamp(output, -2.5, 2.5)/12.0);
        } else {
            cycleCount++;
            if (cycleCount >= Constants.IntakeConstants.PivotConstants.CYCLE_COUNT) {
                pivot.drivePercentOutput(0);
                cycleCount = 0;
            }
        }
    }

    private double feedForward(double currentPos) {
        double rad = currentPos / Constants.IntakeConstants.PivotConstants.Falcon.UP_POSITION * Math.PI / 2;
        return Math.cos(rad) * Constants.IntakeConstants.PivotConstants.GRAVITY_VOLTAGE + 1;
    }

    @Override
    public void disable() {
        cycleCount = 0;
        super.disable();
    }

    @Override
    public double getMeasurement() {
        double p = Constants.IntakeConstants.PivotConstants.USE_PIVOT_MOTOR_ENCODER ? pivot.getSelectedSensorPosition() : pot.get();
        return Math.round(1000*p)/1000;
    }

    public boolean atSetpoint() {
        return getController().atSetpoint();
    }

    public void pivotUp() {
        getController().setGoal(Constants.IntakeConstants.PivotConstants.USE_PIVOT_MOTOR_ENCODER
                                    ? Constants.IntakeConstants.PivotConstants.Falcon.UP_POSITION
                                    : Constants.IntakeConstants.PivotConstants.Analog.UP_POSITION);
        super.enable();
    }

    public void pivotDown() {
        getController().setGoal(Constants.IntakeConstants.PivotConstants.USE_PIVOT_MOTOR_ENCODER
                                    ? Constants.IntakeConstants.PivotConstants.Falcon.DOWN_POSITION
                                    : Constants.IntakeConstants.PivotConstants.Analog.DOWN_POSITION);
        super.enable();
    }

    @Override
    public void periodic() {
        if (m_enabled) {
            // weird fix since the PID controller was not correctly sending signals to the motor
            useOutput(m_controller.calculate(getMeasurement(), getController().getGoal()) + feedForward(getMeasurement()), m_controller.getSetpoint());
        }
        // if (isEnabled()) pivot.motor.setVoltage(getController().calculate(getMeasurement()));
        //onInit(); // Oh no no no no no
        
        if (SmartDashboard.getBoolean("ZERO PIVOT", false)) {
            pivot.motor.setSelectedSensorPosition(0);
            SmartDashboard.putBoolean("ZERO PIVOT", false);
        }

        // SmartDashboard.putNumber("Intake Cycle Count", cycleCount);
        SmartDashboard.putNumber("Intake Pivot Angle", getMeasurement());
        // SmartDashboard.putNumber("Intake Pivot Motor Output", pivot.motor.get());
        // SmartDashboard.putNumber("Intake Pivot Applied Output", pivot.motor.getMotorOutputPercent());
        SmartDashboard.putNumber("Errorrororor", getController().getPositionError());
        SmartDashboard.putNumber("setpointtttttttttt", getController().getSetpoint().position );
        SmartDashboard.putNumber("MEAASSSURRMENNTTTT", getMeasurement());

        //SmartDashboard.putBoolean("MOVED", 30000);

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