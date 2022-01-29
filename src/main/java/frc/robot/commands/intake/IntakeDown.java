package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Constants;
//import frc.robot.Constants.IntakeConstants.IntakeMotors.IntakePivot;
import frc.robot.subsystems.IntakePivot;
//import frc.robot.subsystems.IntakePivot.ActionState;

public class IntakeDown extends CommandBase {

  public IntakePivot intakePivot;

  /**
   * Creates a new IntakeIntake.
   */
  public IntakeDown(IntakePivot intakePivot) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.intakePivot= intakePivot;
    addRequirements(this.intakePivot);
  }


  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //intakePivot.actionState = ActionState.INTAKING;
    intakePivot.pivot.drivePercentOutput(Constants.IntakeConstants.IntakeMotionParameters.INTAKE_PERCENT_OUTPUT);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    intakePivot.pivot.drivePercentOutput(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {

    return false;
  }
}

  
    

