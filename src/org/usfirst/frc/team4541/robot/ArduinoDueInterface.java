package org.usfirst.frc.team4541.robot;

import java.text.DecimalFormat;
import java.util.Arrays;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.filters.LinearDigitalFilter;
import edu.wpi.first.wpilibj.hal.DIOJNI;
import edu.wpi.first.wpilibj.hal.RelayJNI;

//The class that handles SPI communication between arduino due and rio
public class ArduinoDueInterface {
	private static I2C i2c;
	private static PIDSource source = new PIDSource() {

		@Override
		public void setPIDSourceType(PIDSourceType pidSource) {

		}

		@Override
		public PIDSourceType getPIDSourceType() {
			return PIDSourceType.kDisplacement;
		}

		@Override
		public double pidGet() {
			return getUltrasonic(2);
		}

	};
	private static LinearDigitalFilter filter = LinearDigitalFilter.movingAverage(source, 20);

	public static void init() {
		i2c = new I2C(I2C.Port.kOnboard, 4);
	}

	public static double getUltrasonic(int i) {
		try {
			int[] intArr = { 255, 255, 255 };
			byte[] byteArr = { (byte) i };
			byte[] recievedData = { 0x00, 0x00 };
			i2c.transaction(byteArr, byteArr.length, recievedData, recievedData.length);
			// Timer.delay(.01);
			return normalizeBytes(recievedData) / 10;
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
			return -1;
		}

	}

	public static boolean isDueOnline() {
		int[] intArr = { 255, 255, 255 };
		byte[] byteArr = { (byte) 5 };
		byte[] recievedData = { 0x00, 0x00 };
		i2c.transaction(byteArr, byteArr.length, recievedData, recievedData.length);
		if (recievedData != null && recievedData.length > 0) {
			System.out.println(recievedData[0]);
			return recievedData[0] == -1 && recievedData[1] == 0;
		} else {
			return false;
		}
	}

	public static double normalizeBytes(byte[] b) {
		int distance = (b[0] & 0xFF) <<	 8;
		distance = distance | (b[1] & 0xFF);

		double distanceFloat = (double) (distance);

		distanceFloat = Double.valueOf((new DecimalFormat(".#")).format(distanceFloat)); // A
																							// very
																							// roundabout
																							// way
																							// to
																							// round
																							// to
																							// one
																							// decimal
																							// place
		return distanceFloat;
	}

	// static Double[] avgList = new Double[4];
	// public static void updateUAvg(int value) {
	// for (int i = avgList.length; i < 1; i--) {
	// avgList[i-1] = avgList[i];
	// }
	// avgList[0] = getUltrasonic(value);
	// }
	public static double getUltrasonicAvg() {

		return filter.pidGet();
	}

}
