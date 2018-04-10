package org.usfirst.frc.team3571.robot.subsystems;

import org.usfirst.frc.team3571.robot.RobotMap;
import org.usfirst.frc.team3571.robot.utilities.LiftGroup;
import org.usfirst.frc.team3571.robot.utilities.Loggable;
import org.usfirst.frc.team3571.robot.utilities.RobotMath;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogTrigger;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ForkLift extends Subsystem implements Loggable  {
	
	
	private Spark firstLiftMotor = new Spark(RobotMap.PWM.FL_LIFT_MOTOR);
	private Spark secondLiftMotor = new Spark(RobotMap.PWM.FL_LIFT_MOTOR_SECOND);
	private LiftGroup liftMotor = new LiftGroup(firstLiftMotor, secondLiftMotor);
	//tilt
	private Spark tiltMotor = new Spark(RobotMap.PWM.FL_TILT_MOTOR);
	/*encoder
	private Encoder distanceEncoder = new Encoder(RobotMap.ENCODER.FL_DISTANCE_ENCODER_CHANNEL_A,
															RobotMap.ENCODER.FL_DISTANCE_ENCODER_CHANNEL_B,
															RobotMap.ENCODER.REVERSE_DIRECTION,
															 RobotMap.ENCODER.ENCODER_TYPE);*/
	private AnalogTrigger rawDistanceInput;
	private Counter distanceEncoder; 
	
	private Encoder tiltEncoder = new Encoder(RobotMap.ENCODER.TILT_DISTANCE_ENCODER_CHANNEL_A,
			RobotMap.ENCODER.TILT_DISTANCE_ENCODER_CHANNEL_B, RobotMap.ENCODER.REVERSE_DIRECTION, RobotMap.ENCODER.ENCODER_TYPE);
	
	private State liftState;
	private State tiltState;
	private boolean direction; 
	private boolean tiltDirection;
	
	public ForkLift() {
		super();
		
		//analog encoder
		rawDistanceInput = new AnalogTrigger(0);
		distanceEncoder = new Counter(rawDistanceInput);
		
		//current state
		this.liftState = State.LIFT_BOTTOM;
		this.tiltState = State.TILT_TOP;
		this.direction = RobotMap.LIFT.UP;
		this.tiltDirection = RobotMap.LIFT.UP;
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
		SmartDashboard.putNumber("Lift motor (first)", liftMotor.first().getPosition());
		SmartDashboard.putNumber("Lift motor (second)", liftMotor.second().getPosition());
		SmartDashboard.putNumber("Distance Encoder Rate", distanceEncoder.getRate());
		SmartDashboard.putNumber("Distance Encoder Distance", distanceEncoder.getDistance());
	}
	
	public void updateDirection(boolean direction) {
		this.direction = direction;
		changeState();
	}
	
	public void updateTiltDirection(boolean direction) {
		this.tiltDirection = direction;
		changeTiltState();
	}
	
	public LiftGroup getLift() {
		return liftMotor;
	}
	
	public Spark getTilt() {
		return tiltMotor;
	}
	
	public Counter getDistanceEncoder() {
		return distanceEncoder;
	}
	
	public Encoder getTiltEncoder() {
		return tiltEncoder;
	}
	
	public State getLiftState() {
		return liftState;
	}
	
	public State getTiltState() {
		return tiltState;
	}
	
	private void changeState() {
		if(direction==RobotMap.LIFT.UP) {
			liftState = ForkLift.State.values()[liftState.ordinal()+1];
		}
		else {
			liftState = ForkLift.State.values()[liftState.ordinal()-1];
		}
	}
	
	private void changeTiltState() {
		if(direction==RobotMap.LIFT.UP) {
			tiltState = ForkLift.State.values()[tiltState.ordinal()+1];
		}
		else {
			tiltState = ForkLift.State.values()[tiltState.ordinal()-1];
		}
	}
	
	public enum State {
		//lift range
		LIFT_BOTTOM,
		LIFT_MIDDLE,
		LIFT_TOP,
		//lift range (*end*)
		//tilt range
		TILT_BOTTOM,
		TILT_MIDDLE,
		TILT_TOP;
		//tilt range (*end*)
	}
	

}
