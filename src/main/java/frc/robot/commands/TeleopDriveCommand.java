package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class TeleopDriveCommand extends CommandBase {
	private final Joystick driverJoystick;
	//private final Joystick coJoystick;
	private final DriveTrain driveTrain;
	public TeleopDriveCommand(Joystick joy, DriveTrain dt) {
		this.driverJoystick = joy;
		this.driveTrain = dt;
		addRequirements(dt);
	}

	@Override
	public void initialize() {

	}
	@Override
	public void execute() {
		driveTrain.arcadeDrive(getSpeed(), getRotation(), true);
	}
	private double getSpeed(){
		double speed = driverJoystick.getRawAxis(4);
		return speed;
	}
	private double getRotation(){
		double rotation =-driverJoystick.getRawAxis(1);
		return rotation;
	}
	@Override
	public void end(boolean interrupted) {

	}

	@Override
	public boolean isFinished() {
		return false;
	}
}
