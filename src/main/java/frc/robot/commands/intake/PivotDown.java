// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;
import frc.robot.Constants.IntakeConstants;

public class PivotDown extends CommandBase {
  public Intake intake;
  //public bool done;

  /** Creates a new PivotDown. */
  public PivotDown(Intake intake) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.intake = intake;
    addRequirements(this.intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // if(intake.getPot()>IntakeConstants.IntakeSensors.IntakePot.DOWN){
    //   intake.runPivot(0);
    // }else{
    //   intake.runPivot(IntakeConstants.IntakeMotionParameters.INTAKE_PIVOT_PERCENT_OUTPUT);
    // }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    intake.runPivot(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
