package org.usfirst.frc.team3571.robot.command;

import edu.wpi.first.wpilibj.command.Command;

public class LiftCommand extends Command {
	
	//target location
	private double targetDistance;
	
	public LiftCommand(double targetDistance) {
		this.targetDistance = targetDistance;
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	

}
