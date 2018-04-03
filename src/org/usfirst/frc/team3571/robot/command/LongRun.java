package org.usfirst.frc.team3571.robot.command;

import org.usfirst.frc.team3571.robot.Robot;
import org.usfirst.frc.team3571.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class LongRun extends CommandGroup {
	
	private int turn;
	
	public LongRun(boolean left) {
		if(left) {
			turn = -90;
		}
		else {
			turn = 90;
		}
		addSequential(new DriveStraightDistance(7780));
		addSequential(new TurnWithDegrees(turn));
		addSequential(new DriveStraightDistance(500));
		addSequential(new LiftCommand(RobotMap.LIFT.UP));
		addSequential(new IntakeOut(false));
	}
	
	@Override
	protected void initialize() {
		System.out.println("Long run");
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
