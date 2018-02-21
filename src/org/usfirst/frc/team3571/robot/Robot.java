/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3571.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team3571.robot.commands.Autonomous;
import org.usfirst.frc.team3571.robot.subsystems.DriveTrain;
import org.usfirst.frc.team3571.robot.subsystems.Intake;
import org.usfirst.frc.team3571.robot.subsystems.Pneumatics;
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
	public static Pneumatics m_pneumatics;
	public static Intake m_intake;
	public static XboxController driverXbox;
	public static XboxController operatorXbox;
	public static OI m_oi;
	
	//public MPU6050 gyro = new MPU6050();
    //private Compressor c = new Compressor(0);
	//private Encoder enc = new Encoder(0,1,true,EncodingType.k1X);
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
			
		// Initialize all subsystems
		m_drivetrain = new DriveTrain();
		m_pneumatics = new Pneumatics();
		m_intake = new Intake();
		m_oi = new OI();

		// instantiate the command used for the autonomous period
		m_autonomousCommand = new Autonomous();
		driverXbox = m_oi.getDriverXboxControl();
		operatorXbox = m_oi.getOperatorXboxControl();

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
		OI.refreshAll();
		Scheduler.getInstance().run();
		log();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic(){
		//Testing xbox buttons, joysticks and triggers
		OI.refreshAll();
	}

	/**
	 * The log method puts interesting information to the SmartDashboard.
	 */
	private void log() {
		m_drivetrain.log();
	}
}
