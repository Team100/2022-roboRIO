package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Intake.ActionState;

public class IntakeIntake extends CommandBase {

  public Intake intake;

  /**
   * Creates a new IntakeIntake.
   */
  public IntakeIntake(Intake intake) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.intake = intake;
    addRequirements(this.intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    intake.actionState = ActionState.INTAKING;
    intake.spin.drivePercentOutput(Constants.IntakeConstants.IntakeMotionParameters.INTAKE_PERCENT_OUTPUT);

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

  
    

