// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.drivetrain.Drive;
import frc.robot.commands.drivetrain.AlignClimber;
import frc.robot.commands.drivetrain.DriveFurious;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.commands.intake.IntakeIntake;
import frc.robot.commands.intake.IntakeStop;

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
    private final Intake intake = new Intake();

    // OI Devices
    private final Joystick leftJoystick = new Joystick(0);
    private final Joystick rightJoystick = new Joystick(1);
    private final Joystick gamepad = new Joystick(2);

    public JoystickButton turboButton;
    public JoystickButton intakeIntakeButton;
    public JoystickButton intakeDownButton;
    public JoystickButton alignButton;

    // Commands
    private final Drive driveCommand = new Drive(drivetrain, leftJoystick, rightJoystick);
    private final DriveFurious driveFuriousCommand = new DriveFurious(drivetrain, leftJoystick, rightJoystick);
    private final AlignClimber alignCommand = new AlignClimber(drivetrain);
    private final IntakeIntake intakeIntakeCommand = new IntakeIntake(intake);
    private final IntakeStop intakeStopCommand = new IntakeStop(intake);

    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer() {
        // Set default commands
        drivetrain.setDefaultCommand(driveCommand);
        intake.setDefaultCommand(intakeStopCommand);

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
        turboButton = new JoystickButton(rightJoystick, 1);
        turboButton.whileHeld(driveFuriousCommand);
        
        intakeIntakeButton = new JoystickButton(leftJoystick, 3);
        intakeIntakeButton.whileHeld(intakeIntakeCommand);
        
        alignButton = new JoystickButton(gamepad, 7);
        alignButton.whileHeld(alignCommand);
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
