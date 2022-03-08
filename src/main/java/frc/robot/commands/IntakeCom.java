// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSub;

public class IntakeCom extends CommandBase {
  /** Creates a new IntakeCom. */
  IntakeSub intake_Sub;
  public IntakeCom(IntakeSub mIntakeSub) {
    intake_Sub = mIntakeSub;
    addRequirements(intake_Sub);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    intake_Sub.changeIntake();
  }

  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    intake_Sub.changeIntake();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
