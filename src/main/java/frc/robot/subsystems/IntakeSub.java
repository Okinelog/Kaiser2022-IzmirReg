// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakeSub extends SubsystemBase {
	WPI_VictorSPX intake;
	Solenoid intakeSolenoid;
	Solenoid intakeSolenoid2;
	Compressor comp;
	boolean startEngine = false;
	boolean startSole = false;

	boolean a = false;
	public IntakeSub() {
		comp = new Compressor(0, PneumaticsModuleType.CTREPCM);
		intake = new WPI_VictorSPX(Constants.IntakeConstants.intakePort);
		intake.setSafetyEnabled(Constants.IntakeConstants.intakeSafety);
		intake.setNeutralMode(Constants.IntakeConstants.intakeBrake);
		intake.setInverted(Constants.IntakeConstants.intakeInvert);

		intakeSolenoid = new Solenoid(PneumaticsModuleType.CTREPCM, Constants.IntakeConstants.intakeSolenoidPort);
		intakeSolenoid2 = new Solenoid(PneumaticsModuleType.CTREPCM, Constants.IntakeConstants.intakeSolenoidPort2);
	}

	public void disablecomp(){
		comp.disable();
	}

	public void activeComp(){
		comp.enableDigital();
	}
	@Override
	public void periodic() {
		System.out.println(startSole);
		/*if (startEngine == true) {
			activeIntake();
		} else {
			disableIntake();
		}*/
		if(startSole == true){
			activeIntakeSolenoid();
		}
		else{
			disableIntakeSolenoid();
		}
	}

	public void changeIntake(){
		startEngine = !startEngine;
	}
	public void changeSolenoid(){
		startSole = !startSole;
	}
	public void activeIntake(){
		intake.set(ControlMode.PercentOutput, Constants.IntakeConstants.intakeSpeed);
	}
	public void InvertIntake(){
		intake.set(ControlMode.PercentOutput, -Constants.IntakeConstants.intakeSpeed);
	}
	/*public void activeInvertIntake(){
		startEngine = true;
		intake.set(ControlMode.PercentOutput, Constants.IntakeConstants.intakeInvertSpeed);
	}*/
	public void disableIntake(){
		intake.stopMotor();
	}
	public void activeIntakeSolenoid(){
		intakeSolenoid.set(true);
		intakeSolenoid2.set(false);
		//doubleIntakeSolenoid.set(Value.kReverse);
	}
	public void disableIntakeSolenoid(){
		intakeSolenoid.set(false);
		intakeSolenoid2.set(true);
		//doubleIntakeSolenoid.set(Value.kOff);
	}
}
