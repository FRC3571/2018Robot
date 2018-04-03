package org.usfirst.frc.team3571.robot.command;

import org.usfirst.frc.team3571.robot.Robot;
import org.usfirst.frc.team3571.robot.RobotMap;
import org.usfirst.frc.team3571.robot.subsystems.ForkLift;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Command;

public class TiltCommand extends Command {
	
	private boolean direction;
	
	public TiltCommand(boolean direction) {
		requires(Robot.m_forklift);
		this.direction = direction;
	}
	
	@Override
	protected void initialize() {
		Spark tilt = Robot.m_forklift.getTilt();
		double speed = RobotMap.LIFT.TILT.SPEED;
		
		ForkLift.State currState = Robot.m_forklift.getLiftState();
		//make sure state is not in max zones
		if((direction==RobotMap.LIFT.UP && currState == ForkLift.State.TILT_TOP) ||
				(direction==RobotMap.LIFT.DOWN && currState == ForkLift.State.TILT_BOTTOM)) {
			System.out.println("Move the opposite direction!");
		}
		
		else {
			//setup speed
			if(direction==RobotMap.LIFT.DOWN) {
				speed = -speed;
			}
			Robot.m_forklift.getTilt().setSpeed(speed);
		}
	}
	
	@Override
	protected void execute() {
		double currDistance = Math.abs(Robot.m_forklift.getTiltEncoder().getDistance());
		if(currDistance>=RobotMap.ENCODER.TILT_DISTANCE) {
			System.out.println("hit!");
			Robot.m_forklift.updateTiltDirection(direction);
			Robot.m_forklift.getTilt().setSpeed(0);
			Robot.m_forklift.getTiltEncoder().reset();
		}
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return Robot.m_forklift.getTilt().getSpeed()==0;
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
