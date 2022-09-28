// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.climber;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;
import frc.robot.Constants.ClimberConstants;
//import frc.robot.Constants.ClimberConstants.*;



public class LockStationary extends CommandBase {
    public boolean mainLocked, dropping;

    public Climber climber;
    /** Creates a new LockStationary. */
    public LockStationary(Climber climber) {
        // Use addRequirements() here to declare subsystem dependencies.
        this.climber = climber;
        addRequirements(this.climber);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        SmartDashboard.putString("Climber Command","Locking Stationaries");
        mainLocked = climber.mainLocked();
        dropping = false;
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {    //climber is hanging on or lined up with a bar(needs seperate variable b/c otherwise idk, prbly smthn bad)
        if (!mainLocked) { //is climber main hooks locked on a bar?
            climber.setWinch(ClimberConstants.ClimberMotionParameters.CLIMBER_PERCENT_OUTPUT);  //if not pull until you grab it 
            SmartDashboard.putString("Climber Command", "mains not locked, retracting to grab bar con el manos principal");
            if (climber.mainLocked()) {
                mainLocked = true;
                climber.setWinch(0);
                SmartDashboard.putString("Climber Command", "mains now locked");
            }
        }
    
        if (climber.mainLocked() &&
            climber.mainPosition() <= ClimberConstants.ClimberMotionParameters.CLIMBER_BOTTOM + 7000 && 
            !dropping
            ) {           //if climber is not all the way retracted the hooks are locked on
            climber.setWinch(ClimberConstants.ClimberMotionParameters.CLIMBER_PERCENT_OUTPUT);             //retract the hooks
            SmartDashboard.putString("Climber Command", "Doing an SFR climb but only for a bit");

        // if (!climber.stationaryLocked() &&
        //     climber.mainPosition() >= ClimberConstants.ClimberMotionParameters.TILT_START) {               //while your doing that, if your close enough to the top
        //     climber.setTilt(ClimberConstants.ClimberMotionParameters.TILT_PERCENT_OUTPUT);                 //start tilting the stationary hooks into position
        // }
        }else if(climber.mainPosition() > ClimberConstants.ClimberMotionParameters.CLIMBER_BOTTOM && !dropping) {
            climber.setWinch(0);

        }
        if (climber.mainPosition() > ClimberConstants.ClimberMotionParameters.CLIMBER_BOTTOM-2000 &&
                   climber.mainLocked() && !climber.stationaryLocked() &&
                   climber.tiltAngle() < ClimberConstants.ClimberMotionParameters.STATIONARY_LOCK_ANGLE) { //if you are sufficiently retracted and the main hooks are locked and the stationaries are not and your tilt angle is insufficient to drop the stationary hooks onto the bar
            // climber.setWinch(0);                                                                           //stop retracting
            climber.setTilt(ClimberConstants.ClimberMotionParameters.TILT_PERCENT_OUTPUT);                 //tilt until your in position to drop the stationary hooks onto the bar
            SmartDashboard.putString("Climber Command", "SFR climb done, tilting stationaries into position");
        } else if (!climber.stationaryLocked() &&
                   climber.tiltAngle() >= ClimberConstants.ClimberMotionParameters.STATIONARY_LOCK_ANGLE &&
                   climber.mainPosition() >= ClimberConstants.ClimberMotionParameters.CLIMBER_DESCEND_SAFTEY_SHUTOFF) { //if its just the stationary hooks that need to be droped
            dropping = true;
            climber.setTilt(0);
            climber.setWinch(-ClimberConstants.ClimberMotionParameters.CLIMBER_DESCEND_PERCENT_OUTPUT);             //drop down to lock 'em
            SmartDashboard.putString("Climber Command", "stationaries in position, dropping down onto the bar");
        } else if (!climber.stationaryLocked() &&
        climber.tiltAngle() >= ClimberConstants.ClimberMotionParameters.STATIONARY_LOCK_ANGLE &&
        climber.mainPosition() < ClimberConstants.ClimberMotionParameters.CLIMBER_DESCEND_SAFTEY_SHUTOFF) {
            climber.setTilt(0);
            climber.setWinch(0);
            System.out.println("climb aborted due to climber descend saftey shuttoff");
        }



        //safties to prevent exiting frame permiter or incurring death by rohan
        if (climber.mainPosition() < ClimberConstants.ClimberMotionParameters.CLIMBER_TOP-20000){
            climber.setWinch(0);
            SmartDashboard.putString("Climber Command", "stopped climbing b/c climber too tall");
        }
        if (climber.tiltAngle() > ClimberConstants.ClimberMotionParameters.STATIONARY_LOCK_ANGLE + 3){
            climber.setTilt(0);
            SmartDashboard.putString("Climber Command", "stopped climbing b/c tilt too tilty");
        }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        climber.setWinch(0);
        climber.setTilt(0);
        SmartDashboard.putString("Climber Command","Stationaries Locked");
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return climber.stationaryLocked(); //if stationary hooks locked command done
    }
}
