package org.usfirst.frc.team3571.robot.commands;

//import org.usfirst.frc.team3571.robot.OI;
import org.usfirst.frc.team3571.robot.Robot;

//import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.TimedCommand;

/**
 *
 */
public class DriveStraightTimed extends TimedCommand {

	double speed = 0;
	
    public DriveStraightTimed(double timeout, double speed) {
        super(timeout);
        this.speed = speed;
        // Use requires() here to declare subsystem dependencies
        requires(Robot.m_drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {	
    	Robot.m_drivetrain.drive(speed, speed);
    }

    // Called once after timeout
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
