// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSub;

public class IntakeComPush extends CommandBase {
  IntakeSub intakeSub;
  Boolean stat;
  /** Creates a new IntakeComPush. */
  public IntakeComPush(IntakeSub m, boolean a) {
    intakeSub = m;
    stat = a;
    addRequirements(intakeSub);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
//    intakeSub.changeIntake();
    if(stat){
      intakeSub.activeIntake();
    }
    else{
      intakeSub.InvertIntake();
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    intakeSub.disableIntake();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
