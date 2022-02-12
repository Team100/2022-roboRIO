// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.climber.ClimberTilt;
import frc.robot.subsystems.climber.ClimberWinch;
import frc.robot.commands.Drive;

import frc.robot.commands.climb.tilt.TiltBackward;
import frc.robot.commands.climb.tilt.TiltForward;
import frc.robot.commands.climb.tilt.TiltStop;
import frc.robot.commands.climb.winch.WinchIn;
import frc.robot.commands.climb.winch.WinchOut;
import frc.robot.commands.climb.winch.WinchStop;

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
  private final ClimberTilt climberTilt = new ClimberTilt();
  private final ClimberWinch climberWinch = new ClimberWinch();


  // OI Devices
  private final Joystick leftJoystick = new Joystick(0);
  private final Joystick rightJoystick = new Joystick(1);
  public final Joystick gamepad = new Joystick(2);

  public JoystickButton tiltForwardButton;
  public JoystickButton tiltBackwardButton;
  public JoystickButton winchInButton;
  public JoystickButton winchOutButton;

  // Commands
  private final Drive driveCommand = new Drive(drivetrain, leftJoystick, rightJoystick);

  private final TiltStop tiltStop = new TiltStop(climberTilt);
  private final TiltForward tiltForward = new TiltForward(climberTilt);
  private final TiltBackward tiltBackward = new TiltBackward(climberTilt);

  private final WinchStop winchStop = new WinchStop(climberWinch, gamepad);
  //private final WinchIn winchIn = new WinchIn(climberWinch);
  //private final WinchOut winchOut = new WinchOut(climberWinch);
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Set default commands
    drivetrain.setDefaultCommand(driveCommand);
    climberWinch.setDefaultCommand(winchStop);
    climberTilt.setDefaultCommand(tiltStop);


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
    tiltForwardButton = new JoystickButton(gamepad, 1);
    tiltForwardButton.whileHeld(tiltForward);
    tiltBackwardButton = new JoystickButton(gamepad, 2);
    tiltBackwardButton.whileHeld(tiltBackward);
    // winchInButton = new JoystickButton(gamepad, 3);
    // winchInButton.whileHeld(winchIn);
    // winchOutButton = new JoystickButton(gamepad, 4);
    // winchOutButton.whileHeld(winchOut);


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
