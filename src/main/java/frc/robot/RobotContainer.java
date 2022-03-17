// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import javax.sql.rowset.spi.SyncProvider;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.ScheduleCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.*;
import frc.robot.commands.drivetrain.*;
import frc.robot.commands.intake.*;
import frc.robot.commands.indexer.*;
import frc.robot.commands.autonomous.AutonProcedure;
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
    private final Symphony symphony = new Symphony();


    // OI Devices
    private final Joystick leftJoystick = new Joystick(0);
    private final Joystick rightJoystick = new Joystick(1);
    private final Joystick gamepad = new Joystick(2);
    //private final JoystickButton indexButton = new JoystickButton(leftJoystick, 1);
    private final Joystick buttonBoard = new Joystick(3);
    private final JoystickButton turboButton = new JoystickButton(rightJoystick, 1);
    private final JoystickButton slowButton = new JoystickButton(rightJoystick, 3);
    private final JoystickButton intakeButton = new JoystickButton(buttonBoard, 2);
    private final JoystickButton ejectButton = new JoystickButton(buttonBoard, 5);

    private final JoystickButton alignButton = new JoystickButton(leftJoystick, 1);
    //private final JoystickButton shootButton = new JoystickButton(buttonBoard, 16);
    //private final JoystickButton indexerIntakeButton = new JoystickButton(buttonBoard, 12);
    private final JoystickButton shootHighButton = new JoystickButton(buttonBoard, 14);
    private final JoystickButton shootLowButton = new JoystickButton(buttonBoard, 13);

    private final JoystickButton mediaControlButton = new JoystickButton(buttonBoard, 12);

    private final JoystickButton HookDownButton = new JoystickButton(buttonBoard, 1);
    private final JoystickButton HookUpButton = new JoystickButton(buttonBoard, 16);

    private final JoystickButton stopAll = new JoystickButton(buttonBoard, 4);

    private final JoystickButton fixClimberButton = new JoystickButton(rightJoystick, 7);

    // private final JoystickButton runPivot = new JoystickButton(gamepad, 1);
    //private final JoystickButton controlBallButton = new JoystickButton(buttonBoard, 14);

    // Commands
    private final Drive driveCommand = new Drive(drivetrain, leftJoystick, rightJoystick);
    private final DriveFurious driveFuriousCommand = new DriveFurious(drivetrain, leftJoystick, rightJoystick);
    private final DriveSlow driveSlowCommand = new DriveSlow(drivetrain, leftJoystick, rightJoystick);
    private final AlignClimber alignCommand = new AlignClimber(drivetrain);
    private final IntakeIntake intakeIntakeCommand = new IntakeIntake(intake);
    private final IntakeEject intakeEjectCommand = new IntakeEject(intake);
    private final BetterIntakeStop intakeStopCommand = new BetterIntakeStop(intake);
    private final ShootHigh shootHighCommand = new ShootHigh(shooter);
    private final ShootLow shootLowCommand = new ShootLow(shooter);
    private final ShootEject shootEjectCommand = new ShootEject(shooter);
    private final ShootStop shootStopCommand = new ShootStop(shooter);
    private final IndexerStop indexerStopCommand = new IndexerStop(indexer);
    private final BetterIndexerIntake intakeCommand = new BetterIndexerIntake(indexer);
    private final IndexerEject indexerEjectCommand = new IndexerEject(indexer, intake);
    private final IndexerFeedHigh feedHighCommand = new IndexerFeedHigh(indexer, shooter);
    private final IndexerFeedLow feedLowCommand = new IndexerFeedLow(indexer, shooter);
    private final ClimberStop climberStopCommand = new ClimberStop(climber);

    private final HookUp HookUpCommand = new HookUp(climber);
    private final HookDown HookDownCommand = new HookDown(climber, symphony);    
    private final HookZero HookZeroCommand = new HookZero(climber);

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
        turboButton.whileHeld(driveFuriousCommand);
        alignButton.whileHeld(alignCommand);
        HookDownButton.whenPressed(HookDownCommand);
        HookUpButton.whenPressed(HookUpCommand);
        slowButton.whileHeld(driveSlowCommand);
        stopAll.whenPressed(new ParallelCommandGroup(new ClimberStop(climber), new IndexerStop(indexer), new BetterIntakeStop(intake), new ShootStop(shooter)));
        intakeButton.whenPressed(new SequentialCommandGroup(new ParallelDeadlineGroup(intakeCommand, intakeIntakeCommand), new WaitCommand(0.2)));
        shootHighButton.whileHeld(new ParallelCommandGroup(shootHighCommand, feedHighCommand));
        shootLowButton.whileHeld(new ParallelCommandGroup(shootLowCommand, feedLowCommand));
        ejectButton.whileHeld(new ParallelCommandGroup(intakeEjectCommand, indexerEjectCommand, shootEjectCommand));

        mediaControlButton.whenPressed(new InstantCommand(() -> { symphony.play();}, symphony));

        fixClimberButton.whileHeld(HookZeroCommand);
        // runPivot.whileHeld(new InstantCommand(() -> { intake.runPivot(0.1); System.out.println("running the thing");}, intake));
    }

    public void onInit() {
        // intake.onInit();
        climber.onInit();
    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        // An ExampleCommand will run in autonomous
        //return null;
        return new AutonProcedure(drivetrain, intake, indexer, shooter);
    }
}
