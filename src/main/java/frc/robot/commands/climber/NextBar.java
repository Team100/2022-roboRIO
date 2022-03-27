// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.climber;

import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Constants.ClimberConstants;
import frc.robot.Constants.ClimberConstants.ClimberMotionParameters;
import frc.robot.subsystems.Climber;

public class NextBar extends CommandBase {
    public boolean done, behindBar, aboveBar, finishing;
    public double encoderTicks=17;
    public Climber climber;
    
    /** Creates a new LockStationary. */
    public NextBar(Climber climber) {
        // Use addRequirements() here to declare subsystem dependencies.
        this.climber = climber;
        addRequirements(this.climber);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        done = false;
        behindBar = false;
        aboveBar = false;
        finishing = false;
        encoderTicks = 17;
        SmartDashboard.putString("Climber Command","Grabbing Next Bar");
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        // if (climber.mainLocked() && 
        //     climber.stationaryLocked() && 
        //     !behindBar) {
        //     climber.setWinch(-ClimberConstants.ClimberMotionParameters.CLIMBER_PERCENT_OUTPUT);
        //     SmartDashboard.putString("Climber Command", "preparing to tilt back for next bar case 1");
        // }

        if (climber.stationaryLocked() && 
        !behindBar&&!aboveBar&&(climber.mainPosition()>ClimberMotionParameters.CLIMBER_TILT_BACK_ANGLE_DISTANCE_THING)  && !finishing) {
            SmartDashboard.putString("Climber Command", "preparing to tilt back for next bar case 2");
            if(encoderTicks==17){
                encoderTicks=climber.mainPosition();
                System.out.println("set encoder tics to " + encoderTicks);
                climber.setWinch(-ClimberConstants.ClimberMotionParameters.CLIMBER_PERCENT_OUTPUT);
            }else if(Math.abs(climber.mainPosition())>(Math.abs(encoderTicks)+ClimberConstants.ClimberMotionParameters.BAR_HEIGHT_OFFSET)  && !finishing){
                climber.setTilt(0);
                climber.setWinch(0);
                aboveBar=true;
                SmartDashboard.putString("Climber Command", "bar height offset reached, preparing to tilt back");
            }
    }

        if (climber.stationaryLocked() &&
            !behindBar &&
            aboveBar &&
            climber.tiltAngle() > ClimberConstants.ClimberMotionParameters.NEXT_BAR_ANGLE  && !finishing) {
            climber.setWinch(0);
            climber.setTilt(-ClimberConstants.ClimberMotionParameters.TILT_PERCENT_OUTPUT); //tilt back until angled with the hooks behind the next bar
            // if (climber.tiltAngle() > ClimberConstants.ClimberMotionParameters.EXTEND_START_ANGLE && climber.mainPosition() > ClimberConstants.ClimberMotionParameters.NEXT_BAR_DISTANCE) { //if you are sufficiently tilted that you can start reaching back and still end up behind the next bar and you haven't reached back enough
            //   climber.setWinch(-ClimberConstants.ClimberMotionParameters.CLIMBER_PERCENT_OUTPUT); //extend main hooks
            // }
            SmartDashboard.putString("Climber Command", "tilting back to get behind next bar");
        } else if (!behindBar &&
                   climber.stationaryLocked() &&
                   climber.tiltAngle() <= ClimberConstants.ClimberMotionParameters.NEXT_BAR_ANGLE  && !finishing) {
            climber.setTilt(0);
            climber.setWinch(-ClimberConstants.ClimberMotionParameters.CLIMBER_PERCENT_OUTPUT); //extend main hooks
            SmartDashboard.putString("Climber Command", "tilted back sufficently to reach next bar, extedning hooks");
        }

        if (!behindBar &&
            climber.mainPosition() < ClimberConstants.ClimberMotionParameters.NEXT_BAR_DISTANCE  && !finishing) { //if main hooks are far back enough
            climber.setWinch(0); //stop extending
            behindBar = true; //you are now behind the bar
            SmartDashboard.putString("Climber Command", "finished extending to grab next bar");
        }

        if (behindBar &&
            climber.tiltAngle() <= ClimberConstants.ClimberMotionParameters.NEXT_BAR_GRAB_ANGLE && !finishing) { //if you are behind the bar
            climber.setTilt(ClimberConstants.ClimberMotionParameters.TILT_PERCENT_OUTPUT); //tilt forward until you can grab the bar
            SmartDashboard.putString("Climber Command", "tilting forward to grab next bar");
        } else if (behindBar &&
                   climber.tiltAngle() >= ClimberConstants.ClimberMotionParameters.NEXT_BAR_GRAB_ANGLE  && !finishing) {
            climber.setTilt(0);
            SmartDashboard.putString("Climber Command", "tilted forward enough to grab next bar but didn't get it, giving up");
            done = true;
        }

        // if (behindBar &&
        //     !climber.mainLocked() &&
        //     climber.tiltAngle() >= ClimberConstants.ClimberMotionParameters.NEXT_BAR_GRAB_ANGLE) { //if you are in position to grab the bar and haven't yet
        //     climber.setTilt(0); //stop tilting
        //     climber.setWinch(ClimberConstants.ClimberMotionParameters.CLIMBER_PERCENT_OUTPUT); //retract main hooks
        //     SmartDashboard.putString("Climber Command", "retracting to grab next bar");
        // }

        if (behindBar &&
            climber.mainLocked() &&
            climber.mainPosition() <= ClimberConstants.ClimberMotionParameters.CLIMBER_BOTTOM-25000) { //if locked on next bar and not retracted sufficiently
            climber.setWinch(ClimberConstants.ClimberMotionParameters.CLIMBER_PERCENT_OUTPUT); //retract main hooks
            //if(climber.tiltAngle() > ClimberConstants.ClimberMotionParameters.NEXT_BAR_GRAB_ANGLE)
            //climber.setTilt(-ClimberConstants.ClimberMotionParameters.TILT_PERCENT_OUTPUT/2); //stop tilting



            // if(encoderTicks2==17){
            //     climber.setTilt(0);
            //     encoderTicks=climber.mainPosition();
            //     System.out.println("set encoder tics 2 to " + encoderTicks2);
            //     climber.setWinch(ClimberConstants.ClimberMotionParameters.CLIMBER_PERCENT_OUTPUT);
            // }else if(Math.abs(climber.mainPosition())<(Math.abs(encoderTicks2)-ClimberConstants.ClimberMotionParameters.TILT_NEXT_BAR_HEIGHT_OFFSET)){
            //     SmartDashboard.putString("Climber Command", "bar assumed locked, begening tilt to avoid clutch slip");
            //     climber.setTilt(-ClimberConstants.ClimberMotionParameters.CLIMBER_CLUTCH_SAFTEY_SPEED);
            // }

            climber.setWinch(ClimberConstants.ClimberMotionParameters.CLIMBER_PERCENT_OUTPUT);

            climber.setTilt(-ClimberConstants.ClimberMotionParameters.CLIMBER_CLUTCH_SAFTEY_SPEED);
            finishing = true;



            //climber.setTilt(-ClimberConstants.ClimberMotionParameters.CLIMBER_CLUTCH_SAFTEY_SPEED);
            SmartDashboard.putString("Climber Command", "retracting to remove stationary hooks from initial bar");
        }

        if (behindBar &&
            climber.mainLocked() &&
            finishing &&
            !climber.stationaryLocked()) { //if locked on next bar and  retracted sufficiently
            climber.setWinch(0); //stop winch
            climber.setTilt(0); //stop tilting
            SmartDashboard.putString("Climber Command","Next Bar Command finished");
            System.out.println("finished the normal way withthe climb");
            done = true;
        }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        climber.setWinch(0);
        climber.setTilt(0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return done;
    }
}
