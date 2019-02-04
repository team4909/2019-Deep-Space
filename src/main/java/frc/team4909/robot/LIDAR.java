// /* Commented code is old, uses communication directly with Rio */

// package frc.team4909.robot;

// import java.util.TimerTask;
// import edu.wpi.first.wpilibj.Timer;
// import edu.wpi.first.wpilibj.I2C.Port;
// import edu.wpi.first.wpilibj.PIDSource;
// import edu.wpi.first.wpilibj.PIDSourceType;
// import java.io.IOException;
// import edu.wpi.first.wpilibj.TimedRobot;
// import edu.wpi.first.wpilibj.DriverStation;
// import edu.wpi.first.wpilibj.I2C;
// import java.util.logging.Level;
// import java.util.logging.Logger;
// import edu.wpi.first.wpilibj.command.Subsystem;
// import java.io.IOException;
// import edu.wpi.first.wpilibj.SerialPort;
// import edu.wpi.first.wpilibj.Counter;
// import edu.wpi.first.wpilibj.DigitalSource;

// public class LIDAR {

// 	//public static I2C arduino = new I2C(I2C.Port.kMXP, 1); // Creates I2C Bus - Should this be kOnBoard or MXP?
// 	//public static SerialPort serialPort = new SerialPort(9600, SerialPort.Port.kUSB1);
// 	private static final int CALIBRATION_OFFSET = -18;
// 	private Counter counter;
// 	private int printedWarningCount = 5;

// 	public LidarLitePWM(DigitalSource source) {
// 		counter = new Counter(source);
// 		counter.setMaxPeriod(1.0);
// 		counter.setSemiPeriodMode(true);
// 		counter.reset();
// 	}

// 	public double getDistance() { // Method to find LIDAR distance reading at any given time

// 		byte[] bytes = new byte[2]; // Creates an array of length 2 to hold the two bytes
// 		// Code for Serial
// 		//bytes[0] = serialPort.read(2)[0];
// 		//bytes[1] = serialPort.read(2)[1];

// 		// Code for I2C
// 		//boolean existence = arduino.addressOnly();
// 		//boolean connection = arduino.verifySensor(1, 2, bytes);
// 		//boolean failed = arduino.readOnly(bytes, 2);
// 		//boolean failed = arduino.read(1, 2, bytes); // Read the byte array
		
// 		System.out.println("Prints Begin");

// 		// Prints for I2C
// 		//System.out.println(existence); // Returns false if there is a device on the I2C bus that responds to the address specified in the constructor
// 		//System.out.println(connection); // Returns true if the sensor was verified to be connected
// 		//System.out.println(failed); // Returns false when we are getting actual values from the arduino
	
// 		/* Prints for Serial
// 		System.out.println(serialPort.read(2)[0]);
// 		System.out.println(serialPort.read(2)[1]);
// 		System.out.println(bytes[0]);
// 		System.out.println(bytes[1]);
// 		*/

// 		double cm;
// 		if(counter.get() < 1) {
// 			return 0;
// 		}

// 		cm = (counter.getPeriod() * 1000000.0 / 10.0) + CALIBRATION_OFFSET;
// 		System.out.println(cm);
// 		return cm;

// 		//int distance = java.nio.ByteBuffer.wrap(bytes).getInt(); // Convert the bytes to an integer value
// 		//System.out.println("Prints End");
// 		//System.out.println(distance);
// 		//return distance;
// 	}
// }
// /*
//  * public class LIDAR implements PIDSource { private I2C i2c; private byte[]
//  * distance; private java.util.Timer updater;
//  * 
//  * private final int LIDAR_ADDR = 0x62; private final int LIDAR_CONFIG_REGISTER
//  * = 0x00; private final int LIDAR_DISTANCE_REGISTER = 0x8f;
//  * 
//  * public LIDAR() { i2c = new I2C(Port.kOnboard, LIDAR_ADDR);
//  * 
//  * distance = new byte[2];
//  * 
//  * updater = new java.util.Timer(); }
//  * 
//  * // Distance in cm public int getDistance() { return (int)
//  * Integer.toUnsignedLong(distance[0] << 8) + Byte.toUnsignedInt(distance[1]); }
//  * 
//  * public double pidGet() { return getDistance(); }
//  * 
//  * // Start 10Hz polling public void start() { updater.scheduleAtFixedRate(new
//  * LIDARUpdater(), 0, 100); }
//  * 
//  * // Start polling for period in milliseconds public void start(int period) {
//  * updater.scheduleAtFixedRate(new LIDARUpdater(), 0, period); }
//  * 
//  * public void stop() { updater.cancel(); updater = new java.util.Timer(); }
//  * 
//  * // Update distance variable public void update() {
//  * i2c.write(LIDAR_CONFIG_REGISTER, 0x04); // Initiate measurement
//  * Timer.delay(0.04); // Delay for measurement to be taken
//  * i2c.read(LIDAR_DISTANCE_REGISTER, 2, distance); // Read in measurement
//  * Timer.delay(0.005); // Delay to prevent over polling }
//  * 
//  * // Timer task to keep distance updated private class LIDARUpdater extends
//  * TimerTask { public void run() { update(); /* while(true) { update(); try {
//  * Thread.sleep(10); } catch (InterruptedException e) { e.printStackTrace(); } }
//  */ /*
// 	 * } }
// 	 * 
// 	 * @Override public void setPIDSourceType(PIDSourceType pidSource) {
// 	 * 
// 	 * }
// 	 * 
// 	 * @Override public PIDSourceType getPIDSourceType() { return null; } }
// 	 */