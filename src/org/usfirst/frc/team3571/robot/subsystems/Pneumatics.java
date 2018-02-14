package org.usfirst.frc.team3571.robot.subsystems;

import java.util.ArrayList;

import org.usfirst.frc.team3571.robot.RobotMap;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Pneumatics extends Subsystem {

	Compressor c;
	ArrayList<DoubleSolenoid> solenoidList;
	
	//boolean enabled = c.enabled();
	//boolean pressureSwitch = c.getPressureSwitchValue();
	//double current = c.getCompressorCurrent();
	

	public Pneumatics(){
		c = new Compressor(RobotMap.PNEUMATICS.PUMP_ID);
		solenoidList = new ArrayList<DoubleSolenoid>();
	}
	
	@Override
	protected void initDefaultCommand() {
		c.setClosedLoopControl(true);	
	}
	
	public void start(){
		c.setClosedLoopControl(true);
	}
	
	public void stop(){
		c.setClosedLoopControl(false);
	}
	
	public void createSolenoid(int solenoidId, int id1, int id2){
		solenoidList.add(solenoidId, new DoubleSolenoid(id1, id2));
	}
	
	public void solenoidOff(int solenoidId){
		DoubleSolenoid ds = solenoidList.get(solenoidId);
		if(solenoidList.get(solenoidId)!= null){
			ds.set(DoubleSolenoid.Value.kOff);
		}
	}
	
	public void solenoidForward(int solenoidId){
		DoubleSolenoid ds = solenoidList.get(solenoidId);
		if(solenoidList.get(solenoidId)!= null){
			ds.set(DoubleSolenoid.Value.kForward);
		}
	}
	
	public void solenoidReverse(int solenoidId){
		DoubleSolenoid ds = solenoidList.get(solenoidId);
		if(solenoidList.get(solenoidId)!= null){
			ds.set(DoubleSolenoid.Value.kReverse);
		}
	}
}
