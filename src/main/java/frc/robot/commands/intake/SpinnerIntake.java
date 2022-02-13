package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Intake.ActionState;
import frc.robot.subsystems.Intake.ValidAngles;

public class SpinnerIntake extends CommandBase {
    public boolean done;

    public Intake intake;

    /**
     * Creates a new IntakeIntake.
     */
    public SpinnerIntake(Intake intake) {
        // Use addRequirements() here to declare subsystem dependencies.
        this.intake = intake;
        addRequirements(this.intake);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        done = false;
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        if (intake.getCurrentAngle() == ValidAngles.DOWN) {
            intake.setActionState(ActionState.INTAKING);
            intake.runSpinner(Constants.IntakeConstants.IntakeMotionParameters.INTAKE_PERCENT_OUTPUT);
            done = true;
            return;
        }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return done;
    }
}

  
    

