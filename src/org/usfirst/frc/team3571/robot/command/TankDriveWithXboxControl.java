/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3571.robot.command;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3571.robot.Robot;

/**
 * Have the robot drive tank style using the XboxController until interrupted.
 */
public class TankDriveWithXboxControl extends Command {
	public TankDriveWithXboxControl() {
		requires(Robot.m_drivetrain);
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		Robot.m_drivetrain.drive(Robot.m_oi.getXboxControl());
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false; // Runs until interrupted
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.m_drivetrain.drive(0, 0);
	}
}
