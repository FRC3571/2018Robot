package org.usfirst.frc.team3571.robot.command;

import org.usfirst.frc.team3571.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.TimedCommand;

public class CenterRun extends CommandGroup {
	
	private int turn;
	private double secondTurn;
	
	public CenterRun(boolean left) {
		if(left) {
			turn = -40;
			secondTurn = -52;
		}
		else {
			turn = 40;
			secondTurn = 52;
		}
		
		/**
		 * 500
		 * t45
		 * 1000
		 * t-45
		 * 2350
		 */
		addSequential(new DriveStraightDistance(300));
		addSequential(new TimedCommand(0.75));
		addSequential(new TurnWithDegrees(turn));
		addSequential(new TimedCommand(0.75));
		//extra 10 CM
		addSequential(new DriveStraightDistance(1500+100));
		addSequential(new TimedCommand(0.75));
		addSequential(new TurnWithDegrees(-secondTurn));
		addSequential(new TimedCommand(0.75));
		addSequential(new DriveStraightDistance(800));
		//addSequential(new DriveStraightDistance(2350));
		addSequential(new TimedTiltCommand(RobotMap.LIFT.DOWN));
		addSequential(new IntakeOut(false));
	}
	
	@Override 
	protected void initialize() {
		System.out.println("Moving center");
	}

}
