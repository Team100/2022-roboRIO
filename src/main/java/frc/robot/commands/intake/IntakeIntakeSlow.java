package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Intake;

public class IntakeIntakeSlow extends CommandBase {
    public boolean done;

    public Intake intake;

    /**
     * Creates a new IntakeIntake.
     */
    public IntakeIntakeSlow(Intake intake) {
        // Use addRequirements() here to declare subsystem dependencies.
        this.intake = intake;
        addRequirements(this.intake);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        done = false;
        intake.disable();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        if (intake.getMeasurement() >= 20) {
            intake.runPivot(Constants.IntakeConstants.IntakeMotionParameters.INTAKE_PIVOT_PERCENT_OUTPUT_DOWN);
            intake.runSpinner(0);
        } else if (intake.getMeasurement() >= 10) {
            intake.runPivot(Constants.IntakeConstants.IntakeMotionParameters.INTAKE_PIVOT_PERCENT_OUTPUT_DOWN_DECEL);
            intake.runSpinner(0);
        } else {
            intake.runPivot(0);
            intake.runSpinner(Constants.IntakeConstants.IntakeMotionParameters.INTAKE_SPINNER_SLOW_PERCENT_OUTPUT);
        }
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

  
    
