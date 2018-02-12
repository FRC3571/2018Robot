package org.usfirst.frc.team3571.robot;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;

public class RobotMap {
	
	public static class PWM {
		public static final int FRONT_LEFT_DRIVE_MOTOR = 0;
		public static final int MIDDLE_LEFT_DRIVE_MOTOR = 1;
		public static final int REAR_LEFT_DRIVE_MOTOR = 2;
		public static final int FRONT_RIGHT_DRIVE_MOTOR = 3;
		public static final int MIDDLE_RIGHT_DRIVE_MOTOR = 4;
		public static final int REAR_RIGHT_DRIVE_MOTOR = 5;
		public static final int FL_LIFT_MOTOR = 6;
		public static final int FL_TILT_MOTOR = 7;
		public static final boolean MOTOR_INVERTED = true;
		public static final boolean MOTOR_NOT_INVERTED = false;
		
		// 4-9 Free
		// 0-9 Free
		//Turn error
	}

	public static class DriverUSB {
		public static final int DRIVER_CONTROLLER = 0;
		public static final int OPERATOR_CONTROLLER = 1;
	}
	
	public static class ENCODER{
		public static final int FRONT_LEFT_ENCODER_CHANNEL_A = 0;
		public static final int FRONT_LEFT_ENCODER_CHANNEL_B = 1;
		public static final int FRONT_RIGHT_ENCODER_CHANNEL_A = 3;
		public static final int FRONT_RIGHT_ENCODER_CHANNEL_B = 4;
		public static final boolean REVERSE_DIRECTION = true;
		public static final boolean FORWARD_DIRECTION = false;
		public static final EncodingType ENCODER_TYPE = EncodingType.k1X;
		public static final double COUNTS_PER_REVOLUTION = 2048.0;
		public static final double WHEEL_RADIUS = 63.5; //in millimeters
		
		
	}
	
	public static class DEFAULT{
		public static final double CONTROLLER_DEADZONE = 0.25;
	}

}
