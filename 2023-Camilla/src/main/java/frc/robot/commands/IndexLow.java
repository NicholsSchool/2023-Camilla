// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.RobotMap;

public class IndexLow extends CommandBase {
  /** Creates a new IndexLow. */
  public IndexLow() {
    // Use addRequirements() here to declare subsystem dependencies.

    addRequirements( RobotContainer.lowerIndexer );
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    RobotContainer.lowerIndexer.move( RobotMap.LOWER_INDEXER_SPEED );

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
      RobotContainer.lowerIndexer.stop(); 

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
