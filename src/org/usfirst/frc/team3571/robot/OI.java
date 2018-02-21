/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3571.robot;

import org.usfirst.frc.team3571.robot.RobotMap.DEFAULT;
import org.usfirst.frc.team3571.robot.RobotMap.DriverUSB;
import org.usfirst.frc.team3571.robot.commands.Autonomous;
import org.usfirst.frc.team3571.robot.commands.DriveStraightDistance;
import org.usfirst.frc.team3571.robot.commands.DriveStraightTimed;
import org.usfirst.frc.team3571.robot.commands.ShiftGears;
import org.usfirst.frc.team3571.robot.utilities.XboxController;
import org.usfirst.frc.team3571.robot.utilities.XboxController.CommandState;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	public static final XboxController driver = new XboxController(DriverUSB.DRIVER_CONTROLLER, DEFAULT.CONTROLLER_DEADZONE);
	public static final XboxController operator = new XboxController(DriverUSB.OPERATOR_CONTROLLER, DEFAULT.CONTROLLER_DEADZONE);
	
	public OI() {
		// Put Some buttons on the SmartDashboard
		//SmartDashboard.putData("Deliver Box", new Autonomous());  
		driver.Buttons.A.runCommand(new DriveStraightDistance(200, 0.5), CommandState.WhenPressed); 
		driver.Buttons.RightStick.runCommand(new ShiftGears(), CommandState.WhenPressed);
	}
	
	public static void refreshAll() {
		driver.refresh();
		operator.refresh();
	}
	
	//return the driver's  xbox controller
	public XboxController getDriverXboxControl() {
		return driver;
	}
	
	//return the operator's xbox controller
	public XboxController getOperatorXboxControl() {
		return operator;
	}
}
