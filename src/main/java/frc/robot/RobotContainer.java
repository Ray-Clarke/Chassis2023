// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import frc.robot.Constants.AutoConstants;
import frc.robot.Constants.OIConstants;
import frc.robot.commands.AutoElevator;
import frc.robot.commands.DriveDistance;
import frc.robot.commands.DriveRotate;
import frc.robot.commands.DriveTime;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;


/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final Drivetrain m_robotDrive = new Drivetrain();
  private final Elevator m_robotElevator = new Elevator();
  private final Intake m_robotIntake = new Intake();

  private final Command m_timedAuto = new DriveTime(1, AutoConstants.kAutoDriveSpeed, m_robotDrive);
  private final Command m_simpleAuto =
      new DriveDistance(
          AutoConstants.kAutoDriveDistanceInches, AutoConstants.kAutoDriveSpeed, m_robotDrive);
  SendableChooser<Command> m_chooser = new SendableChooser<>();
  private final Command m_rotateAuto = new DriveRotate(90, 0, m_robotDrive);
  CommandXboxController m_driverController = new CommandXboxController(0);
  CommandXboxController m_driverController2 = new CommandXboxController(1);
  private final Command m_elevatorAuto = new AutoElevator(m_robotElevator);


  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    
    configureButtonBindings();
    // Configure default commands
    // Set the default drive command to split-stick arcade drive
    m_robotDrive.setDefaultCommand(
        // A split-stick arcade command, with forward/backward controlled by the left
        // hand, and turning controlled by the right.
        new RunCommand(
            () ->
                m_robotDrive.arcadeDrive(
                    -m_driverController.getLeftY() , m_driverController.getRightX()),
            m_robotDrive));
    m_robotElevator.setDefaultCommand(
      new RunCommand(
        () ->
            m_robotElevator.powerMotor(m_driverController.getRightY()),
            m_robotElevator));
    m_robotIntake.setDefaultCommand(
      new RunCommand(
        () ->
            m_robotIntake.powerIntake(m_driverController.getLeftX()),
            m_robotIntake));


    m_chooser.setDefaultOption("Drive Distance", m_simpleAuto);
    m_chooser.addOption("Drive Time", m_timedAuto);
    m_chooser.addOption("Drive Rotate", m_rotateAuto);
    m_chooser.addOption("Timed Elevator", m_elevatorAuto);

    Shuffleboard.getTab("Autonomous").add(m_chooser);
  }


  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

  }
  
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_chooser.getSelected();
  }

}
