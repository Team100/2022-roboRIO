package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Intake;

public class PivotDown extends CommandBase {

    public Intake intake;

    /**
     * Creates a new IntakeIntake.
     */
    public PivotDown(Intake intake) {
        this.intake = intake;
        
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(this.intake);
    }


    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        intake.runPivot(Constants.IntakeConstants.IntakeMotionParameters.INTAKE_PERCENT_OUTPUT);
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
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
