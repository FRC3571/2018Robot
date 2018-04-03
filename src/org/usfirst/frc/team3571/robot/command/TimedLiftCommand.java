package org.usfirst.frc.team3571.robot.command;

import org.usfirst.frc.team3571.robot.Robot;
import org.usfirst.frc.team3571.robot.RobotMap;
import org.usfirst.frc.team3571.robot.subsystems.ForkLift;

import edu.wpi.first.wpilibj.command.TimedCommand;

public class TimedLiftCommand extends TimedCommand {
	
	private boolean direction;
	
	public TimedLiftCommand(boolean direction) {
		super(RobotMap.LIFT.TIME);
		requires(Robot.m_forklift);
		this.direction = direction;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void initialize() {
		ForkLift.State currState = Robot.m_forklift.getLiftState();
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
	
	@Override
	protected void end() {
		Robot.m_forklift.updateDirection(direction);
		Robot.m_forklift.getLift().setSpeed(0);
		Robot.m_forklift.getDistanceEncoder().reset();
	}

}
