package org.usfirst.frc.team3571.robot.command;

import org.usfirst.frc.team3571.robot.Robot;
import org.usfirst.frc.team3571.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class OpenCloseCommand extends Command {
	
	public OpenCloseCommand(boolean forward) {
		requires(Robot.m_pneumatics);
		
	}
	
	public void initialize() {
		if(Robot.m_pneumatics.getOpenState()) {
			Robot.m_pneumatics.solenoidReverse(RobotMap.PNEUMATICS.FORKLIFT_SOLENOID);
			Robot.m_pneumatics.setOpenState(false);
		}
		else {
			Robot.m_pneumatics.solenoidForward(RobotMap.PNEUMATICS.FORKLIFT_SOLENOID);
			Robot.m_pneumatics.setOpenState(true);
		}
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
