package org.usfirst.frc.team3571.robot.path;

import org.usfirst.frc.team3571.robot.command.CenterRun;
import org.usfirst.frc.team3571.robot.command.LongRun;
import org.usfirst.frc.team3571.robot.command.ShortRun;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class PathCommand extends Command {
	
	public void start(boolean side, TARGET target, boolean isMiddle) {
		if(!isMiddle) {
			getCommand(side, target).start();
		}
		else {
			new CenterRun(side).start();
		}
	}
	
	@Override
	public void start() {
		//can't throw exception for overrided method 
		System.out.println("Use #start(boolean side, TARGET target)");
	}
	
	private CommandGroup getCommand(boolean side, TARGET target) {
		if(target==TARGET.SWITCH) {
			return new ShortRun(side);
		}
		else {
			return new LongRun(side);
		}
	}
	
	public enum TARGET {
		SWITCH,
		SCALE
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
