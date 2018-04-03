/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3571.robot.subsystems;


import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import org.usfirst.frc.team3571.robot.utilities.XboxController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import org.usfirst.frc.team3571.robot.Robot;
//import org.usfirst.frc.team3571.robot.Robot;
import org.usfirst.frc.team3571.robot.RobotMap;
import org.usfirst.frc.team3571.robot.command.TankDriveWithXboxControl;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team3571.robot.utilities.Loggable;
import org.usfirst.frc.team3571.robot.utilities.MPU6050;
import org.usfirst.frc.team3571.robot.utilities.RobotMath;

/**
 * The DriveTrain subsystem incorporates the sensors and actuators attached to
 * the robots chassis. These include four drive motors, a left and right encoder
 * and a gyro.
 */
public class DriveTrain extends Subsystem implements Loggable {
	
	//Six motor drivetrain:	 
	
	private Spark m_frontLeft = new Spark(RobotMap.PWM.FRONT_LEFT_DRIVE_MOTOR);
	private Spark m_midLeft = new Spark(RobotMap.PWM.MIDDLE_LEFT_DRIVE_MOTOR);
	//private Spark m_rearLeft = new Spark(RobotMap.PWM.REAR_LEFT_DRIVE_MOTOR);
	private SpeedControllerGroup m_left = new SpeedControllerGroup(m_frontLeft, m_midLeft);

	private Spark m_frontRight = new Spark(RobotMap.PWM.FRONT_RIGHT_DRIVE_MOTOR);
	private Spark m_midRight = new Spark(RobotMap.PWM.MIDDLE_RIGHT_DRIVE_MOTOR);
	//private Spark m_rearRight = new Spark(RobotMap.PWM.REAR_RIGHT_DRIVE_MOTOR);
	private SpeedControllerGroup m_right = new SpeedControllerGroup(m_frontRight, m_midRight);

	private DifferentialDrive m_drive = new DifferentialDrive(m_left, m_right);
	

	private Encoder leftEncoder = new Encoder(RobotMap.ENCODER.FRONT_LEFT_ENCODER_CHANNEL_A, 
												RobotMap.ENCODER.FRONT_LEFT_ENCODER_CHANNEL_B, 
												RobotMap.ENCODER.REVERSE_DIRECTION,
												RobotMap.ENCODER.ENCODER_TYPE);
	
	private Encoder rightEncoder = new Encoder(RobotMap.ENCODER.FRONT_RIGHT_ENCODER_CHANNEL_A, 
												 RobotMap.ENCODER.FRONT_RIGHT_ENCODER_CHANNEL_B, 
												 RobotMap.ENCODER.FORWARD_DIRECTION,
												 RobotMap.ENCODER.ENCODER_TYPE);
	
	//private AnalogInput m_rangefinder = new AnalogInput(6);
	private MPU6050 m_gyro = new MPU6050();

	public DriveTrain() {
		super();

		//Middle motor may need to travel in opposite direction to others based on gearbox design
		m_midLeft.setInverted(RobotMap.PWM.MOTOR_INVERTED);
		m_midRight.setInverted(RobotMap.PWM.MOTOR_INVERTED);
		
		final double encoderLinearDistancePerPulse = RobotMath.getDistancePerPulse(RobotMap.ENCODER.COUNTS_PER_REVOLUTION, 
				RobotMap.ENCODER.WHEEL_RADIUS);
		
		leftEncoder.setDistancePerPulse(encoderLinearDistancePerPulse);
		rightEncoder.setDistancePerPulse(encoderLinearDistancePerPulse);
		
		
		// Let's name the sensors on the LiveWindow
		addChild("Drive", m_drive);
		addChild("Left Encoder", leftEncoder);
		addChild("Right Encoder", rightEncoder);
		//addChild("Rangefinder", m_rangefinder);
		addChild("Gyro", m_gyro);
		
		drive(0, 0);
		m_drive.setSafetyEnabled(false);
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
	@Override
	public void log() {
		SmartDashboard.putNumber("Left Distance", leftEncoder.getDistance());
		SmartDashboard.putNumber("Right Distance", rightEncoder.getDistance());
		SmartDashboard.putNumber("Left Speed", leftEncoder.getRate());
		SmartDashboard.putNumber("Right Speed", rightEncoder.getRate());
		SmartDashboard.putNumber("Gyro", m_gyro.getAngle());
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
		double leftOffset = SmartDashboard.getNumber("leftOff", RobotMap.PWM.LEFT_MOTOR_OFFSET);
		double rightOffset = SmartDashboard.getNumber("rightOff", RobotMap.PWM.RIGHT_MOTOR_OFFSET);
		
		m_drive.tankDrive(left*leftOffset, right*rightOffset);
	}

	/**
	 * Tank style driving for the DriveTrain.
	 *
	 * @param xbox The XboxController use to drive tank style.
	 */
	public void drive(XboxController xbox) {
		//drive(-xbox.getY(GenericHID.Hand.kLeft), xbox.getY(GenericHID.Hand.kRight));	
		drive(-xbox.LeftStick.Y, -xbox.RightStick.Y);
	}

	/**
	 * Get the robot's heading.
	 *
	 * @return The robots heading in degrees.
	 */
	public double getHeading() {
		return m_gyro.getAngle();
	}

	/**
	 * Reset the robots sensors to the zero states.
	 */
	public void reset() {
		m_gyro.reset();
		leftEncoder.reset();
		rightEncoder.reset();
	}

	/**
	 * Get the average distance of the encoders since the last reset.
	 *
	 * @return The distance driven (average of left and right encoders).
	 */
	public double getDistance() {
		return (leftEncoder.getDistance() + rightEncoder.getDistance())/2;
	}
	
	public double getTurnDistance() {
		return (Math.abs(leftEncoder.getDistance()) + Math.abs(rightEncoder.getDistance()))/2;
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
