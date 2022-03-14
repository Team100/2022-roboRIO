package frc.robot.commands.intake;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Intake;

public class IntakeIntake extends CommandBase {
    public boolean done;

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
        done = false;
        //intake.runSpinner(Constants.IntakeConstants.IntakeMotionParameters.INTAKE_SPINNER_PERCENT_OUTPUT);

    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        // if (intake.getPot() <= Constants.IntakeConstants.PivotConstants.DOWN_POSITION) {
        //      intake.runPivot(Constants.IntakeConstants.IntakeMotionParameters.INTAKE_PIVOT_PERCENT_OUTPUT);
        // }
        // if(intake.getPot() >= Constants.IntakeConstants.PivotConstants.DOWN_POSITION){
        // intake.setPivot(Constants.IntakeConstants.PivotConstants.DOWN_POSITION);
        // SmartDashboard.putNumber("intake setpoint", Constants.IntakeConstants.PivotConstants.DOWN_POSITION);
        if (intake.getPot() >= 21) {
            intake.runPivot(Constants.IntakeConstants.IntakeMotionParameters.INTAKE_PIVOT_PERCENT_OUTPUT);
            intake.runSpinner(Constants.IntakeConstants.IntakeMotionParameters.INTAKE_SPINNER_PERCENT_OUTPUT);
        } else {
            intake.runPivot(0);
            intake.runSpinner(Constants.IntakeConstants.IntakeMotionParameters.INTAKE_SPINNER_PERCENT_OUTPUT);
        }
        //     intake.runPivot(0);
        // }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        // intake.setPivot(1.6);
        // intake.runSpinner(0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}

  
    

