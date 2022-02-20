// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants.*;
import frc.robot.FRCLib.Motors.FRCNEO;

public class Shooter extends SubsystemBase {
    private FRCNEO portMotor, starboardMotor;

    private boolean atSpeed = false;
    public boolean isAtSpeed() { return atSpeed; }

    /** Creates a new Shooter. */
    public Shooter() {
        portMotor = new FRCNEO.FRCNEOBuilder(ShooterMotors.PortShooter.CAN_ID)
            .withInverted(ShooterMotors.PortShooter.INVERT)
            .withFeedbackPort(ShooterMotors.PortShooter.FEEDBACK_PORT)
            .withSensorPhase(ShooterMotors.PortShooter.SENSOR_PHASE)
            .withTimeout(ShooterMotors.PortShooter.TIMEOUT)
            .withCurrentLimitEnabled(ShooterMotors.PortShooter.ENABLE_CURRENT_LIMIT)
            .withCurrentLimit(ShooterMotors.PortShooter.CURRENT_LIMIT)
            .withOpenLoopRampRate(ShooterMotors.PortShooter.OPEN_LOOP_RAMP)
            .withPeakOutputForward(ShooterMotors.PortShooter.PEAK_OUTPUT_FORWARD)
            .withPeakOutputReverse(ShooterMotors.PortShooter.PEAK_OUTPUT_REVERSE)
            .withNeutralMode(ShooterMotors.PortShooter.NEUTRAL_MODE)
            .build();

        starboardMotor = new FRCNEO.FRCNEOBuilder(ShooterMotors.StarboardShooter.CAN_ID)
            .withInverted(ShooterMotors.StarboardShooter.INVERT)
            .withFeedbackPort(ShooterMotors.StarboardShooter.FEEDBACK_PORT)
            .withSensorPhase(ShooterMotors.StarboardShooter.SENSOR_PHASE)
            .withTimeout(ShooterMotors.StarboardShooter.TIMEOUT)
            .withCurrentLimitEnabled(ShooterMotors.StarboardShooter.ENABLE_CURRENT_LIMIT)
            .withCurrentLimit(ShooterMotors.StarboardShooter.CURRENT_LIMIT)
            .withOpenLoopRampRate(ShooterMotors.StarboardShooter.OPEN_LOOP_RAMP)
            .withPeakOutputForward(ShooterMotors.StarboardShooter.PEAK_OUTPUT_FORWARD)
            .withPeakOutputReverse(ShooterMotors.StarboardShooter.PEAK_OUTPUT_REVERSE)
            .withNeutralMode(ShooterMotors.StarboardShooter.NEUTRAL_MODE)
            .withMaster(portMotor)
            .build();
            
        addChild("PortShooter", portMotor);
        addChild("StarboardShooter", starboardMotor);
    }

    public void set(double speed) {
        this.portMotor.drivePercentOutput(speed);
    }

    public double getCurrentCurrent() {
        return portMotor.motor.getOutputCurrent() + starboardMotor.motor.getOutputCurrent();
    }
    
    @Override
    public void periodic() {
        // This method will be called once per scheduler run

        atSpeed = (portMotor.getSensorVelocity() >= ShooterMotionParameters.NOMINAL_VELOCITY);
        // if(atSpeed) System.out.println("MOTOR GO BRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR");
        // TODO: Implement Encoder conversion utilities
        SmartDashboard.putNumber("ShooterRPM", (portMotor.getSensorVelocity()/2048)*(1000/100)*60);
    }
}
