package org.usfirst.frc.team4541.robot.commands;

import org.usfirst.frc.team4541.robot.Robot;
import org.usfirst.frc.team4541.robot.OI;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Joystick;

public class triggerLights extends Command {

	
	public triggerLights() {
		requires(Robot.launcher);

	}

	// Called just before this Command runs the first time
	protected void initialize() {
		if (Robot.oi.getJoystick().getRawAxis(2) > 0.8 && Robot.oi.getJoystick().getRawAxis(3) > 0.8) {
		setTimeout(.6);
		Robot.launcher.lightStart();	
		}
		else {
			setTimeout(.1);
		}
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return isTimedOut();
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.launcher.lightEnd();
	}
}
		