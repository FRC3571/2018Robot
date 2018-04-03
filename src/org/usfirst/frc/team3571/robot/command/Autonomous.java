/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3571.robot.command;

import java.util.BitSet;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * The main autonomous command to pickup and deliver the soda to the box.
 */
public class Autonomous extends CommandGroup {
	/**
	 * LEFT SIDE SHORT
	 * LEFT SIDE LONG
	 * RIGHT SIDE SHORT
	 * RIGHT SIDE LONG
	 * CENTER LEFT SHORT
	 * CENTER LEFT LONG
	 * CENTER RIGHT SHORT
	 * CENTER RIGHT LONG
	 * LEFT RIGHT SHORT
	 * LEFT RIGHT LONG
	 * RIGHT LEFT SHORT
	 * RIGHT LEFT LONG
	 * 
	 */
	public Autonomous() {
		
		// Usage: DriveStraightTimed(float timeout, float speed) 
		//addSequential(new DriveStraightTimed(4, 0.5)); // use a timed event to drive straight
		/**SHORT**/
		addSequential(new DriveStraightDistance(4550,0.75));
		addSequential(new TurnWithDegrees(90));
		addSequential(new DriveStraightDistance(500,0.5));
		/**LONG**/
		addSequential(new DriveStraightDistance(7400, 0.75));
		addSequential(new TurnWithDegrees(90));
		addSequential(new DriveStraightDistance(500, 0.5));
		//addSequential(new DriveStraightDistance(100,0.5));
		// use negative for reverse
		//addSequential(new DriveStraightDistance(4)); // Use Encoders if ultrasonic is
		
		
		// broken
		//addSequential(new Place());
		//addSequential(new SetDistanceToBox(0.60));
		// addSequential(new DriveStraight(-2)); // Use Encoders if ultrasonic
		// is broken
		//addParallel(new SetWristSetpoint(-45));
		//addSequential(new CloseClaw());
	}
	
	public void start(String signal, int position) {
		if(signal.length() > 0) {
			//directions
			BitSet directions = new BitSet();
			for(int i = 0; i<3; i++) {
				directions.set(i, signal.charAt(i) == 'R');
			}
			
		}
		super.start();
		
	}
}
