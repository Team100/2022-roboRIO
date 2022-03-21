// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.subsystems.Intake;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class BetterIntakeStop extends SequentialCommandGroup {
  /** Creates a new BetterIntakeStop. */
  public BetterIntakeStop(Intake intake) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(new ParallelRaceGroup(new IntakeStop(intake), new WaitCommand(Constants.IntakeConstants.IntakeMotionParameters.INTAKE_UP_TIMEOUT)), 
    new WaitCommand(Constants.IntakeConstants.IntakeMotionParameters.INTAKE_UP_TIME_TO_RESTART), 
    new InstantCommand(() -> { intake.enable(); })
    );
    //start moving the intake up using intake stop, if it hasn't finished after the length of the first wait then stop the command and wait the length of the second wait before restarting the command 
  }
}
