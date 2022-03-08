package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.simulation.JoystickSim;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import frc.robot.commands.ClimbCom;
import frc.robot.commands.ClimbSolenoidCom;
import frc.robot.commands.DriveAuto;
import frc.robot.commands.IndexCom;
import frc.robot.commands.IntakeCom;
import frc.robot.commands.IntakeComPush;
import frc.robot.commands.IntakeSolenoidCom;
import frc.robot.commands.ShooterCom;
import frc.robot.commands.ShooterSolenoidCom;
import frc.robot.commands.TeleopDriveCommand;
import frc.robot.subsystems.ClimbSub;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.IntakeSub;
import frc.robot.subsystems.Shooter;

public class RobotContainer {
	/*
	** Drive Train +
	** Intake + 
	** Shooter +

	** Climber
	** Climber Angle
	** Addressable Led
	*/
	private DriveTrain driveTrain = new DriveTrain();
	private IntakeSub intakeSub = new IntakeSub();
	private Shooter shooter = new Shooter();
	private ClimbSub climb = new ClimbSub();
	public Joystick driverJoystick = new Joystick(Constants.JoystickConstants.driverPort);

	public DriveAuto drive = new DriveAuto(driveTrain);
	public RobotContainer() {
		driveTrain.setDefaultCommand(new TeleopDriveCommand(driverJoystick, driveTrain));
		configureButtonBindings();
	}

	private void configureButtonBindings() {
		//new JoystickButton(driverJoystick, 1).whileHeld(new IntakeCom(intakeSub));
		new JoystickButton(driverJoystick, 2).whileHeld(new IntakeComPush(intakeSub , true));
		new JoystickButton(driverJoystick, 1).whileHeld(new IntakeComPush(intakeSub, false));
		new JoystickButton(driverJoystick, 7).whileHeld(new IntakeSolenoidCom(intakeSub));
		//new JoystickButton(driverJoystick, 3).whileHeld(new ShooterCom(shooter)); 
		//new JoystickButton(driverJoystick, 12).whileHeld(new ShooterSolenoidCom(shooter)); 
		//new POVButton(driverJoystick, 180).whileHeld(new IndexCom(shooter));
		new JoystickButton(driverJoystick, 8).whenPressed(() -> driveTrain.changeSlowMode());
		//new JoystickButton(driverJoystick, 7).whileHeld(new ClimbCom(climb, true)); 
		//new JoystickButton(driverJoystick, 8).whileHeld(new ClimbCom(climb, false));  
		
		//new JoystickButton(driverJoystick, 7).whileHeld(new ClimbCom(climb, true)); 
		//new JoystickButton(driverJoystick, 8).whileHeld(new ClimbCom(climb, false)); 
		new JoystickButton(driverJoystick, 3).whileHeld(new ClimbSolenoidCom(climb, true));
		new JoystickButton(driverJoystick, 4).whileHeld(new ClimbSolenoidCom(climb, false));
		//new POVButton(driverJoystick, 270).whenPressed(() -> intakeSub.disablecomp());
		//new POVButton(driverJoystick, 90).whenPressed(() -> intakeSub.activeComp());

		new JoystickButton(driverJoystick, 6).whileHeld(new ClimbCom(climb, true));
		new JoystickButton(driverJoystick, 5).whileHeld(new ClimbCom(climb, false));
	}

	public Command getAutonomousCommand() {
		return drive;
		//return null;
	}
}


