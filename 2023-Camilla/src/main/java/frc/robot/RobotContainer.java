// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.commands.IndexLow;
import frc.robot.commands.IndexUpper;
import frc.robot.commands.ReverseIndexUpper;
import frc.robot.commands.ShootHigh;
import frc.robot.commands.ShootLow;
import frc.robot.commands.TakeIn;
import frc.robot.commands.TakeOut;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.LowerIndexer;
import frc.robot.subsystems.IntakePistons;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.ShooterPistons;
import frc.robot.subsystems.UpperIndexer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;



/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...



  public static DriveTrain driveTrain;

  public static Shooter shooter; 
  public static LowerIndexer lowerIndexer; 
  public static UpperIndexer upperIndexer; 

  public static Intake intake;
  public static IntakePistons intakePistons; 
  public static ShooterPistons shooterPistons;
  
  public static CommandXboxController driverOI;
  public static CommandXboxController operatorOI;
  public static XboxController driverRumbler;
  public static XboxController operatorRumbler;
  


  Compressor compressor;

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    driveTrain = new DriveTrain();

    shooter = new Shooter(); 
    intake = new Intake(); 
    lowerIndexer = new LowerIndexer(); 
    upperIndexer = new UpperIndexer(); 
    

    compressor = new Compressor(PneumaticsModuleType.CTREPCM); 
    intakePistons = new IntakePistons(); 
    shooterPistons = new ShooterPistons(); 

    driverOI = new CommandXboxController(0);
    driverRumbler = new XboxController(0);
    operatorOI = new CommandXboxController(1);
    operatorRumbler = new XboxController(1);
    


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

    driveTrain.setDefaultCommand(
      new RunCommand(
          () -> driveTrain.arcadeDrive(
            -MathUtil.applyDeadband(driverOI.getLeftY() * 0.75, 0.05),
            -MathUtil.applyDeadband(driverOI.getRightX() * 0.50, 0.05)), driveTrain));

    driverOI.rightTrigger().onTrue(new InstantCommand( () -> intakePistons.extend() ) );
    driverOI.rightTrigger().whileTrue(new TakeIn().raceWith(new IndexLow()) );
    driverOI.rightTrigger().onFalse(new InstantCommand( () -> intakePistons.retract() ) );

    driverOI.leftTrigger().onTrue(new InstantCommand( () -> intakePistons.extend() ) );
    driverOI.leftTrigger().whileTrue(new TakeOut().raceWith(new IndexLow() ) );
    driverOI.leftTrigger().onFalse(new InstantCommand( () -> intakePistons.retract() ) );

    operatorOI.leftBumper().whileTrue( new IndexLow() );
    operatorOI.rightBumper().whileTrue(new IndexUpper() );
    operatorOI.x().whileTrue(new ReverseIndexUpper() );

    operatorOI.a().onTrue( new InstantCommand( () -> shooterPistons.toggle() ) );

    operatorOI.rightTrigger().whileTrue( new ShootHigh() );
    operatorOI.leftTrigger().whileTrue( new ShootLow() );




    // driveTrain.setDefaultCommand( new Drive() ); +

    // j1.b1.whenPressed( new InstantCommand( () -> pistons.extend() ) ); +
    // j1.b1.whileHeld(new TakeIn() ); +
    // j1.b1.whenReleased( new InstantCommand( () -> pistons.retract() ) ); +

    // j0.b1.whenPressed( new InstantCommand( () -> pistons.extend() ) ); +
    // j0.b1.whileHeld( new TakeOut() ); +
    // j0.b1.whenReleased( new InstantCommand( () -> pistons.retract() ) ); +

    // c0.lBumper.whileHeld( new IndexLow() ); +
    // c0.rBumper.whileHeld(new IndexUpper()); +
    // c0.x.whileHeld(new RevIndexUpper() ); +

    // c0.a.whenPressed( new InstantCommand( () -> shooterPistons.toggle() ) ); +

    // c0.rTrigger.whileHeld( new ShootHigh() );
    // c0.lTrigger.whileHeld( new ShootLow() ); 
    


  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return null; 
  }
}
