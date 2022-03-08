// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {
  CANSparkMax shooter;
  Solenoid solenoid;
  boolean startEngine = false;
  boolean changeSolenoidStatus = false;

  WPI_VictorSPX indexer;

  public Shooter() {
    shooter = new CANSparkMax(Constants.ShooterConstants.shooterPort, MotorType.kBrushless);
    solenoid = new Solenoid(PneumaticsModuleType.CTREPCM, Constants.ShooterConstants.shooterSolenoidPort);
    indexer = new WPI_VictorSPX(Constants.IndexConstants.indexerPort);
  }

  @Override
  public void periodic() {
    if(startEngine){
      activeShooter();
    }
    else{
      disableShooter();
    }
    if(changeSolenoidStatus){
      activeSolenoid();
    }
    else{
      disableSolenoid();
    }
  }
  public void startIndex(){
    indexer.set(Constants.IndexConstants.indexerSpeed);
  }
  public void stopIndex(){
    indexer.stopMotor();
  }
  public void changeShooter(){
    startEngine = !startEngine;
  }
  public void changeSolenoid(){
    changeSolenoidStatus = !changeSolenoidStatus;
  }
  public void activeSolenoid(){
    solenoid.set(true);
  }
  public void disableSolenoid(){
    solenoid.set(false);
  }
  public void activeShooter() {
    shooter.set(Constants.ShooterConstants.shooterSpeed);
  }
  public void disableShooter(){
    shooter.stopMotor();
  }
}
