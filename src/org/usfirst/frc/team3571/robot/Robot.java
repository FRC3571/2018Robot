/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3571.robot;

import edu.wpi.first.wpilibj.Compressor;
//import edu.wpi.first.wpilibj.AnalogGyro;
//import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.IterativeRobot;
//import edu.wpi.first.wpilibj.Spark;
//import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
//import edu.wpi.first.wpilibj.interfaces.Gyro;
//import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team3571.robot.commands.Autonomous;
import org.usfirst.frc.team3571.robot.subsystems.DriveTrain;
import org.usfirst.frc.team3571.utilities.MPU6050;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	Command m_autonomousCommand;

	public static DriveTrain m_drivetrain;
	public static OI m_oi;
	
	//gyro fields
	//private AnalogGyro gyro;
	//private final int GYRO_PORT = 1;
	//private final double kAngleSetpoint = 0.0;
	//private final double kVoltsPerDegreePerSecond = 0.0128;
	//private final double kP = 0.005;
	
	//private final static byte MPU6050_ADDRESS = 0x68;
    //private final static int REGISTER_PWR_MGMT_1 = 0x6B;
    //private final static int REGISTER_GYRO = 0x43;

    //private I2C accelerometer = new I2C(I2C.Port.kOnboard, MPU6050_ADDRESS);
    //private byte[] buffer = new byte[6];
	private MPU6050 gyro = new MPU6050();

    private Compressor c = new Compressor(0);
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		//gyro
		//gyro = new AnalogGyro(GYRO_PORT);
		//gyro.setSensitivity(kVoltsPerDegreePerSecond);
		// Take the accelerometer out of sleep mode
        //gyro.write(REGISTER_PWR_MGMT_1, 0);
		
		
		// Initialize all subsystems
		m_drivetrain = new DriveTrain();
		m_oi = new OI();

		// instantiate the command used for the autonomous period
		m_autonomousCommand = new Autonomous();

		// Show what command your subsystem is running on the SmartDashboard
		SmartDashboard.putData(m_drivetrain);
		
	}

	@Override
	public void autonomousInit() {
		m_autonomousCommand.start(); // schedule the autonomous command (example)
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		log();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		m_autonomousCommand.cancel();
	}

	/**
	 * This function is called periodically during teleoperated mode.
	 */
	@Override
	public void teleopPeriodic() {
		//gyro
		//double turningValue = (kAngleSetpoint - gyro.getAngle()) * kP;
		//forums said there's a place to read roborio logs(from the default output stream)
		//System.out.println("GYRO LOG: " + turningValue);
		// Invert the direction of the turn if we are going backwards
		Scheduler.getInstance().run();
		log();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
		
		//GYRO
		// Read the gyro measurements (6 bytes)
	    gyro.readGyro();
	    // Convert the raw bytes into measurements in degrees per second
	    //double x = gyroFromBytes(buffer[0], buffer[1]);
	    //double y = gyroFromBytes(buffer[2], buffer[3]);
	    //double z = gyroFromBytes(buffer[4], buffer[5]);
	  
	    System.out.println(gyro.getX());

	    //PNEUMATICS
	    c.setClosedLoopControl(true);
	    //c.setClosedLoopControl(false);
	}
	
	/** Adapted from {@link ADXL345_I2C#accelFromBytes} 
	private double gyroFromBytes(byte first, byte second) {
	    short tempLow = (short) (first & 0xff);
	    short tempHigh = (short) ((second << 8) & 0xff00);
	    return (tempLow | tempHigh) * 0.004;
	}
*/

	/**
	 * The log method puts interesting information to the SmartDashboard.
	 */
	private void log() {
		m_drivetrain.log();
	}
}
