package org.usfirst.frc.team4541.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class TShirtSolenoid extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	Solenoid sol;
	DigitalOutput lights;
	
	public TShirtSolenoid() {
		sol = new Solenoid(5, 1);
		lights = new DigitalOutput(0);
		lights.set(true);
	}
    public void initDefaultCommand() {
    	
	}

	public void setSolenoidOpen(boolean state) {
		sol.set(state);
	}

	public boolean getSolenoidState() {
		return sol.get();
	}

	public void lightStart() {
		lights.set(false);
	}
	
	public void lightEnd() {
		lights.set(true);
	}
}


