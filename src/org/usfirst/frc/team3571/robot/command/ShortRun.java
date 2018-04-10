package org.usfirst.frc.team3571.robot.command;

import org.usfirst.frc.team3571.robot.RobotMap;
import org.usfirst.frc.team3571.robot.path.PathCommand;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.TimedCommand;

public class ShortRun extends CommandGroup {
	
	private int turn;
	
	public ShortRun(boolean left) {
		if(left) {
			turn = -82;
		}
		else {
			turn = 82;
		}
		
		addSequential(new DriveStraightDistance(3250));
		addSequential(new TimedCommand(1));
		addSequential(new TurnWithDegrees(turn));
		addSequential(new TimedCommand(1));
		addSequential(new DriveStraightDistance(650));
		addSequential(new TimedTiltCommand(RobotMap.LIFT.DOWN));
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
