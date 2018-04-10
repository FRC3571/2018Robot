package org.usfirst.frc.team3571.robot.command;

import org.usfirst.frc.team3571.robot.Robot;
import org.usfirst.frc.team3571.robot.RobotMap;

import edu.wpi.first.wpilibj.command.TimedCommand;

public class IntakeOut extends TimedCommand {
	
	private boolean in;
	
	public IntakeOut(boolean in) {
		super(RobotMap.INTAKE.AUTO_TIME);
		requires(Robot.m_intake);
		this.in = in;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void initialize() {
		final double speed;
		if(in) {
			speed = -RobotMap.INTAKE.SPEED;
		}
		else {
			speed = RobotMap.INTAKE.SPEED;
		}
		Robot.m_intake.runIntake(speed);
	}
	
	
	@Override
	protected void end() {
		Robot.m_intake.runIntake(0,null,false);
	}

}
