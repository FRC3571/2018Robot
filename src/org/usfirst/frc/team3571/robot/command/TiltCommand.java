package org.usfirst.frc.team3571.robot.command;

import org.usfirst.frc.team3571.robot.Robot;
import org.usfirst.frc.team3571.robot.RobotMap;
import org.usfirst.frc.team3571.robot.subsystems.ForkLift;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Command;

public class TiltCommand extends Command {
	
	private int direction;
	
	public TiltCommand(int direction) {
		requires(Robot.m_forklift);
		this.direction = direction;
	}
	
	@Override
	protected void initialize() {
		Spark tilt = Robot.m_forklift.getTilt();
		final double speed = RobotMap.LIFT.TILT.SPEED;
		if(direction==RobotMap.LIFT.TILT.UP) {
			Robot.m_forklift.getTilt().setSpeed(0.4);
		}
		else if(direction==RobotMap.LIFT.TILT.MIDDLE) {
			ForkLift.State currState = Robot.m_forklift.getTiltState();
			switch (currState) {
				case TILT_BOTTOM:
					tilt.setSpeed(speed);
					break;
				case TILT_TOP:
					tilt.setSpeed(-speed);
				default:
					break;
			}
		}
		else {
			Robot.m_forklift.getTilt().setSpeed(-0.4);
		}
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		switch(direction) {
			case RobotMap.LIFT.TILT.UP:
				if(Robot.m_forklift.isTopHit()) {
					Robot.m_forklift.setTiltState(ForkLift.State.TILT_TOP);
					return true;
				}
				break;
			case RobotMap.LIFT.TILT.MIDDLE:
				if(Robot.m_forklift.isMiddleHit()) {
					Robot.m_forklift.setTiltState(ForkLift.State.TILT_MIDDLE);
					return true;
				}
				break;
			case RobotMap.LIFT.TILT.DOWN:
				if(Robot.m_forklift.isBottomHit()) {
					Robot.m_forklift.setTiltState(ForkLift.State.TILT_BOTTOM);
					return true;
				}
				break;
			
		}
		return false;
	}
	
	@Override 
	protected void end() {
		Robot.m_forklift.getTilt().setSpeed(0);
	}
	
	@Override
	protected void interrupted() {
		end();
	}
	


}
