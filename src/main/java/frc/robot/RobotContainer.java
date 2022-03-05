// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ScheduleCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
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
    private final Drivetrain drivetrain = new Drivetrain();
    private final Climber climber = new Climber();
    private final Shooter shooter = new Shooter();
    private final Intake intake = new Intake();
    private final Indexer indexer = new Indexer();

    // OI Devices
    private final Joystick leftJoystick = new Joystick(0);
    private final Joystick rightJoystick = new Joystick(1);
    private final Joystick gamepad = new Joystick(2);
    private final Joystick buttonBoard = new Joystick(3);
    private final JoystickButton turboButton = new JoystickButton(rightJoystick, 1);
    private final JoystickButton intakeIntakeButton = new JoystickButton(buttonBoard, 2);
    private final JoystickButton intakeEjectButton = new JoystickButton(buttonBoard, 5);
    private final JoystickButton alignButton = new JoystickButton(gamepad, 7);
    private final JoystickButton shootButton = new JoystickButton(buttonBoard, 16);
    private final JoystickButton indexerIntakeButton = new JoystickButton(buttonBoard, 12);
    private final JoystickButton ejectButton = new JoystickButton(buttonBoard, 4);
    private final JoystickButton feedButton = new JoystickButton(buttonBoard, 15);
    private final JoystickButton climberControlButton = new JoystickButton(gamepad, 3);
    private final JoystickButton intakePivotDown = new JoystickButton(buttonBoard, 13);
    private final JoystickButton intakePivotUp = new JoystickButton(buttonBoard, 14);
    //private final JoystickButton controlBallButton = new JoystickButton(buttonBoard, 14);

    // Commands
    private final Drive driveCommand = new Drive(drivetrain, leftJoystick, rightJoystick);
    private final DriveFurious driveFuriousCommand = new DriveFurious(drivetrain, leftJoystick, rightJoystick);
    private final AlignClimber alignCommand = new AlignClimber(drivetrain);
    private final IntakeIntake intakeIntakeCommand = new IntakeIntake(intake);
    private final IntakeEject intakeEjectCommand = new IntakeEject(intake);
    private final IntakeStop intakeStopCommand = new IntakeStop(intake);
    private final Shoot shootCommand = new Shoot(shooter);
    private final ShootStop shootStopCommand = new ShootStop(shooter);
    private final IndexerStop indexerStopCommand = new IndexerStop(indexer);
    private final IndexerIntake intakeCommand = new IndexerIntake(indexer);
    private final IndexerEject ejectCommand = new IndexerEject(indexer, intake);
    private final IndexerFeed feedCommand = new IndexerFeed(indexer);
    private final ClimberStop climberStopCommand = new ClimberStop(climber);
    private final ClimberControl climberControl = new ClimberControl(climber, gamepad);
    //private final ParallelCommandGroup controlBall = new ParallelCommandGroup(intakeIntakeCommand,indexerStopCommand);


    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer() {
        // Set default commands
        drivetrain.setDefaultCommand(driveCommand);
        climber.setDefaultCommand(climberStopCommand);
        indexer.setDefaultCommand(indexerStopCommand);
        intake.setDefaultCommand(intakeStopCommand);
        shooter.setDefaultCommand(shootStopCommand);


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
        shootButton.whileHeld(shootCommand);
        turboButton.whileHeld(driveFuriousCommand);
        intakeIntakeButton.whileHeld(intakeIntakeCommand);
        intakeEjectButton.whileHeld(intakeEjectCommand);
        alignButton.whileHeld(alignCommand);
        indexerIntakeButton.whenPressed(new ScheduleCommand(new SequentialCommandGroup(intakeCommand, new WaitCommand(0.3))));
        ejectButton.whileHeld(ejectCommand);
        feedButton.whenPressed(feedCommand);
        climberControlButton.whenPressed(climberControl);
        //controlBallButton.whileHeld(controlBall);

        intakePivotDown.whileHeld());
        // intakePivotUp.whileHeld(new InstantCommand(() -> { intake.runPivot(-0.2); }, intake));
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
