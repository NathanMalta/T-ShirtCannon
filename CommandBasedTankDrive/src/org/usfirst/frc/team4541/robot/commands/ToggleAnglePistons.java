package org.usfirst.frc.team4541.robot.commands;

import org.usfirst.frc.team4541.robot.Robot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;

import org.usfirst.frc.team4541.robot.subsystems.AnglePistons;

/**
 *
 */
public class ToggleAnglePistons extends Command {
	//boolean isFinished = false;
	public ToggleAnglePistons() {
		requires(Robot.anglePistons);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.anglePistons.setSolenoidOpen(!Robot.anglePistons.getSolenoidState());
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
//		if (Robot.oi.getJoystick().getRawAxis(2) >= 0.9) {
			//Timer.delay(1);
			//isFinished = true;
//		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return true;
	}

	// Called once after isFinished returns true
	protected void end() {
	}
}