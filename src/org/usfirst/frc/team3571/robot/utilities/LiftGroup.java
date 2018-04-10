package org.usfirst.frc.team3571.robot.utilities;

import edu.wpi.first.wpilibj.Spark;

public class LiftGroup {
	
	private Spark first;
	private Spark second;
	
	public LiftGroup(Spark first, Spark second) {
		this.first = first;
		this.second = second;
	}
	
	public void setSpeed(double speed) {
		first.setSpeed(-speed);
		second.setSpeed(speed);
	}
	
	public Spark first() {
		return first;
	}
	
	public Spark second() {
		return second;
	}

}
