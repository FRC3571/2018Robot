package org.usfirst.frc.team3571.robot.command;

import org.usfirst.frc.team3571.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CenterRun extends CommandGroup {
	
	private int turn;
	
	public CenterRun(boolean left) {
		if(left) {
			turn = -45;
		}
		else {
			turn = 45;
		}
		
		/**
		 * 500
		 * t45
		 * 1000
		 * t-45
		 * 2350
		 */
		addSequential(new DriveStraightDistance(500));
		addSequential(new TurnWithDegrees(turn));
		addSequential(new DriveStraightDistance(1000));
		addSequential(new TurnWithDegrees(-turn));
		addSequential(new DriveStraightDistance(2350));
		addSequential(new TiltCommand(RobotMap.LIFT.DOWN));
		addSequential(new IntakeOut(false));
	}
	
	@Override 
	protected void initialize() {
		System.out.println("Moving center");
	}

}
