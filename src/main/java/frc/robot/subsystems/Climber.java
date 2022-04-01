// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.FRCLib.Motors.FRCTalonFX;

public class Climber extends SubsystemBase {
    private boolean stationaryLockOverride = false;
    private boolean mainLockOverride = false;

    private double tiltSetpoint, winchSetpoint;


    private FRCTalonFX tilt, winch;
    private AnalogPotentiometer pot;

    private DigitalInput leftStationaryHook, rightStationaryHook, leftMainHook, rightMainHook;
    /** Creates a new Climber. */
    public Climber() {
        leftStationaryHook = new DigitalInput(Constants.ClimberConstants.ClimberSensors.StationaryHooks.LeftSensor.ID);
        rightStationaryHook = new DigitalInput(Constants.ClimberConstants.ClimberSensors.StationaryHooks.RightSensor.ID);
        leftMainHook = new DigitalInput(Constants.ClimberConstants.ClimberSensors.MainHooks.LeftSensor.ID);
        rightMainHook = new DigitalInput(Constants.ClimberConstants.ClimberSensors.MainHooks.RightSensor.ID);
        // homeSwitch = new DigitalInput(Constants.ClimberConstants.ClimberSensors.Homing.ID);

        pot = new AnalogPotentiometer(Constants.ClimberConstants.ClimberSensors.Tilt.ID,Constants.ClimberConstants.ClimberSensors.Tilt.POT_ADJUSTMENT_FACTOR,Constants.ClimberConstants.ClimberSensors.Tilt.POT_OFFSET);

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
        .withNeutralMode(Constants.ClimberConstants.ClimberMotors.Tilt.NEUTRAL_MODE).build();

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
        .withNeutralMode(Constants.ClimberConstants.ClimberMotors.Winch.NEUTRAL_MODE).build();

        addChild("tilt", tilt);
        addChild("winch", winch);
    }

    public void setTilt(double percentOutput) {
        if (this.tiltSetpoint != percentOutput) {
            tilt.drivePercentOutput(percentOutput);
            this.tiltSetpoint = percentOutput;
        }
    }

    public void setWinch(double percentOutput) {
        if (this.winchSetpoint != percentOutput) {
            winch.drivePercentOutput(percentOutput);
            this.winchSetpoint = percentOutput;
        }
    }

    public boolean stationaryLocked(){
        return (!rightStationaryHook.get()&&!leftStationaryHook.get())||stationaryLockOverride;
        //return SmartDashboard.getBoolean("Stationaries Locked?", false);
    }

    // public boolean getHomeSwitch(){
    //     return !homeSwitch.get();
    //     //return SmartDashboard.getBoolean("Stationaries Locked?", false);
    // }

    public boolean mainLocked(){
        return (!leftMainHook.get()||!rightMainHook.get())||mainLockOverride;
        //return SmartDashboard.getBoolean("Main Hooks Locked?", false);
    }

    public void setMainLockedOverride(boolean set){
        mainLockOverride = set;
    }

    public void setStationaryLockedOverride(boolean set){
        stationaryLockOverride = set;
    }

    public boolean getMainSensorLeft(){
        return !leftMainHook.get();
    }
    
    // public boolean stationaryLeft(){
    //     // SmartDashboard.putBoolean("6", leftStationaryHook1.get());
    //     // SmartDashboard.putBoolean("9", leftStationaryHook2.get());

    //     return (!leftStationaryHook1.get()&&leftStationaryHook2.get());
    // }

    public boolean getMainSensorRight(){
        return !rightMainHook.get();
    }

    public double mainPosition(){
        return winch.getSelectedSensorPosition();
    }

    public void onInit(){
        // zeroWinch();
        // tilt.motor.setSelectedSensorPosition(0);
    }

    public void zeroWinch(){
        winch.motor.setSelectedSensorPosition(0);
    }

    public void zeroTilt(){
        tilt.motor.setSelectedSensorPosition(0);
    }

    public double legacyTiltAngle(){
        return tilt.getSelectedSensorPosition()/2048/2000*360;//previously used one
    }

    public double tiltAngle(){
        // return 90-((tilt.getSelectedSensorPosition()/2048)+45);
        // return 45-(tilt.getSelectedSensorPosition()/2048/2000*360);
        //return tilt.getSelectedSensorPosition()/2048/2000*360;//previously used one
        //return pot.get();
        // double p = pot.get();
        // return p;//((p - 0.082) * -345) +256 - 9.3;
        double p = pot.get();
        return Math.round(1000*p)/1000;
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("main hooks position", mainPosition());
        SmartDashboard.putNumber("TiltAngle", tiltAngle());
        // SmartDashboard.putNumber("Legacy TiltAngle", legacyTiltAngle());

        //SmartDashboard.putNumber("Old value testing", ((tiltAngle() - 0.082) * -340) +256 - 10.8);

        SmartDashboard.putBoolean("mainLocked", mainLocked());
        SmartDashboard.putBoolean("stationaryLocked", stationaryLocked());

        SmartDashboard.putBoolean("sensorLeft", getMainSensorLeft());
        SmartDashboard.putBoolean("sensorRight", getMainSensorRight());
        
        SmartDashboard.putBoolean("leftSationary", !leftStationaryHook.get());
        SmartDashboard.putBoolean("rightSatioanrj", !rightStationaryHook.get());

        // SmartDashboard.putBoolean("allingment switch", getHomeSwitch());

        // SmartDashboard.putNumber("Winch", winch.getSelectedSensorPosition());
    }
}
