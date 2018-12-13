package org.usfirst.frc.team4541.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.hal.HAL;
import edu.wpi.first.wpilibj.hal.FRCNetComm.tInstances;
import edu.wpi.first.wpilibj.hal.FRCNetComm.tResourceType;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4541.robot.Robot;
import org.usfirst.frc.team4541.robot.commands.TankDriveWithJoystick;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.hal.HAL;
import edu.wpi.first.wpilibj.hal.FRCNetComm.tInstances;
import edu.wpi.first.wpilibj.hal.FRCNetComm.tResourceType;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4541.robot.Robot;
import org.usfirst.frc.team4541.robot.commands.TankDriveWithJoystick;

import edu.wpi.first.wpilibj.PowerDistributionPanel;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
/**
 * The DriveTrain subsystem incorporates the sensors and actuators attached to
 * the robots chassis. These include four drive motors, a left and right encoder
 * and a gyro.
 */
public class DriveTrain extends Subsystem {
	private WPI_TalonSRX frontLeftMotor = new WPI_TalonSRX(3);
	private WPI_TalonSRX rearLeftMotor = new WPI_TalonSRX(1);
	private WPI_TalonSRX frontRightMotor = new WPI_TalonSRX(4);
	private WPI_TalonSRX rearRightMotor = new WPI_TalonSRX(2);
	public static PowerDistributionPanel panel = new PowerDistributionPanel(0);

	private DifferentialCANDrive drive = new DifferentialCANDrive(frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor);

	private AnalogGyro gyro = new AnalogGyro(1);
	
	public DriveTrain() {
		super();
		
		// Encoders may measure differently in the real world and in
		// simulation. In this example the robot moves 0.042 barleycorns
		// per tick in the real world, but the simulated encoders
		// simulate 360 tick encoders. This if statement allows for the
		// real robot to handle this difference in devices.
		// Let's show everything on the LiveWindow
//		LiveWindow.addActuator("Drive Train", "Front_Left Motor", (Talon) frontLeftMotor);
//		LiveWindow.addActuator("Drive Train", "Back Left Motor", (Talon) rearLeftMotor);
//		LiveWindow.addActuator("Drive Train", "Front Right Motor", (Talon) frontRightMotor);
//		LiveWindow.addActuator("Drive Train", "Back Right Motor", (Talon) rearRightMotor);
//		LiveWindow.addSensor("Drive Train", "Gyro", gyro);
	}

	/**
	 * When no other command is running let the operator drive around using the
	 * PS3 joystick.
	 */
	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new TankDriveWithJoystick());
	}

	/**
	 * Tank style driving for the DriveTrain.
	 * 
	 * @param left
	 *            Speed in range [-1,1]
	 * @param right
	 *            Speed in range [-1,1]
	 */
	public void drive(double forward, double rotate) {
		drive.arcadeDrive(forward, rotate);
	}

	/**
	 * @param joy
	 *            The ps3 style joystick to use to drive tank style.
	 */
	public void drive(Joystick joy) {
		this.drive(joy.getRawAxis(4), -joy.getRawAxis(1));
	}


	/**
	 * @return The robots heading in degrees.
	 */
	public double getHeading() {
		return gyro.getAngle();
	}

	/**
	 * Reset the robots sensors to the zero states.
	 */
	public void reset() {
		gyro.reset();
	}

}
