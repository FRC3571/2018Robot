/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3571.robot;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team3571.robot.command.Autonomous;
import org.usfirst.frc.team3571.robot.command.CenterRun;
import org.usfirst.frc.team3571.robot.command.DriveStraightDistance;
import org.usfirst.frc.team3571.robot.command.DriveStraightTimed;
import org.usfirst.frc.team3571.robot.command.LongRun;
import org.usfirst.frc.team3571.robot.command.ShortRun;
import org.usfirst.frc.team3571.robot.path.PathCommand;
import org.usfirst.frc.team3571.robot.subsystems.DriveTrain;
import org.usfirst.frc.team3571.robot.subsystems.ForkLift;
import org.usfirst.frc.team3571.robot.subsystems.Intake;
import org.usfirst.frc.team3571.robot.subsystems.Pneumatics;
import org.usfirst.frc.team3571.robot.utilities.MPU6050;
import org.usfirst.frc.team3571.robot.utilities.XboxController;
import org.usfirst.frc.team3571.robot.utilities.XboxController.StickSides;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	Autonomous m_autonomousCommand;
	
	public static DriveTrain m_drivetrain;
	public static Pneumatics m_pneumatics;
	public static ForkLift m_forklift;
	public static Intake m_intake;
	public static XboxController driverXbox;
	public static XboxController operatorXbox;
	public static OI m_oi;

	
	//public MPU6050 gyro = new MPU6050();
    //private Compressor c = new Compressor(0);
	//private Encoder enc = new Encoder(0,1);
	//private DigitalInput di = new DigitalInput(9);
	
	//for game input
	private DriverStation driverStation; 
	
	//chooser
	private SendableChooser<Command> chooser;
	
	//signal recieved
	private boolean signalRecieved;
	
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		
		//CameraServer.getInstance().startAutomaticCapture(); //Minimum required for camera
		
		//new Thread(() -> {
             UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
             camera.setResolution(640, 480);
             camera.setWhiteBalanceAuto();
             camera.setFPS(20);
             
             //CvSink cvSink = CameraServer.getInstance().getVideo();
             //CvSource outputStream = CameraServer.getInstance().putVideo("Blur", 640, 480);
            /* 
             Mat source = new Mat();
             Mat output = new Mat();
             
             while(!Thread.interrupted()) {
                 cvSink.grabFrame(source);
                 Imgproc.cvtColor(source, output, Imgproc.COLOR_BGR2GRAY);
                 outputStream.putFrame(output);
             }*/
         //}).start();
		
		
		
		/**
		 * 7780 long run
		 */
		
		driverStation = DriverStation.getInstance();
		// Initialize all subsystems
		m_drivetrain = new DriveTrain();
		//m_pneumatics = new Pneumatics();
		
		/*m_pneumatics.createSolenoid(RobotMap.PNEUMATICS.GEARSHIFT_SOLENOID, 

				RobotMap.PNEUMATICS.SOLENOID_ID_1, 

				RobotMap.PNEUMATICS.SOLENOID_ID_2);
		
		m_pneumatics.createSolenoid(RobotMap.PNEUMATICS.FORKLIFT_SOLENOID, RobotMap.PNEUMATICS.SOLENOID_ID_3, RobotMap.PNEUMATICS.SOLENOID_ID_4);*/
		
		m_forklift = new ForkLift();
		m_intake = new Intake();
		m_oi = new OI();

		// instantiate the command used for the autonomous period
		m_autonomousCommand = new Autonomous();
		driverXbox = m_oi.getDriverXboxControl();
		operatorXbox = m_oi.getDriverXboxControl();
		setupAutoCommands();
		
		//chooser.addDefault("Left Long",new LeftLong());
		
		//SmartDashboard.putData("Auto",chooser);

		// Show what command your subsystem is running on the SmartDashboard
		SmartDashboard.putData(m_drivetrain);
		SmartDashboard.putData(m_forklift);
		
	}

	@Override
	public void autonomousInit() {
		
		System.out.println("Init auto");
		
		Command autoCommand = chooser.getSelected();
		if(autoCommand instanceof PathCommand) {
			PathCommand pathCommand = (PathCommand) autoCommand;
			String signal = "";
			while(!signalRecieved) {
				signal = driverStation.getGameSpecificMessage();
				if(signal.length()==3) {
					signalRecieved = true;
				}
			}
			if(signal.length() <= 0) {
				System.out.println("signal not recieved");
				new DriveStraightDistance(4000,0.75).start();
			}
			else {
				char first = signal.charAt(0);
				char second = signal.charAt(1);
				int position = (int) SmartDashboard.getNumber("position", 2);
				PathCommand.TARGET target = getTarget(first,position);
				pathCommand.start(target==PathCommand.TARGET.SWITCH ? getSide(first) : getSide(second), target, position==2);
			}
		}
		else autoCommand.start();
//		String signal = driverStation.getGameSpecificMessage();
//		int position = (int) SmartDashboard.getNumber("start", 1);
//		m_autonomousCommand.start(signal,position); // schedule the autonomous command (example)
	}
	
	private PathCommand.TARGET getTarget(char first, int position) {
		if(position==2) {
			return PathCommand.TARGET.SWITCH;
		}
		else if(position==1) {
			if(first=='L') {
				return PathCommand.TARGET.SWITCH;
			}
			return PathCommand.TARGET.SCALE;
		}
		else if(first=='R') {
			return PathCommand.TARGET.SWITCH;
		}
		return PathCommand.TARGET.SCALE;
	}
	
	private boolean getSide(char level) {
		return level=='L';
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		log();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		chooser.getSelected().cancel();		
	}

	/**
	 * This function is called periodically during teleoperated mode.
	 */
	@Override
	public void teleopPeriodic() {
		OI.refreshAll();
		Scheduler.getInstance().run();
		log();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic(){
		//Testing xbox buttons, joysticks and triggers
		OI.refreshAll();
//		//System.out.println("Left Y = " + xbox.LeftStick.Y);
//		//System.out.println("Right Y = " + xbox.RightStick.Y);
//		
//		
//		final double cpr = 360.0;
//		final double encoder_angular_distance_per_pulse = 2.0*Math.PI / cpr;
//		final double wheel_radius = 2.5;  // .564 in (18T 5mm pitch)
//		final double encLinearDistancePerPulse = wheel_radius * encoder_angular_distance_per_pulse; //2.0 * Math.PI / cpr;
//		
//		System.out.println(enc.getRaw()*encLinearDistancePerPulse);
//		
		
		//System.out.println(gyro.getAngle(MPU6050.Axis.X));

	    //PNEUMATICS
	    //c.setClosedLoopControl(true);
	    //c.setClosedLoopControl(false);
	}
	
	private void setupAutoCommands() {
		
		chooser = new SendableChooser<Command>();
		
		chooser.addObject("Auto run", new PathCommand());
		chooser.addDefault("3.75m", new DriveStraightDistance(3750,0.5));
		chooser.addObject("Left Short", new ShortRun(true));
		chooser.addObject("Right Short", new ShortRun(false));
		chooser.addObject("Left Long", new LongRun(true));
		chooser.addObject("Right Long", new LongRun(false));
		chooser.addObject("Center Right", new CenterRun(true));
		chooser.addObject("Center Left", new CenterRun(false));
		
		SmartDashboard.putData("Auto", chooser);
		SmartDashboard.putNumber("AutoSpeed",0.75);
		SmartDashboard.putNumber("deadzone", RobotMap.DEFAULT.CONTROLLER_DEADZONE);
		SmartDashboard.putNumber("leftOff", RobotMap.PWM.LEFT_MOTOR_OFFSET);
		SmartDashboard.putNumber("rightOff", RobotMap.PWM.RIGHT_MOTOR_OFFSET);
		//1 left, 2 middle, 3 right (middle default position)
		SmartDashboard.putNumber("position", 2);
	}
	

	/**
	 * The log method puts interesting information to the SmartDashboard.
	 */
	
	void log() {
		m_drivetrain.log();
		m_forklift.log();
	}
}
