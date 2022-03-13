package frc.robot.commands.intake;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Intake;

public class IntakeStop extends CommandBase {
    public Intake intake;

    /**
     * Creates a new IntakeStop.
     */
    public IntakeStop(Intake intake) {
        // Use addRequirements() here to declare subsystem dependencies.
        this.intake = intake;
        addRequirements(this.intake);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        this.intake.runSpinner(0);
        this.intake.runPivot(0);
        // this.intake.setPivot(Constants.IntakeConstants.PivotConstants.UP_POSITION);
        // SmartDashboard.putNumber("intake setpoint", 0);
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        // if (intake.getPot() <= 2.55) {
        //     intake.runPivot(
        //         Constants.IntakeConstants.IntakeMotionParameters.INTAKE_PIVOT_PERCENT_OUTPUT_UP
        //         * (2.55 - intake.getPot()));
        // } else {
        //     intake.runPivot(0);
        // }
        intake.runPivot(0);
        intake.runSpinner(0);
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

