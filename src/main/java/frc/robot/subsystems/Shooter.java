// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants.*;
import frc.robot.FRCLib.Motors.FRCNEO;


public class Shooter extends SubsystemBase {
    private FRCNEO shootMotor,shootMotorFollower;

    // private boolean atSpeed = false;
    public int speed() { 
        return Math.abs(shootMotor.getSensorVelocity()); 
    }

    /** Creates a new Shooter. */
    public Shooter() {
        shootMotor = new FRCNEO.FRCNEOBuilder(ShooterMotors.Shooter.CAN_ID)
            .withInverted(ShooterMotors.Shooter.INVERT)
            .withFeedbackPort(ShooterMotors.Shooter.FEEDBACK_PORT)
            .withSensorPhase(ShooterMotors.Shooter.SENSOR_PHASE)
            .withTimeout(ShooterMotors.Shooter.TIMEOUT)
            .withCurrentLimitEnabled(ShooterMotors.Shooter.ENABLE_CURRENT_LIMIT)
            .withCurrentLimit(ShooterMotors.Shooter.CURRENT_LIMIT)
            .withOpenLoopRampRate(ShooterMotors.Shooter.OPEN_LOOP_RAMP)
            .withPeakOutputForward(ShooterMotors.Shooter.PEAK_OUTPUT_FORWARD)
            .withPeakOutputReverse(ShooterMotors.Shooter.PEAK_OUTPUT_REVERSE)
            .withNeutralMode(ShooterMotors.Shooter.NEUTRAL_MODE)
            .withKP(ShooterMotionParameters.KP)
            .withKI(ShooterMotionParameters.KI)
            .withKF(ShooterMotionParameters.KF)
            .withKD(ShooterMotionParameters.KD)
            .build();

        shootMotorFollower = new FRCNEO.FRCNEOBuilder(ShooterMotors.ShooterFollower.CAN_ID)
            .withInverted(ShooterMotors.ShooterFollower.INVERT)
            .withFeedbackPort(ShooterMotors.ShooterFollower.FEEDBACK_PORT)
            .withSensorPhase(ShooterMotors.ShooterFollower.SENSOR_PHASE)
            .withTimeout(ShooterMotors.ShooterFollower.TIMEOUT)
            .withCurrentLimitEnabled(ShooterMotors.ShooterFollower.ENABLE_CURRENT_LIMIT)
            .withCurrentLimit(ShooterMotors.ShooterFollower.CURRENT_LIMIT)
            .withOpenLoopRampRate(ShooterMotors.ShooterFollower.OPEN_LOOP_RAMP)
            .withPeakOutputForward(ShooterMotors.ShooterFollower.PEAK_OUTPUT_FORWARD)
            .withPeakOutputReverse(ShooterMotors.ShooterFollower.PEAK_OUTPUT_REVERSE)
            .withNeutralMode(ShooterMotors.ShooterFollower.NEUTRAL_MODE)
            .withMaster(shootMotor)
            .build();
            
            
        addChild("Shooter", shootMotor); 
        addChild("ShooterFollower", shootMotorFollower); 
    }

    public void set(double speed) {
        this.shootMotor.drivePercentOutput(-speed);
    }

    public void setVelocity(double velocity){
        this.shootMotor.driveVelocity(velocity);
    }
    
    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        // SmartDashboard.putNumber("shooter offset", value)
        //SmartDashboard.putNumber("shooter current", shootMotor.motor.getOutputCurrent());
        SmartDashboard.putNumber("shooter velocity", (shootMotor.getSensorVelocity()));        
    }
}