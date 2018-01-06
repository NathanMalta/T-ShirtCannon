package org.usfirst.frc.team4541.robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4541.robot.subsystems.AnglePistons;
//import org.usfirst.frc.team4541.robot.subsystems.AnglePistons;
import org.usfirst.frc.team4541.robot.subsystems.CompressorSystem;
import org.usfirst.frc.team4541.robot.subsystems.DriveTrain;
import org.usfirst.frc.team4541.robot.subsystems.TShirtSolenoid;

//import com.ctre.CANTalon;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	public static OI oi;
	public static DriveTrain drivetrain;
	public static TShirtSolenoid launcher;
	public static CompressorSystem compressor;
	public static AnglePistons anglePistons;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		// Initialize all subsystems
		drivetrain = new DriveTrain(); 
		launcher   = new TShirtSolenoid();
		compressor = new CompressorSystem();
		anglePistons = new AnglePistons();
		oi =  new OI();
		CameraServer.getInstance().startAutomaticCapture(0);
		ArduinoDueInterface.init();
		

		// Show what command your subsystem is running on the SmartDashboard
//		SmartDashboard.putData(drivetrain);

	}

	@Override
	public void autonomousInit() {
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
	}

	@Override
	public void teleopInit() {
		
		compressor.setCompressorState(false);  //Disable closed control loop control on telepo init
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		log();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
	}

	/**
	 * The log method puts interesting information to the SmartDashboard.
	 */
	private void log() {
//		ArduinoDueInterface.updateUAvg(3);
		SmartDashboard.putBoolean("compressor close loop: ", compressor.getCompressorClosedLoopState());
		SmartDashboard.putBoolean("compressor on: ", compressor.getCompressorState());
		SmartDashboard.putBoolean("cannon state: ", anglePistons.getSolenoidState());
		SmartDashboard.putNumber("Total AMPS: ", DriveTrain.panel.getTotalCurrent());
		SmartDashboard.putNumber("Total Joules", DriveTrain.panel.getTotalEnergy());
		SmartDashboard.putNumber("Pressure Transducer Voltage", CompressorSystem.pressureTransducer.getVoltage());
		SmartDashboard.putNumber("Pressure Transducer Pressure", 250*(CompressorSystem.pressureTransducer.getVoltage()/4.5977)-25);

		SmartDashboard.putNumber("U1", ArduinoDueInterface.getUltrasonic(1));
		SmartDashboard.putNumber("U2", ArduinoDueInterface.getUltrasonic(2));
		SmartDashboard.putNumber("U3", ArduinoDueInterface.getUltrasonic(3));
		SmartDashboard.putNumber("U4", ArduinoDueInterface.getUltrasonic(4));
		
		SmartDashboard.putNumber("U3 AVG", ArduinoDueInterface.getUltrasonicAvg());
		
		SmartDashboard.putBoolean("isDueOnline:", ArduinoDueInterface.isDueOnline());

	}
}
