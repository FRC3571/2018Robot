package org.usfirst.frc.team3571.robot.command;

import org.usfirst.frc.team3571.robot.Robot;
import org.usfirst.frc.team3571.robot.RobotMap;
import org.usfirst.frc.team3571.robot.utilities.RobotMath;

import edu.wpi.first.wpilibj.command.Command;


public class TurnWithDegrees extends Command {
	
	
	private double distance; 
	private double leftSpeed = 0.5;
	private double rightSpeed = 0.5;
	
	public TurnWithDegrees(double degrees) {
		if(degrees<0) {
			leftSpeed *= -1;
		}
		else {
			rightSpeed *= -1;
		}
		
		requires(Robot.m_drivetrain);
		
		distance = RobotMath.getDistanceFromDegrees(degrees,RobotMap.DEFAULT.ROBOT_TURN_RADIUS);
		
	}
	
	@Override
	protected void initialize() {
		Robot.m_drivetrain.reset();
		Robot.m_drivetrain.drive(leftSpeed,rightSpeed);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return Robot.m_drivetrain.getTurnDistance() >= distance;
	}
	
	@Override
	protected void end() {
		Robot.m_drivetrain.drive(0, 0);
	}

}
