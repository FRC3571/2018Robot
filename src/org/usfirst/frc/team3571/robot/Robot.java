/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3571.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team3571.robot.command.Autonomous;
import org.usfirst.frc.team3571.robot.subsystems.DriveTrain;
import org.usfirst.frc.team3571.robot.utilities.MPU6050;
import org.usfirst.frc.team3571.robot.utilities.XboxController;
import org.usfirst.frc.team3571.robot.utilities.XboxController.StickSides;


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
	public static XboxController xbox;
	public MPU6050 gyro = new MPU6050();

    //private Compressor c = new Compressor(0);
	private Encoder enc = new Encoder(0,1);
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		
		
		// Initialize all subsystems
		m_drivetrain = new DriveTrain();
		m_oi = new OI();

		// instantiate the command used for the autonomous period
		m_autonomousCommand = new Autonomous();
		xbox = m_oi.getXboxControl();

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
		m_oi.refreshAll();
		Scheduler.getInstance().run();
		log();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic(){
		//Testing xbox buttons, joysticks and triggers
		m_oi.refreshAll();
		//System.out.println("Left Y = " + xbox.LeftStick.Y);
		//System.out.println("Right Y = " + xbox.RightStick.Y);
		//System.out.println("Left X = " + xbox.LeftStick.X);
		//System.out.println("Right X = " + xbox.RightStick.X);
		if(xbox.Buttons.A.changedDown){
			System.out.println("Button A = " + xbox.Buttons.A.changedDown);
		} else if(xbox.Buttons.B.changedUp){
			System.out.println("Button B = " + xbox.Buttons.B.changedDown);
		}
		
		
		final double cpr = 360.0;
		final double encoder_angular_distance_per_pulse = 2.0*Math.PI / cpr;
		final double wheel_radius = 2.5;  // .564 in (18T 5mm pitch)
		final double encLinearDistancePerPulse = wheel_radius * encoder_angular_distance_per_pulse; //2.0 * Math.PI / cpr;
		
		System.out.println(enc.getRaw()*encLinearDistancePerPulse);
		
		
		//System.out.println(gyro.getAngle(MPU6050.Axis.X));

	    //PNEUMATICS
	    //c.setClosedLoopControl(true);
	    //c.setClosedLoopControl(false);
	}
	

	/**
	 * The log method puts interesting information to the SmartDashboard.
	 */
	private void log() {
		m_drivetrain.log();
	}
}
