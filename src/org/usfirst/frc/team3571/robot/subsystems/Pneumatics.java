package org.usfirst.frc.team3571.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Pneumatics /*extends Subsystem*/ {

	//@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		Compressor c = new Compressor(0);

		c.setClosedLoopControl(true);
		
	}
	
	public void test() {
		Compressor c = new Compressor(0);
		c.setClosedLoopControl(true);
	}

}
