package org.usfirst.frc.team4541.robot.commands;

import org.usfirst.frc.team4541.robot.Robot;
import org.usfirst.frc.team4541.robot.OI;
import edu.wpi.first.wpilibj.Joystick;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class shootShirt extends Command {

	public shootShirt() {
		requires(Robot.launcher);

	}

	// Called just before this Command runs the first time
	protected void initialize() {
//		setTimeout(.1);
		if (Robot.oi.getJoystick().getRawAxis(2) > 0.8 && Robot.oi.getJoystick().getRawAxis(3) > 0.8) {
			Robot.launcher.setSolenoidOpen(true);
			System.out.println("OPENED");
			setTimeout(.3);
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
		Robot.launcher.setSolenoidOpen(false);
	}
}
