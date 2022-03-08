// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.climber;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Climber;

public class ClimberControl extends CommandBase {
    /** Creates a new Climb. */
    private final Climber climber;
    //private final Joystick gamepad;
    private final Joystick leftJoystick;
    private final Joystick rightJoystick;

    public ClimberControl(Climber climber, Joystick lefJoystick, Joystick righJoystick) {
        this.climber = climber;
        leftJoystick = lefJoystick;
        rightJoystick = righJoystick;

        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(climber);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        System.out.println("initialize climb");
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        //System.out.println("climbing");
       // if ((1-(leftJoystick.getY() < 0.05){
        //      climber.setWinch(-(leftJoystick.getY()*(-leftJoystick.getZ()+1)/2));
        if ((leftJoystick.getY() > 0.05)||(leftJoystick.getY() < -0.05)){
        //System.out.println(-(leftJoystick.getY()*(-leftJoystick.getZ()+1)/2));
        //System.out.println("doing tilt of " + -(leftJoystick.getY()*(-leftJoystick.getZ()+1)/2));

        climber.setWinch((leftJoystick.getY()*(-leftJoystick.getZ()+1)/2));
        }  else {
            climber.setWinch(0);
        }  
    //}
    if ((rightJoystick.getY() > 0.05)||(rightJoystick.getY() < -0.05)){
        // System.out.println("doing tilt of " + -(rightJoystick.getY()*(-rightJoystick.getZ()+1)/2));
        climber.setTilt((rightJoystick.getY()*(-rightJoystick.getZ()+1)/2));
        } else {
            climber.setTilt(0);
            //System.out.println("not doing tilt");

        }

       // if ((1-(rightJoystick.getY()+1/2)/2) < 0.05){
        //      climber.setTilt(-(rightJoystick.getY()*(-rightJoystick.getZ()+1)/2));
        //System.out.println("climbing right");    
   // }

        //System.out.println("setWinch = " + (leftJoystick.getY()*(-leftJoystick.getZ()+1)/2) + "     set tilt =  " + (rightJoystick.getY()*(-rightJoystick.getZ()+1)/2));


        // System.out.println("gamepad axis = " + gamepad.getRawAxis(3));

        // // Winch
        // if (Math.abs(gamepad.getRawAxis(3)) < 0.05){
        //     climber.setWinch(0);
        // } else {
        //     climber.setWinch(-gamepad.getRawAxis(3));
        //     System.out.println("winch set to: " + -gamepad.getRawAxis(3));
        // }

        // //Tilt
        // if (gamepad.getRawButton(Constants.ClimberConstants.ClimberControls.TILT_FORWARD_GAMEPAD_BUTTON)) {
        //     climber.setTilt(Constants.ClimberConstants.ClimberMotionParameters.TILT_PERCENT_OUTPUT);
        // } else if (gamepad.getRawButton(Constants.ClimberConstants.ClimberControls.TILT_REVERSE_GAMEPAD_BUTTON)) {
        //     climber.setTilt(-Constants.ClimberConstants.ClimberMotionParameters.TILT_PERCENT_OUTPUT);
        // }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        climber.setTilt(0);
        climber.setWinch(0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
