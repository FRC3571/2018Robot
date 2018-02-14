package org.usfirst.frc.team3571.robot;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;


public class RobotMap {
	
	public static class PWM {
		public static final int FRONT_LEFT_DRIVE_MOTOR = 0;
		public static final int MIDDLE_LEFT_DRIVE_MOTOR = 2;
		public static final int REAR_LEFT_DRIVE_MOTOR = 1;
		public static final int FRONT_RIGHT_DRIVE_MOTOR = 4;
		public static final int MIDDLE_RIGHT_DRIVE_MOTOR = 3;
		public static final int REAR_RIGHT_DRIVE_MOTOR = 5;
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
	
/*
 * channelA         The a channel digital input channel.
 * channelB         The b channel digital input channel.
 * reverseDirection represents the orientation of the encoder and inverts the output values
 *                         if necessary so forward represents positive values. (default = false)
 * encodingType     either k1X, k2X, or k4X to indicate 1X, 2X or 4X decoding. If 4X is
 *                         selected, then an encoder FPGA object is used and the returned counts
 *                         will be 4x the encoder spec'd value since all rising and falling edges
 *                         are counted. If 1X or 2X are selected then a m_counter object will be
 *                         used and the returned value will either exactly match the spec'd count
 *                         or be double (2x) the spec'd count. (default = k4X);
 */
	public static class ENCODER{
		public static final int FRONT_LEFT_ENCODER_CHANNEL_A = 0;
		public static final int FRONT_LEFT_ENCODER_CHANNEL_B = 1;
		//public static final int FRONT_LEFT_ENCODER_CHANNEL_X = 2;//index
		public static final int FRONT_RIGHT_ENCODER_CHANNEL_A = 3;
		public static final int FRONT_RIGHT_ENCODER_CHANNEL_B = 4;
		//public static final int FRONT_RIGHT_ENCODER_CHANNEL_X = 5;//index
		public static final boolean REVERSE_DIRECTION = true;
		public static final boolean FORWARD_DIRECTION = false;
		public static final EncodingType ENCODER_TYPE = EncodingType.k1X;
		public static final double COUNTS_PER_REVOLUTION = 2048.0;
		public static final double WHEEL_RADIUS = 63.5;//in millimetres
	}
	
	public static class PNEUMATICS{
		public static final int PUMP_ID = 0;
		public static final int GEARSHIFT_SOLENOID; 
		public static final int SOLENOID_ID_1;
		public static final int SOLENOID_ID_2;
	}
	
	public static class DEFAULT{
		public static final double CONTROLLER_DEADZONE = 0.25;
	}

}
