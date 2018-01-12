package org.usfirst.frc.team3571.robot.utilities;

import edu.wpi.first.wpilibj.SpeedController;

public class MotorControllerJoint implements SpeedController  {
	private final SpeedController cont1, cont2;
	public MotorControllerJoint(SpeedController cont1, SpeedController cont2) {
		this.cont1 = cont1;
		this.cont2 = cont2;
	}
	@Override
	public void pidWrite(double output) {
		cont1.pidWrite(output);
		cont2.pidWrite(output);
		
	}

	@Override
	public void set(double speed) {
		cont1.set(speed);
		cont2.set(speed);
	}

	@Override
	public double get() {
		return cont1.get();
	}

	@Override
	public void setInverted(boolean isInverted) {
		cont1.setInverted(isInverted);
		cont2.setInverted(isInverted);
	}

	@Override
	public boolean getInverted() {
		return cont1.getInverted();
	}

	@Override
	public void disable() {
		cont1.disable();
		cont2.disable();
	}

	@Override
	public void stopMotor() {
		cont1.disable();
		cont2.disable();
	}

}
