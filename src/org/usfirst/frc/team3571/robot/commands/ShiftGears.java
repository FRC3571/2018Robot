package org.usfirst.frc.team3571.robot.commands;

import org.usfirst.frc.team3571.robot.Robot;
import org.usfirst.frc.team3571.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class ShiftGears extends Command {
	
	public ShiftGears(){	
		 requires(Robot.m_pneumatics);
	}

	
	// Called just before this Command runs the first time
    protected void initialize() {
    	Robot.m_pneumatics.createSolenoid(RobotMap.PNEUMATICS.GEARSHIFT_SOLENOID, 
    									RobotMap.PNEUMATICS.SOLENOID_ID_1, 
    									RobotMap.PNEUMATICS.SOLENOID_ID_2);
    }
    
    public void shiftGears(){
    	Robot.m_pneumatics.solenoidForward(RobotMap.PNEUMATICS.GEARSHIFT_SOLENOID);
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
