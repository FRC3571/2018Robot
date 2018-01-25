
/* This class will have functionality of the MPU-6050
 * accelerometer/gyro. It uses the I2C on the RoboRio
 * 
 * I2C port for MPU-6050 is 0x68
 * 
 */
package org.usfirst.frc.team3571.utilities;

import edu.wpi.first.wpilibj.I2C;

public class MPU6050 extends I2C {
	
	//private final static byte MPU6050_ADDRESS = 0x68;
    //private final static int REGISTER_PWR_MGMT_1 = 0x6B;
    //private final static int REGISTER_GYRO = 0x43;
    
    /* 
     * Check MPU6050 Manual for register use, default values and ranges
     * http:// ???
   	*/	
    public static final int DEFAULT_MPU6050_ADDRESS = 0x68;
    public static final I2C.Port DEFAULT_I2C_PORT = I2C.Port.kOnboard;
    //public static final int DEFAULT_DLPF_CFG = 0x06;    
    //public static final int DEFAULT_SMPLRT_DIV = 0x00;    
    //public static final int MPU6050_REG_ADDR_SMPRT_DIV = 0x19; // 25    
    //public static final int MPU6050_REG_ADDR_CONFIG = 0x1A; // 26    
    //public static final int MPU6050_REG_ADDR_GYRO_CONFIG = 0x1B; // 27
    //public static final int MPU6050_REG_ADDR_ACCEL_CONFIG = 0x1C; // 28    
    //public static final int MPU6050_REG_ADDR_INT_ENABLE = 0x1A; // 56    
    public static final int MPU6050_REG_ADDR_PWR_MGMT_1 = 0x6B; // 107
    //public static final int MPU6050_REG_ADDR_PWR_MGMT_2 = 0x6C; // 108
    //public static final int MPU6050_REG_ADDR_ACCEL_XOUT_H = 0x3B; // 59
    //public static final int MPU6050_REG_ADDR_ACCEL_XOUT_L = 0x3C; // 60
    //public static final int MPU6050_REG_ADDR_ACCEL_YOUT_H = 0x3D; // 61   
    //public static final int MPU6050_REG_ADDR_ACCEL_YOUT_L = 0x3E; // 62
    //public static final int MPU6050_REG_ADDR_ACCEL_ZOUT_H = 0x3F; // 63
    //public static final int MPU6050_REG_ADDR_ACCEL_ZOUT_L = 0x40; // 64
    //public static final int MPU6050_REG_ADDR_TEMP_OUT_H = 0x41; // 65
    //public static final int MPU6050_REG_ADDR_TEMP_OUT_L = 0x42; // 66
    //public static final int MPU6050_REG_ADDR_GYRO_XOUT_H = 0x43; // 67
    //public static final int MPU6050_REG_ADDR_GYRO_XOUT_L = 0x44; // 68
    //public static final int MPU6050_REG_ADDR_GYRO_YOUT_H = 0x45; // 69
    //public static final int MPU6050_REG_ADDR_GYRO_YOUT_L = 0x46; // 70
    //public static final int MPU6050_REG_ADDR_GYRO_ZOUT_H = 0x47; // 71
    //public static final int MPU6050_REG_ADDR_GYRO_ZOUT_L = 0x48; // 72
    
    //public static final double RADIAN_TO_DEGREE = 180. / Math.PI;
    //private static final double ACCEL_Z_ANGLE = 0;
    
    //private int dlpfCfg;
    //private int smplrtDiv;
    //private double accelLSBSensitivity;
    //private double gyroLSBSensitivity;    
    //private Thread updatingThread = null;
    //private boolean updatingThreadStopped = true;
    //private long lastUpdateTime = 0;

    
    //private I2C accelerometer = new I2C(I2C.Port.kOnboard, MPU6050_ADDRESS);
    private byte[] buffer = new byte[6];
	
    
    public MPU6050() {
        this(DEFAULT_I2C_PORT, DEFAULT_MPU6050_ADDRESS);//, DEFAULT_DLPF_CFG, DEFAULT_SMPLRT_DIV);
    }
    
    
    public MPU6050(I2C.Port i2cPort, int i2cAddress){ //, int dlpfCfg, int smplrtDiv) {
        super(i2cPort, i2cAddress);
        /*
        this.dlpfCfg = dlpfCfg;
        this.smplrtDiv = smplrtDiv;
        
        updateRegisterValue(MPU6050_REG_ADDR_PWR_MGMT_1, 0x00);        
        updateRegisterValue(MPU6050_REG_ADDR_SMPRT_DIV, smplrtDiv);        
        setDLPFConfig(dlpfCfg);        
        gyroLSBSensitivity = 131.; // cfr [datasheet 2 - p.31]
        updateRegisterValue(MPU6050_REG_ADDR_GYRO_CONFIG, fsSel);        
        byte afsSel = 0; // AFS_SEL full scale range: ± 2g. LSB sensitivity : 16384 LSB/g
        accelLSBSensitivity = 16384.; // LSB Sensitivity corresponding to AFS_SEL 0
        updateRegisterValue(MPU6050_REG_ADDR_ACCEL_CONFIG, afsSel);      
        updateRegisterValue(MPU6050_REG_ADDR_INT_ENABLE, 0x00);        
        updateRegisterValue(MPU6050_REG_ADDR_PWR_MGMT_2, 0x00);        
        calibrateSensors();
        */
    }

    
    
    
    
    //****************************************************************/
    
    
    
    // Convert the raw bytes into measurements in degrees per second
    public double getX(){
    	return gyroFromBytes(buffer[0], buffer[1]);
    }
    
    public double getY(){
    	return gyroFromBytes(buffer[2], buffer[3]);
    }
    
    public double getZ(){
    	return gyroFromBytes(buffer[4], buffer[5]);
    }
  
   
	/** Adapted from {@link ADXL345_I2C#accelFromBytes} */
	private double gyroFromBytes(byte first, byte second) {
	    short tempLow = (short) (first & 0xff);
	    short tempHigh = (short) ((second << 8) & 0xff00);
	    return (tempLow | tempHigh) * 0.004;
	}

}
