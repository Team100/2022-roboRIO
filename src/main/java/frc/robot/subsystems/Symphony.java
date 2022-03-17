// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.ArrayList;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.music.Orchestra;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Symphony extends SubsystemBase {
  /** Creates a new Symphony. */

  Orchestra orchestra;
  TalonFX [] FX =  {new TalonFX(Constants.ClimberConstants.ClimberMotors.Tilt.CAN_ID)};

  public Symphony() {
    ArrayList<TalonFX> instruments = new ArrayList<TalonFX>();
    for (int i = 0; i < FX.length; ++i) {
      instruments.add(FX[i]);
    }
    orchestra = new Orchestra(instruments);
    orchestra.loadMusic("mega.chrp"); 
    orchestra.pause();
  }

  public void play(){
    if (orchestra.isPlaying()) orchestra.pause();
    else orchestra.play();
  }

  @Override
  public void periodic() {
    SmartDashboard.putBoolean("Is playing", orchestra.isPlaying());
    // This method will be called once per scheduler run
  }
}
