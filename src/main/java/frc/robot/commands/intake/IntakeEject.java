package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Intake;

public class IntakeEject extends CommandBase {
    public boolean done;

    public Intake intake;

    /**
     * Creates a new IntakeEject.
     */
    public IntakeEject(Intake intake) {
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
        if(intake.getMeasurement()<=10){
            intake.runSpinner(-Constants.IntakeConstants.IntakeMotionParameters.INTAKE_SPINNER_PERCENT_OUTPUT);
        }else{
            intake.runSpinner(0);
        }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        // intake.runPivot(0);
        // intake.disable();
        intake.runSpinner(0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}

  
    

