    // Copyright (c) FIRST and other WPILib contributors.
    // Open Source Software; you can modify and/or share it under the terms of
    // the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.indexer;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Indexer; 

import frc.robot.Constants;

public class IndexerIntake extends CommandBase {
    private Indexer indexer;
    private boolean done, secondBall, B1C2T;

    /** Creates a new IndexerIntake. */
    public IndexerIntake(Indexer indexer) {
        this.indexer = indexer;

        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(indexer);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        // indexer.runMotorOne(Constants.IndexerConstants.IndexerMotionParameters.STAGE_ONE_PERCENT_OUTPUT_FORWARD);
        // indexer.runMotorTwo(Constants.IndexerConstants.IndexerMotionParameters.STAGE_TWO_PERCENT_OUTPUT_FORWARD);
        secondBall = indexer.getSensorTwo();
        done = false;
        //B1C2F= false;
        B1C2T= false;
    }

    public void stop(){
        indexer.runMotorOne(0);
        indexer.runMotorTwo(0);
        SmartDashboard.putString("Indexer command", "Stopped");
        done = true;
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        if (secondBall) {
            SmartDashboard.putString("Indexer command","Intaking Second Ball");
            indexer.runMotorOne(Constants.IndexerConstants.IndexerMotionParameters.STAGE_ONE_PERCENT_OUTPUT_FORWARD);
            indexer.runMotorTwo(0);
        }else{
            SmartDashboard.putString("Indexer command","Intaking First Ball");
            indexer.runMotorOne(Constants.IndexerConstants.IndexerMotionParameters.STAGE_ONE_PERCENT_OUTPUT_FORWARD);
            indexer.runMotorTwo(Constants.IndexerConstants.IndexerMotionParameters.STAGE_TWO_PERCENT_OUTPUT_FORWARD);
            if(indexer.getSensorTwo()){
                stop();
            }
        }
        if (indexer.getSensorOne() && indexer.getSensorTwo()) {
            indexer.runMotorOne(Constants.IndexerConstants.IndexerMotionParameters.STAGE_TWO_PERCENT_OUTPUT_FORWARD);
            indexer.runMotorTwo(0);
            B1C2T = true;
        }
        if(B1C2T && !indexer.getSensorOne()) {
            stop();
        }
        SmartDashboard.putBoolean("B1C2T", B1C2T);

    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        // indexer.runMotorOne(0);
        // indexer.runMotorTwo(0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return done;
    }
}
