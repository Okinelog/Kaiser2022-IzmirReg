// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ClimbSub extends SubsystemBase {
  /** Creates a new ClimbSub. */
  CANSparkMax climb;
  Solenoid climbSolenoid;
  Solenoid climbSolenoid2;
  public ClimbSub() {
    climb = new CANSparkMax(Constants.ClimbConstants.climbPort, MotorType.kBrushless);
    climbSolenoid = new Solenoid(PneumaticsModuleType.CTREPCM, Constants.ClimbConstants.climbSolenoidPort);
    climbSolenoid2 = new Solenoid(PneumaticsModuleType.CTREPCM, Constants.ClimbConstants.climbSolenoidPort2);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void climbActive(){
    climb.set(Constants.ClimbConstants.climbSpeed);
  }
  public void climbDisable(){
    climb.set(Constants.ClimbConstants.invertClimbSpeed);
  }
  public void climbStop(){
    climb.stopMotor();
  }
  public void climbSolenoidActive(){
    climbSolenoid.set(true);
    climbSolenoid2.set(false);
  }
  public void climbSolenoidDisable(){
    climbSolenoid.set(false);
    climbSolenoid2.set(true);
  }
}
