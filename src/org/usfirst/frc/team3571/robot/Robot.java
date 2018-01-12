/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3571.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
//import edu.wpi.first.wpilibj.Spark;
//import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
//import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team3571.robot.commands.Autonomous;
import org.usfirst.frc.team3571.robot.subsystems.DriveTrain;


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
	
	//Six motor drivetrain:	 
	/*
	Spark m_frontLeft = new Spark(1);
	Spark m_midLeft = new Spark(2);
	Spark m_rearLeft = new Spark(3);
	SpeedControllerGroup m_left = new SpeedControllerGroup(m_frontLeft, m_midLeft, m_rearLeft);

	Spark m_frontRight = new Spark(4);
	Spark m_midRight = new Spark(5);
	Spark m_rearRight = new Spark(6);
	SpeedControllerGroup m_right = new SpeedControllerGroup(m_frontRight, m_midRight, m_rearRight);

	DifferentialDrive m_drive = new DifferentialDrive(m_left, m_right);
	*/	 

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
		Scheduler.getInstance().run();
		log();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}

	/**
	 * The log method puts interesting information to the SmartDashboard.
	 */
	private void log() {
		m_drivetrain.log();
	}
}
