package org.usfirst.frc.team3571.robot.subsystems;

import org.usfirst.frc.team3571.robot.RobotMap;
import org.usfirst.frc.team3571.robot.utilities.Loggable;
import org.usfirst.frc.team3571.robot.utilities.RobotMath;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ForkLift extends Subsystem implements Loggable  {
	
	
	private Spark liftMotor = new Spark(RobotMap.PWM.FL_LIFT_MOTOR);
	private Spark tiltMotor = new Spark(RobotMap.PWM.FL_TILT_MOTOR);
	//encoder
	private Encoder distanceEncoder = new Encoder(RobotMap.ENCODER.FL_DISTANCE_ENCODER_CHANNEL_A,
															RobotMap.ENCODER.FL_DISTANCE_ENCODER_CHANNEL_B,
															RobotMap.ENCODER.REVERSE_DIRECTION,
															 RobotMap.ENCODER.ENCODER_TYPE);
	private State liftState;
	private boolean direction; 
	
	public ForkLift() {
		super();
		//current state
		this.liftState = State.BOTTOM;
		this.direction = RobotMap.LIFT.UP;
		//set the distance per pulse to whatevers defaulted in the robotmap
		distanceEncoder.setDistancePerPulse(RobotMath.getDistancePerPulse(RobotMap.ENCODER.COUNTS_PER_REVOLUTION, 
				RobotMap.ENCODER.FL_RADIUS));
		
		addChild("Distance Encoder", distanceEncoder);
	}
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method 
	}
	
	@Override
	public void log() {
		//log any values here (will be shown on dashboard)
		//SmartDashboard.putNumber(..)
		SmartDashboard.putNumber("Lift motor", liftMotor.getPosition());
		SmartDashboard.putNumber("Distance Encoder Rate", distanceEncoder.getRate());
		SmartDashboard.putNumber("Distance Encoder Distance", distanceEncoder.getDistance());
	}
	
	public void updateDirection(boolean direction) {
		this.direction = direction;
		changeState();
	}
	
	public Spark getLift() {
		return liftMotor;
	}
	
	public Spark getTilt() {
		return tiltMotor;
	}
	
	public Encoder getDistanceEncoder() {
		return distanceEncoder;
	}
	
	public State getState() {
		return liftState;
	}
	
	private void changeState() {
		if(direction==RobotMap.LIFT.UP) {
			liftState = ForkLift.State.values()[liftState.ordinal()+1];
		}
		else {
			liftState = ForkLift.State.values()[liftState.ordinal()-1];
		}
	}
	
	public enum State {
		BOTTOM,
		MIDDLE,
		TOP;
	}

}
