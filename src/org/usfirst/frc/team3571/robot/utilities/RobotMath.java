package org.usfirst.frc.team3571.robot.utilities;

public class RobotMath {
	
	/**
	 * get distance per pulse for encoder given certain params
	 * @param countsPerRevolution (per wheel cycle)
	 * @param wheelRadius the radius of the wheel
	 * @return the distance per pulse
	 */
	public static double getDistancePerPulse(final double countsPerRevolution, final double wheelRadius) {
		final double encoderAngularDistancePerPulse = 2.0*Math.PI/countsPerRevolution;
		return wheelRadius * encoderAngularDistancePerPulse;
	}

}
