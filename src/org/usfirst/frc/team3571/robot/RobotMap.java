package org.usfirst.frc.team3571.robot;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;

public class RobotMap {
	
	public static class PWM {
		public static final int FRONT_LEFT_DRIVE_MOTOR = 0;
		public static final int MIDDLE_LEFT_DRIVE_MOTOR = 1;
		//public static final int REAR_LEFT_DRIVE_MOTOR = 1;
		public static final int FRONT_RIGHT_DRIVE_MOTOR = 2;
		public static final int MIDDLE_RIGHT_DRIVE_MOTOR = 3;
		//public static final int REAR_RIGHT_DRIVE_MOTOR = 3; 
		
		public static final int RIGHT_INTAKE_MOTOR = 6;
		public static final int LEFT_INTAKE_MOTOR = 5;
		public static final int FL_TILT_MOTOR = 4;
		public static final int FL_LIFT_MOTOR = 7;
		public static final int FL_LIFT_MOTOR_SECOND = 8;
		public static final boolean MOTOR_INVERTED = true;
		public static final boolean MOTOR_NOT_INVERTED = false;
		public static final double LEFT_MOTOR_OFFSET = 1.0000;
		public static final double RIGHT_MOTOR_OFFSET = 1.0000;
		
		// 4-9 Free
		// 0-9 Free
		//Turn error
	}

	public static class DriverUSB {
		public static final int DRIVER_CONTROLLER = 0;
		public static final int OPERATOR_CONTROLLER = 1;
	}
	
	public static class INTAKE {
		//time (in seconds) for intake to suck in/suck out during autonomous
		public static final int AUTO_TIME = 3;
		//intake motor speed during auto
		public static final double SPEED = 0.75;
	}
	
	public static class ENCODER {
		public static final int FRONT_LEFT_ENCODER_CHANNEL_A = 0; //temp, uses 0 and 1
		public static final int FRONT_LEFT_ENCODER_CHANNEL_B = 1;
		public static final int FRONT_RIGHT_ENCODER_CHANNEL_A = 2;
		public static final int FRONT_RIGHT_ENCODER_CHANNEL_B = 3;
		//distance encoders for forklift
		//public static final int FL_DISTANCE_ENCODER_CHANNEL_A = 4;
		//public static final int FL_DISTANCE_ENCODER_CHANNEL_B = 5;
		//tilt distance encoder
		public static final int TILT_DISTANCE_ENCODER_CHANNEL_A = 6;
		public static final int TILT_DISTANCE_ENCODER_CHANNEL_B = 7;
		
		public static final boolean REVERSE_DIRECTION = true;
		public static final boolean FORWARD_DIRECTION = false;
		public static final EncodingType ENCODER_TYPE = EncodingType.k1X;
		public static final double COUNTS_PER_REVOLUTION = 2048.0;
		public static final double WHEEL_RADIUS = 62.5; //in millimeters
		public static final double FL_RADIUS = 3;
		public static final double SCALE_HEIGHT = 1000;
		public static final double TILT_DISTANCE = 100;
		public static final double SWITCH_HEIGHT = 2000;
		public static final double GEAR_RATIO_LOW = 4.6;
		public static final double GEAR_RATIO_HIGH = 2.7;
		
	}
	
	public static class ANALOG {
		public static final int FL_DISTANCE_ENCODER = 0;
	}
	
	public static class LIFT {
		public static final boolean UP = true;
		public static final boolean DOWN = false;
		//used for timed lift, time for moving
		public static final int TIME = 2;
		public static final double SPEED = 0.99;
		
		public static class TILT {
			public static final double SPEED = 0.4;
			public static final double TIME = .5;	
			public static final int UP = 1;
			public static final int MIDDLE = 2;
			public static final int DOWN = 3;
			public static final int TOP_LIMIT_SWITCH = 8;
			public static final int MIDDLE_LIMIT_SWITCH = 7;
			public static final int BOTTOM_LIMIT_SWITCH = 10;
		}
	}
	
	/*

	 * Each solenoid needs an integer identifier for the array use in Pneumatics. (i.e. GEAR_SHIFT_LEFT)

	 * Each physical solenoid on the robot needs to have a DIO assigned to it. (i.e. SOLENOID_ID_1)

	 */

	public static class PNEUMATICS {

		public static final int PUMP_ID = 0;

		public static final int GEARSHIFT_SOLENOID = 0; //0
		
		public static final int FORKLIFT_SOLENOID = 1; //1

		//public static final int RAMP_SOLENOID_LEFT = 4; //4

		//public static final int RAMP_SOLENOID_RIGHT = 5; //5
		
		//shift

		public static final int SOLENOID_ID_1 = 2; //6

		public static final int SOLENOID_ID_2 = 3; //7
		
		//fork
		
		public static final int SOLENOID_ID_3 = 0; //2
		
		public static final int SOLENOID_ID_4 = 1; //3

		//public static final int SOLENOID_ID_3 = 3;

		//public static final int SOLENOID_ID_4 = 3;

		//public static final int SOLENOID_ID_5 = 3;

		//public static final int SOLENOID_ID_6 = 3;

	}
	
	public static class DEFAULT{
		public static final double CONTROLLER_DEADZONE = 0.1;
		public static final double ROBOT_TURN_RADIUS = 315; //mm
	}

}
