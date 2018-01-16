/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3571.robot.subsystems;

import edu.wpi.first.wpilibj.GenericHID;
//import edu.wpi.first.wpilibj.AnalogGyro;
//import edu.wpi.first.wpilibj.AnalogInput;
//import edu.wpi.first.wpilibj.Encoder;
//import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
//import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
//import org.usfirst.frc.team3571.robot.Robot;
import org.usfirst.frc.team3571.robot.commands.TankDriveWithXboxControl;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The DriveTrain subsystem incorporates the sensors and actuators attached to
 * the robots chassis. These include four drive motors, a left and right encoder
 * and a gyro.
 */
public class DriveTrain extends Subsystem {
	
	//Six motor drivetrain:	 
	
	private Spark m_frontLeft = new Spark(0);
	private Spark m_midLeft = new Spark(1);
	private Spark m_rearLeft = new Spark(2);
	private SpeedControllerGroup m_left = new SpeedControllerGroup(m_frontLeft, m_midLeft, m_rearLeft);

	private Spark m_frontRight = new Spark(3);
	private Spark m_midRight = new Spark(4);
	private Spark m_rearRight = new Spark(5);
	private SpeedControllerGroup m_right = new SpeedControllerGroup(m_frontRight, m_midRight, m_rearRight);

	private DifferentialDrive m_drive = new DifferentialDrive(m_left, m_right);
	

	//private Encoder m_leftEncoder = new Encoder(1, 2);
	//private Encoder m_rightEncoder = new Encoder(3, 4);
	//private AnalogInput m_rangefinder = new AnalogInput(6);
	//private AnalogGyro m_gyro = new AnalogGyro(1);

	public DriveTrain() {
		super();

		// Encoders may measure differently in the real world and in
		// simulation. In this example the robot moves 0.042 barleycorns
		// per tick in the real world, but the simulated encoders
		// simulate 360 tick encoders. This if statement allows for the
		// real robot to handle this difference in devices.
		/*
		if (Robot.isReal()) {
			m_leftEncoder.setDistancePerPulse(0.042);
			m_rightEncoder.setDistancePerPulse(0.042);
		} else {
			// Circumference in ft = 4in/12(in/ft)*PI
			m_leftEncoder.setDistancePerPulse((4.0 / 12.0 * Math.PI) / 360.0);
			m_rightEncoder.setDistancePerPulse((4.0 / 12.0 * Math.PI) / 360.0);
		}
		*/
		// Let's name the sensors on the LiveWindow
		addChild("Drive", m_drive);
		//addChild("Left Encoder", m_leftEncoder);
		//addChild("Right Encoder", m_rightEncoder);
		//addChild("Rangefinder", m_rangefinder);
		//addChild("Gyro", m_gyro);
	}

	/**
	 * When no other command is running let the operator drive around using the
	 * XboxContoller.
	 */
	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new TankDriveWithXboxControl());
	}

	/**
	 * The log method puts interesting information to the SmartDashboard.
	 */
	public void log() {
		//SmartDashboard.putNumber("Left Distance", m_leftEncoder.getDistance());
		//SmartDashboard.putNumber("Right Distance", m_rightEncoder.getDistance());
		//SmartDashboard.putNumber("Left Speed", m_leftEncoder.getRate());
		//SmartDashboard.putNumber("Right Speed", m_rightEncoder.getRate());
		//SmartDashboard.putNumber("Gyro", m_gyro.getAngle());
	}

	/**
	 * Tank style driving for the DriveTrain.
	 *
	 * @param left
	 *            Speed in range [-1,1]
	 * @param right
	 *            Speed in range [-1,1]
	 */
	public void drive(double left, double right) {
		m_drive.tankDrive(left, right);
	}

	/**
	 * Tank style driving for the DriveTrain.
	 *
	 * @param xbox The XboxController use to drive tank style.
	 */
	public void drive(XboxController xbox) {
		drive(-xbox.getY(GenericHID.Hand.kLeft), xbox.getY(GenericHID.Hand.kRight));
		//drive(-xbox.getX(GenericHID.Hand.kLeft), -xbox.getY(GenericHID.Hand.kLeft)); //left joystick
	}

	/**
	 * Get the robot's heading.
	 *
	 * @return The robots heading in degrees.
	 */
	public double getHeading() {
		return 0;//m_gyro.getAngle();
	}

	/**
	 * Reset the robots sensors to the zero states.
	 */
	public void reset() {
		//m_gyro.reset();
		//m_leftEncoder.reset();
		//m_rightEncoder.reset();
	}

	/**
	 * Get the average distance of the encoders since the last reset.
	 *
	 * @return The distance driven (average of left and right encoders).
	 */
	public double getDistance() {
		return 0;//(m_leftEncoder.getDistance() + m_rightEncoder.getDistance()) / 2;
	}

	/**
	 * Get the distance to the obstacle.
	 *
	 * @return The distance to the obstacle detected by the rangefinder.
	 */
	public double getDistanceToObstacle() {
		// Really meters in simulation since it's a rangefinder...
		return 0;//m_rangefinder.getAverageVoltage();
	}
}
