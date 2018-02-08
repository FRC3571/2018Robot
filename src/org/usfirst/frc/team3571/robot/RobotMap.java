package org.usfirst.frc.team3571.robot;

public class RobotMap {
	
	public static class PWM {
		public static final int FRONT_LEFT_DRIVE_MOTOR = 0;
		public static final int MIDDLE_LEFT_DRIVE_MOTOR = 1;
		public static final int REAR_LEFT_DRIVE_MOTOR = 2;
		public static final int FRONT_RIGHT_DRIVE_MOTOR = 3;
		public static final int MIDDLE_RIGHT_DRIVE_MOTOR = 4;
		public static final int REAR_RIGHT_DRIVE_MOTOR = 5;
		
		// 4-9 Free
		// 0-9 Free
		//Turn error
	}

	public static class DriverUSB {
		public static final int DRIVER_CONTROLLER = 0;
		public static final int OPERATOR_CONTROLLER = 1;
	}
	
	public static class DEFAULT{
		public static final double CONTROLLER_DEADZONE = 0.25;
	}

}
