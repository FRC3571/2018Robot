package org.usfirst.frc.team3571.robot.command;

import org.usfirst.frc.team3571.robot.Robot;
import org.usfirst.frc.team3571.robot.RobotMap;
import org.usfirst.frc.team3571.robot.subsystems.ForkLift;

import edu.wpi.first.wpilibj.command.TimedCommand;

public class TimedTiltCommand extends TimedCommand {
	
	private boolean direction;
	
	public TimedTiltCommand(boolean direction) {
		super(RobotMap.LIFT.TILT.TIME);
		requires(Robot.m_forklift);
		this.direction = direction;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void initialize() {
		ForkLift.State currState = Robot.m_forklift.getTiltState();
		//make sure state is not in max zones
		if((direction==RobotMap.LIFT.UP && currState == ForkLift.State.TILT_TOP) ||
				(direction==RobotMap.LIFT.DOWN && currState == ForkLift.State.TILT_BOTTOM)) {
			System.out.println("Move the opposite direction!");
		}
		else {
			//setup speed
			double speed = RobotMap.LIFT.TILT.SPEED;
			if(direction==RobotMap.LIFT.DOWN) {
				speed = -speed;
			}
			Robot.m_forklift.getTilt().setSpeed(speed);
		}
	}
	
	@Override
	protected void end() {
		Robot.m_forklift.updateTiltDirection(direction);
		Robot.m_forklift.getTilt().setSpeed(0);
		Robot.m_forklift.getTiltEncoder().reset();
	}

}