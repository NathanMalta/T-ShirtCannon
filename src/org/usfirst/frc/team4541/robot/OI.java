package org.usfirst.frc.team4541.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4541.robot.commands.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
//	private Joystick joy = new Joystick(0);
	/**
	 * Making Joystick and public makes it easier to make a safety trigger in other classes without
	 * creating other joystick objects
	 * Also it's static so you don't have to make another object just in case that messes with code
	 */
	Joystick joy = new Joystick(0);

	public OI() {
		// Create some buttons
		JoystickButton a_button = new JoystickButton(joy, 1);
		JoystickButton b_button = new JoystickButton(joy, 2);
		JoystickButton y_button = new JoystickButton(joy, 4);
		
		JoystickButton d_up = new JoystickButton(joy, 5);
		JoystickButton d_right = new JoystickButton(joy, 6);
		JoystickButton d_down = new JoystickButton(joy, 7);
		JoystickButton d_left = new JoystickButton(joy, 8);
		JoystickButton l2 = new JoystickButton(joy, 9);
		JoystickButton r2 = new JoystickButton(joy, 10);
		JoystickButton l1 = new JoystickButton(joy, 11);
		JoystickButton r1 = new JoystickButton(joy, 12);
		
	
		a_button.whenPressed(new shootWithLights());
		b_button.whenPressed(new ToggleCompressorControlLoop());
		y_button.whenPressed(new ToggleAnglePistons());
		
		
	}

	public Joystick getJoystick() {
		return joy;
	}
}
