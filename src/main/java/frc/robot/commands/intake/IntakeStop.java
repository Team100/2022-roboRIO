package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class IntakeStop extends CommandBase {
    private Intake intake;

    /**
     * Creates a new IntakeStop.
     */
    public IntakeStop(Intake intake) {
        this.intake = intake;

        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(this.intake);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        this.intake.runSpinner(0);
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {}

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {}

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}

