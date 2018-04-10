package org.usfirst.frc.team3571.robot.subsystems;



import org.usfirst.frc.team3571.robot.RobotMap;

import org.usfirst.frc.team3571.robot.command.BoxIntake;

import org.usfirst.frc.team3571.robot.utilities.XboxController;



import edu.wpi.first.wpilibj.Spark;

import edu.wpi.first.wpilibj.SpeedControllerGroup;

import edu.wpi.first.wpilibj.command.Subsystem;

//import edu.wpi.first.wpilibj.drive.DifferentialDrive;

//import org.usfirst.frc.team3571.robot.utilities.XboxController;



public class Intake extends Subsystem {



	private Spark leftIntake = new Spark(RobotMap.PWM.RIGHT_INTAKE_MOTOR);

	private Spark rightIntake = new Spark(RobotMap.PWM.LEFT_INTAKE_MOTOR);

	private SpeedControllerGroup intakeSpeedControl = new SpeedControllerGroup(leftIntake, rightIntake);

	

	public Intake(){

		super();

		//intake motors need to run in opposite directions

		//leftIntake.setInverted(RobotMap.PWM.MOTOR_INVERTED);
		rightIntake.setInverted(RobotMap.PWM.MOTOR_INVERTED);

		

		// Let's name the sensors on the LiveWindow

		addChild("Intake", intakeSpeedControl);



	}

	

	public void runIntake(double speed, Spark intakeMotor, boolean isLeft){
		
		//intakeSpeedControl.set(speed);
		if(intakeMotor==null) {
			leftIntake.setSpeed(0);
			rightIntake.setSpeed(0);
			intakeSpeedControl.set(0);
		}
		else {
			//hard limit 40% when pushing out
			double abSpeed = Math.abs(speed);
			if(abSpeed>=.4 && speed<0) {
				speed = -.4;
			}
			
			if(isLeft) {
				intakeMotor.setSpeed(-speed);
			}
			else {
				intakeMotor.setSpeed(speed);
			}
		}

	}
	
	public void runIntake(double speed) {
		intakeSpeedControl.set(speed);
	}

	

	/*public void runIntake(XboxController xbox) {

		if(xbox.Triggers.Right > 0){

			runIntake(xbox.Triggers.Right);

		} 

		else {

			runIntake(-xbox.Triggers.Left);

		}

	}*/
	
	
	
	public void runIntake(XboxController xbox) {
		runIntake(xbox.RightStick.Y,leftIntake, false);
		runIntake(xbox.LeftStick.Y, rightIntake, true);
	}
	

	

//	public void runIntakeReverse(XboxController xbox) {

//		runIntake(-xbox.Triggers.Left);

//	}

//	

	

	/**

	 * When no other command is running let the operator control the intake using the

	 * XboxContoller.

	 */

	@Override

	public void initDefaultCommand() {

		setDefaultCommand(new BoxIntake());

	}



}