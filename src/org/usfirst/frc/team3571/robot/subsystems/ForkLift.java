package org.usfirst.frc.team3571.robot.subsystems;

import org.usfirst.frc.team3571.robot.RobotMap;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ForkLift extends Subsystem {
	
	
	private Spark liftMotor = new Spark(RobotMap.PWM.FL_LIFT_MOTOR);
	private Spark tiltMotor = new Spark(RobotMap.PWM.FL_TILT_MOTOR);
	
	public ForkLift() {
		super();
		
	}

	@Override
	protected void initDefaultCommand() {
		tiltMotor.set(0.5);
		// TODO Auto-generated method 
	}

}
