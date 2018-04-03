package org.usfirst.frc.team3571.robot.command;



import org.usfirst.frc.team3571.robot.Robot;

import org.usfirst.frc.team3571.robot.RobotMap;



import edu.wpi.first.wpilibj.command.Command;



public class ShiftGears extends Command {

	

	public ShiftGears(){

		System.out.println("ShiftGears starting ...");

		 requires(Robot.m_pneumatics);

	}

	

	// Called just before this Command runs the first time

    @Override

    public void initialize(){

    	if(!Robot.m_pneumatics.getShiftState()) {

    		Robot.m_pneumatics.solenoidForward(RobotMap.PNEUMATICS.GEARSHIFT_SOLENOID);

    	} else if(Robot.m_pneumatics.getShiftState()){

    		Robot.m_pneumatics.solenoidReverse(RobotMap.PNEUMATICS.GEARSHIFT_SOLENOID);

    	}

    }

    



    // Called repeatedly when this Command is scheduled to run

    protected void execute() {	

    }



    // Called once after timeout

    protected void end() {

    }



    // Called when another command which requires one or more of the same

    // subsystems is scheduled to run

    protected void interrupted() {

    }

	

	

	@Override

	protected boolean isFinished() {

		// TODO Auto-generated method stub

		return false;

	}



}