package org.usfirst.frc.team3571.robot.command;

import org.usfirst.frc.team3571.robot.Robot;
import org.usfirst.frc.team3571.robot.RobotMap;
import org.usfirst.frc.team3571.robot.subsystems.ForkLift;

import edu.wpi.first.wpilibj.command.Command;

public class LiftCommand extends Command {
	
	//target location
	private double targetDistance;
	private boolean direction;
	private ForkLift.State currState;
	
	public LiftCommand(boolean direction) {
		requires(Robot.m_forklift);
		targetDistance = RobotMap.ENCODER.SCALE_HEIGHT;
		this.direction = direction;
		this.currState = ForkLift.State.LIFT_BOTTOM;
	}
	
	@Override
	protected void execute() {
		double currDistance = Math.abs(Robot.m_forklift.getDistanceEncoder().getDistance());
		if(currDistance>=targetDistance) {
			System.out.println("hit!");
			Robot.m_forklift.updateDirection(direction);
			Robot.m_forklift.getLift().setSpeed(0);
			Robot.m_forklift.getDistanceEncoder().reset();
		}
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return Robot.m_forklift.getLift().getSpeed()==0;
	}
	
	@Override
	public void initialize() {
		this.currState = Robot.m_forklift.getLiftState();
		//make sure state is not in max zones
		if((direction==RobotMap.LIFT.UP && currState == ForkLift.State.LIFT_TOP) ||
				(direction==RobotMap.LIFT.DOWN && currState == ForkLift.State.LIFT_BOTTOM)) {
			System.out.println("Move the opposite direction!");
		}
		else {
			//setup speed
			double speed = 0.5;
			if(direction==RobotMap.LIFT.DOWN) {
				speed = -0.5;
			}
			Robot.m_forklift.getLift().setSpeed(speed);
		}
	}
	

}
