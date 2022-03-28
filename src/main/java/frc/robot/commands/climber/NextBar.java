// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.climber;

import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;//+ is towards stationaries

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Constants.ClimberConstants;
import frc.robot.Constants.ClimberConstants.ClimberMotionParameters;
import frc.robot.subsystems.Climber;

public class NextBar extends CommandBase {
    public boolean done, behindBar, aboveBar, finishedUnhooking;
    public double encoderTicks=17;
    public double encoderTicks2=17;
    public double finalManuver = 0;
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
        finalManuver = 0;
        encoderTicks = 17;
        encoderTicks2 = 17;
        finishedUnhooking = false;
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
        !behindBar&&!aboveBar&&(climber.mainPosition()>ClimberMotionParameters.CLIMBER_TILT_BACK_ANGLE_DISTANCE_THING)  && (finalManuver==0)) {
            SmartDashboard.putString("Climber Command", "preparing to tilt back for next bar case 2");
            if(encoderTicks==17){
                encoderTicks=climber.mainPosition();
                System.out.println("set encoder tics to " + encoderTicks);
                climber.setWinch(-ClimberConstants.ClimberMotionParameters.CLIMBER_PERCENT_OUTPUT);
            }else if(Math.abs(climber.mainPosition())>(Math.abs(encoderTicks)+ClimberConstants.ClimberMotionParameters.BAR_HEIGHT_OFFSET)  && (finalManuver==0)){
                climber.setTilt(0);
                climber.setWinch(0);
                aboveBar=true;
                SmartDashboard.putString("Climber Command", "bar height offset reached, preparing to tilt back");
            }
    }

        if (climber.stationaryLocked() &&
            !behindBar &&
            aboveBar &&
            climber.tiltAngle() > ClimberConstants.ClimberMotionParameters.NEXT_BAR_ANGLE  && (finalManuver==0)) {
            climber.setWinch(0);
            climber.setTilt(-ClimberConstants.ClimberMotionParameters.TILT_PERCENT_OUTPUT); //tilt back until angled with the hooks behind the next bar
            // if (climber.tiltAngle() > ClimberConstants.ClimberMotionParameters.EXTEND_START_ANGLE && climber.mainPosition() > ClimberConstants.ClimberMotionParameters.NEXT_BAR_DISTANCE) { //if you are sufficiently tilted that you can start reaching back and still end up behind the next bar and you haven't reached back enough
            //   climber.setWinch(-ClimberConstants.ClimberMotionParameters.CLIMBER_PERCENT_OUTPUT); //extend main hooks
            // }
            SmartDashboard.putString("Climber Command", "tilting back to get behind next bar");
        } else if (!behindBar &&
                   climber.stationaryLocked() &&
                   climber.tiltAngle() <= ClimberConstants.ClimberMotionParameters.NEXT_BAR_ANGLE  && (finalManuver==0)) {
            climber.setTilt(0);
            climber.setWinch(-ClimberConstants.ClimberMotionParameters.CLIMBER_PERCENT_OUTPUT); //extend main hooks
            SmartDashboard.putString("Climber Command", "tilted back sufficently to reach next bar, extedning hooks");
        }

        if (!behindBar &&
            climber.mainPosition() < ClimberConstants.ClimberMotionParameters.NEXT_BAR_DISTANCE  && (finalManuver==0)) { //if main hooks are far back enough
            climber.setWinch(0); //stop extending
            behindBar = true; //you are now behind the bar
            SmartDashboard.putString("Climber Command", "finished extending to grab next bar");
        }

        if (behindBar &&
            climber.tiltAngle() <= ClimberConstants.ClimberMotionParameters.NEXT_BAR_GRAB_ANGLE && (finalManuver==0)) { //if you are behind the bar
            climber.setTilt(ClimberConstants.ClimberMotionParameters.SLOW_TILT_PERCENT_OUTPUT); //tilt forward until you can grab the bar
            SmartDashboard.putString("Climber Command", "tilting forward to grab next bar");
        } else if (behindBar &&
                   climber.tiltAngle() >= ClimberConstants.ClimberMotionParameters.NEXT_BAR_GRAB_ANGLE  && (finalManuver==0)) {
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
            !finishedUnhooking &&
            climber.mainPosition() <= ClimberConstants.ClimberMotionParameters.CLIMBER_BOTTOM-10000) { //if locked on next bar and not retracted sufficiently
            //climber.setWinch(ClimberConstants.ClimberMotionParameters.CLIMBER_PERCENT_OUTPUT); //retract main hooks
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

            climber.setWinch(ClimberConstants.ClimberMotionParameters.SLOW_CLIMBER_PERCENT_OUTPUT);
            if(climber.tiltAngle()>ClimberConstants.ClimberMotionParameters.MAX_NEGATIVE_TILT){
                climber.setTilt(-ClimberConstants.ClimberMotionParameters.SLOW_TILT_PERCENT_OUTPUT);
                SmartDashboard.putString("Climber Command", "tilting to protect clutch");
            }else{
                climber.setTilt(0);
                SmartDashboard.putString("Climber Command", "tilt stopped due to hitting saftey limit");

            }
            //climber.setTilt(-ClimberConstants.ClimberMotionParameters.CLIMBER_CLUTCH_SAFTEY_SPEED);








            if(climber.stationaryLocked()){
                SmartDashboard.putString("Climber Command", "stationaries still locked");
            }else{
                if(encoderTicks2==17){
                    encoderTicks=climber.mainPosition();
                    System.out.println("set encoder tics two to " + encoderTicks2);
                    climber.setWinch(-ClimberConstants.ClimberMotionParameters.SLOW_CLIMBER_PERCENT_OUTPUT);
                }else if(Math.abs(climber.mainPosition())<(Math.abs(encoderTicks2)-ClimberConstants.ClimberMotionParameters.STATIONARY_REMOVAL_OFFSET)){
                    climber.setTilt(0);
                    climber.setWinch(0);
                    finishedUnhooking=true;
                    SmartDashboard.putString("Climber Command", "finished unhooking stationaries");
                    if((finalManuver==0)){
                        finalManuver = 1;
                    }
                }
            }

            SmartDashboard.putString("Climber Command", "retracting to remove stationary hooks from initial bar");
        }

        if (behindBar &&
            (finalManuver>0) &&
            climber.mainLocked() &&
            !climber.stationaryLocked() &&
            finishedUnhooking) { //if locked on next bar and  retracted sufficiently to drop the hooks

                SmartDashboard.putString("Climber Command","On next bar, moving stationaries around bar");

            if(climber.mainPosition() > ClimberConstants.ClimberMotionParameters.PIVOT_AROUND_NEXT_BAR_MAIN_POSITION && finalManuver==1){
                climber.setWinch(-ClimberConstants.ClimberMotionParameters.CLIMBER_DESCEND_PERCENT_OUTPUT);
                if(climber.tiltAngle()>ClimberMotionParameters.MAX_NEGATIVE_TILT){
                    climber.setTilt(-ClimberConstants.ClimberMotionParameters.TILT_PERCENT_OUTPUT);
                } else{
                    climber.setTilt(0);
                }
            } else if(climber.mainPosition() < ClimberConstants.ClimberMotionParameters.PIVOT_AROUND_NEXT_BAR_MAIN_POSITION&&climber.tiltAngle()<0){
                if(climber.mainPosition() <= ClimberConstants.ClimberMotionParameters.CLIMBER_BOTTOM-20000&&finalManuver==1&&climber.tiltAngle()>ClimberConstants.ClimberMotionParameters.STATIONARIES_MISS_BAR_ANGLE){//if ok to start winching up
                    climber.setWinch(ClimberConstants.ClimberMotionParameters.CLIMBER_PERCENT_OUTPUT);             //retract the hooks
                }else{
                    climber.setWinch(0);
                    finalManuver=2;
                }
                climber.setWinch(0);
                climber.setTilt(ClimberConstants.ClimberMotionParameters.TILT_PERCENT_OUTPUT); 
            }else{
                climber.setTilt(0);
                climber.setWinch(0);
                SmartDashboard.putString("Climber Command","finished the thing");
                done = true;
            }
            // System.out.println("finished the normal way withthe climb");
            // done = true;

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
