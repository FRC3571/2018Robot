package org.usfirst.frc.team3571.robot.command;

import org.usfirst.frc.team3571.robot.Robot;
import org.usfirst.frc.team3571.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class LiftManualCommand extends Command {
	
	private double speed;
	
	public LiftManualCommand(boolean up) {
		requires(Robot.m_forklift);
		if(up) {
			speed = RobotMap.LIFT.SPEED;
		}
		else {
			speed = -RobotMap.LIFT.SPEED;
		}
	}
	
	@Override
	protected void initialize() {
		Robot.m_forklift.getLift().setSpeed(speed);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	protected void end() {
		Robot.m_forklift.getLift().setSpeed(0);
	}

}
