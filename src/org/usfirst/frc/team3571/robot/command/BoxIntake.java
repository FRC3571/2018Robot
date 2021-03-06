package org.usfirst.frc.team3571.robot.command;



import org.usfirst.frc.team3571.robot.Robot;



import edu.wpi.first.wpilibj.command.Command;



/**

 *

 */

public class BoxIntake extends Command {

	double speed = 1;

	

    public BoxIntake() {

    	super();

    	//this.speed = speed;

        // Use requires() here to declare subsystem dependencies

        requires(Robot.m_intake);

    }



    // Called just before this Command runs the first time

    protected void initialize() {

    }



    // Called repeatedly when this Command is scheduled to run

    protected void execute() {

    	Robot.m_intake.runIntake(Robot.m_oi.getOperatorXboxController());

    }



    // Make this return true when this Command no longer needs to run execute()

    protected boolean isFinished() {

        return false;

    }



    // Called once after isFinished returns true

    protected void end() {

    	Robot.m_intake.runIntake(0,null,false);

    }



    // Called when another command which requires one or more of the same

    // subsystems is scheduled to run

    protected void interrupted() {

    }

}