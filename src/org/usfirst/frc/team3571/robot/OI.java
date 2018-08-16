/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3571.robot;

import org.usfirst.frc.team3571.robot.RobotMap.DEFAULT;
import org.usfirst.frc.team3571.robot.RobotMap.DriverUSB;
import org.usfirst.frc.team3571.robot.command.Autonomous;
import org.usfirst.frc.team3571.robot.command.DriveStraightDistance;
import org.usfirst.frc.team3571.robot.command.DriveStraightTimed;
import org.usfirst.frc.team3571.robot.command.LiftCommand;
import org.usfirst.frc.team3571.robot.command.LiftManualCommand;
import org.usfirst.frc.team3571.robot.command.ShiftGears;
import org.usfirst.frc.team3571.robot.command.TiltCommand;
import org.usfirst.frc.team3571.robot.command.TiltManualCommand;
import org.usfirst.frc.team3571.robot.command.OpenCloseCommand;
import org.usfirst.frc.team3571.robot.command.TurnWithDegrees;
import org.usfirst.frc.team3571.robot.utilities.XboxController;
import org.usfirst.frc.team3571.robot.utilities.XboxController.Button;
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
		//SmartDashboard.putData("Deliver Soda", new Autonomous());  
		//driver.Buttons.RB.runCommand(new LiftCommand(RobotMap.LIFT.DOWN), CommandState.WhenPressed);
		//driver.Buttons.LB.runCommand(new LiftCommand(RobotMap.LIFT.UP), CommandState.WhenPressed); 
		//a forward, b reverse
		operator.Buttons.Y.runCommand(new TiltManualCommand(true), CommandState.WhilePressed);
		operator.Buttons.X.runCommand(new TiltManualCommand(false), CommandState.WhilePressed);
		operator.Buttons.RB.runCommand(new LiftManualCommand(true), CommandState.WhilePressed);
		operator.Buttons.LB.runCommand(new LiftManualCommand(false), CommandState.WhilePressed);
		//driver.Buttons.RightStick.runCommand(new ShiftGears(), CommandState.WhenPressed);
		driver.Buttons.A.runCommand(new OpenCloseCommand(true), CommandState.WhenPressed);
		driver.Buttons.B.runCommand(new OpenCloseCommand(false), CommandState.WhenPressed);
		
	}
	
	public static void refreshAll() {
		driver.refresh();
		operator.refresh();
	}
	
	public XboxController getDriverXboxControl() {
		return driver;
	}
	
	public XboxController getOperatorXboxController() {
		return operator;
	}

}
