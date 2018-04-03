package org.usfirst.frc.team3571.robot.command;

import org.usfirst.frc.team3571.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class TiltManualCommand extends Command {
	
	private double speed;
	
	public TiltManualCommand(boolean up) {
		requires(Robot.m_forklift);
		if(up) {
			speed = 0.9;
		}
		else {
			speed = -0.9;
		}
	}
	
	@Override
	protected void initialize() {
		Robot.m_forklift.getTilt().setSpeed(speed);
	}
	
	@Override
	protected void end() {
		Robot.m_forklift.getTilt().setSpeed(0);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
