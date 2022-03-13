// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.automatic.criteria;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Shooter;

public class ShootEndCriteria extends CommandBase {
    private Shooter shooter;

    /** Creates a new ShootOneEndCriteria. */
    public ShootEndCriteria(Shooter shooter) {
        this.shooter = shooter;

        // Use addRequirements() here to declare subsystem dependencies.
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {}

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {}

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {}

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return true;
        //return shooter.getCurrentCurrent() <= Constants.ShooterConstants.ShooterMotionParameters.IDLE_CURRENT_THRESHOLD;
    }
}
