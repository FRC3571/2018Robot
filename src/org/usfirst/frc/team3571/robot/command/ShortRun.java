package org.usfirst.frc.team3571.robot.command;

import org.usfirst.frc.team3571.robot.RobotMap;
import org.usfirst.frc.team3571.robot.path.PathCommand;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class ShortRun extends CommandGroup {
	
	private int turn;
	
	public ShortRun(boolean left) {
		if(left) {
			turn = -90;
		}
		else {
			turn = 90;
		}
		
		addSequential(new DriveStraightDistance(4550));
		addSequential(new TurnWithDegrees(turn));
		addSequential(new DriveStraightDistance(500));
		addSequential(new TiltCommand(RobotMap.LIFT.DOWN));
		addSequential(new IntakeOut(false));
	}
	
	@Override
	protected void initialize() {
		System.out.print("Short run..");
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
