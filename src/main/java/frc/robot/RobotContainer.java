// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.*;
import frc.robot.commands.drivetrain.*;
import frc.robot.commands.intake.*;
import frc.robot.commands.indexer.*;
import frc.robot.commands.climber.*;
import frc.robot.commands.shooter.*;

/**
* This class is where the bulk of the robot should be declared. Since Command-based is a
* "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
* periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
* subsystems, commands, and button mappings) should be declared here.
*/
public class RobotContainer {
    // The robot's subsystems and commands are defined here...

    // Subsystems
    private final Drivetrain     drivetrain           = new Drivetrain    ();
    private final Climber        climber              = new Climber       ();
    private final Shooter        shooter              = new Shooter       ();
    private final Intake         intake               = new Intake        ();
    private final Indexer        indexer              = new Indexer       ();

    // OI Devices
    private final Joystick       leftJoystick         = new Joystick      (Constants.OI.LeftJoystick.PORT);
    private final Joystick       rightJoystick        = new Joystick      (Constants.OI.RightJoystick.PORT);
    private final Joystick       gamepad              = new Joystick      (Constants.OI.Gamepad.PORT);
    private final JoystickButton turboButton          = new JoystickButton(rightJoystick, Constants.OI.RightJoystick.DRIVE_TURBO);
    private final JoystickButton intakeIntakeButton   = new JoystickButton(leftJoystick, Constants.OI.LeftJoystick.INTAKE_INTAKE);
    private final JoystickButton alignButton          = new JoystickButton(gamepad, Constants.OI.Gamepad.ALIGN_CLIMBER);
    private final JoystickButton shootButton          = new JoystickButton(rightJoystick, Constants.OI.RightJoystick.SHOOTER_SHOOT);
    private final JoystickButton indexerIntakeButton  = new JoystickButton(gamepad, Constants.OI.Gamepad.INDEXER_INTAKE);
    private final JoystickButton ejectButton          = new JoystickButton(gamepad, Constants.OI.Gamepad.INDEXER_EJECT);
    private final JoystickButton feedButton           = new JoystickButton(gamepad, Constants.OI.Gamepad.INDEXER_FEED);
    private final JoystickButton climberControlButton = new JoystickButton(gamepad, Constants.OI.Gamepad.CLIMBER_CONTROL);

    // Commands
    private final Drive          driveCommand         = new Drive         (drivetrain, leftJoystick, rightJoystick);
    private final DriveFurious   driveFuriousCommand  = new DriveFurious  (drivetrain, leftJoystick, rightJoystick);
    private final AlignClimber   alignCommand         = new AlignClimber  (drivetrain);
    private final IntakeIntake   intakeIntakeCommand  = new IntakeIntake  (intake);
    private final IntakeStop     intakeStopCommand    = new IntakeStop    (intake);
    private final Shoot          shootCommand         = new Shoot         (shooter);
    private final IndexerStop    indexerStopCommand   = new IndexerStop   (indexer);
    private final IndexerIntake  intakeCommand        = new IndexerIntake (indexer);
    private final IndexerEject   ejectCommand         = new IndexerEject  (indexer);
    private final IndexerFeed    feedCommand          = new IndexerFeed   (indexer);
    private final ClimberStop    climberStopCommand   = new ClimberStop   (climber);
    private final ClimberControl climberControl       = new ClimberControl(climber, gamepad);

    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer() {
        // Set default commands
        drivetrain.setDefaultCommand(driveCommand);
        climber   .setDefaultCommand(climberStopCommand);
        indexer   .setDefaultCommand(indexerStopCommand);
        intake    .setDefaultCommand(intakeStopCommand);

        //Sanjan.setDefaultCommand(STOP)

        // Configure the button bindings
        configureButtonBindings();
    }

    /**
    * Use this method to define your button->command mappings. Buttons can be created by
    * instantiating a {@link GenericHID} or one of its subclasses ({@link
    * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
    * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
    */
    private void configureButtonBindings() {
        shootButton         .whileHeld  (shootCommand);
        turboButton         .whileHeld  (driveFuriousCommand);
        intakeIntakeButton  .whileHeld  (intakeIntakeCommand);
        alignButton         .whileHeld  (alignCommand);
        indexerIntakeButton .whileHeld  (intakeCommand);
        ejectButton         .whileHeld  (ejectCommand);
        feedButton          .whenPressed(feedCommand);
        climberControlButton.whenPressed(climberControl);
    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        // An ExampleCommand will run in autonomous
        return null;
    }
}
