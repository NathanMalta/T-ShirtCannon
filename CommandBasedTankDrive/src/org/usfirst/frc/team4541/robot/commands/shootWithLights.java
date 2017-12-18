package org.usfirst.frc.team4541.robot.commands;

import org.usfirst.frc.team4541.robot.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class shootWithLights extends CommandGroup {

	public shootWithLights() {
		addSequential(new triggerLights());
		addSequential(new shootShirt());
	}

}
