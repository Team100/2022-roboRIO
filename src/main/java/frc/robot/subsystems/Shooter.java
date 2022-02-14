// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants.*;
import frc.robot.FRCLib.Motors.FRCTalonFX;


public class Shooter extends SubsystemBase {
    private FRCTalonFX shootMotor;

    private boolean atSpeed = false;
    public boolean isAtSpeed() { return atSpeed; }

    /** Creates a new Shooter. */
    public Shooter() {
        shootMotor = new FRCTalonFX.FRCTalonFXBuilder(ShooterMotors.Shooter.CAN_ID)
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
            .build();
            
        addChild("Shooter", shootMotor); 
    }

    public void set(double speed) {
        SmartDashboard.putNumber("IS DO?", speed);
        this.shootMotor.drivePercentOutput(speed);
    }
    
    @Override
    public void periodic() {
        // This method will be called once per scheduler run

        atSpeed = (shootMotor.getSensorVelocity() >= ShooterMotionParameters.NOMINAL_VELOCITY);
        //if(atSpeed) System.out.println("MOTOR GO BRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR");
        SmartDashboard.putBoolean("MOTOR GO BRRRRR?", atSpeed);

    }
}
