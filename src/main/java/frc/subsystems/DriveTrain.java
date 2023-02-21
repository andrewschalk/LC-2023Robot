package frc.subsystems;

import frc.robot.Robot;
import com.ctre.phoenix.motorcontrol.can.*;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.drive.*;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import java.util.Date;
import edu.wpi.first.wpilibj.*;

/**
 * An arcade drive drivetrain class. Contains 4 VictorSPX motor controllers on CANID 0-3.
 * Also contains the onboard accelerometer from the roborio, used for calculating speed.
 */
public class DriveTrain {

  private WPI_VictorSPX        victorLF;
  private WPI_VictorSPX        victorLB;
  private MotorControllerGroup rightSide;

  private WPI_VictorSPX        victorRF;
  private WPI_VictorSPX        victorRB;
  private MotorControllerGroup leftSide;

  private static DifferentialDrive differentialDrive;

	private static Accelerometer accelerometer;// Roborio accelerometer

	//Speed estimation variables
	private double estXSpeed = 0;
	private double estYSpeed = 0;
	private double speed;
	private double dXSpeed;
	private double dYSpeed;
	private long   speedTime = 0;
	private long   tempTime;
	private Date   myDate;

  public DriveTrain() {
		/*
		 * Name       CANID
		 * ----------------
		 * Left Front   0
		 * Left Back    1
		 * Right Front  2
		 * Left Back    3
		 */
    victorLF  = new WPI_VictorSPX(0);
    victorLB  = new WPI_VictorSPX(1);
    rightSide = new MotorControllerGroup(victorLF, victorLB);
    victorRF  = new WPI_VictorSPX(2);
    victorRB  = new WPI_VictorSPX(3);
    leftSide  = new MotorControllerGroup(victorRF, victorRB);

    differentialDrive = new DifferentialDrive(rightSide, leftSide);

    // Set factory defaults
    victorLF.configFactoryDefault();
    victorLB.configFactoryDefault();
    victorRF.configFactoryDefault();
    victorRB.configFactoryDefault();

    // Sets motors so that positive is robot forward i.e. green lights on controllers
    victorLF.setInverted(false);
    victorLB.setInverted(false);
    victorRF.setInverted(true);
    victorRB.setInverted(true);

    /*
     * WPI assumes right side of drivetrain is opposite. Since we already inverted
     * the right motors, it should be set to false.
     */
    rightSide.setInverted(false);

		accelerometer = new BuiltInAccelerometer();// Use onboard roborio accelerometer
		myDate = new Date();// This is used in estimateSpeed()
  }

  /**
   * Sets the speed and rotation of the drive train.
	 * 
	 * @param speed The robot's speed along the X axis [-1.0..1.0]. Forward is positive.
	 * @param rotation The robot's rotation rate around the Z axis [-1.0..1.0]. Counterclockwise is positive.
   */
  public void arcadeDrive(double speed, double rotation) {
    differentialDrive.arcadeDrive(speed, rotation);
  }

	/**
 	 * Estimates the speed using data from the accelerometers onboard the roborio.
 	 * Utilizes numerical integration. Extremely imprecise and subject to significant drift.
	 * Should estimate zero speed when robot stopped.
 	 * @return The estimated speed.
 	 */
	public double estimateSpeed(){
				
		/*
		 * If robot not accelerating and joysticks not commanding movement, assume zero speed.
		 * This should help correct drift as it should zero each time the robot stops.
		 * FIXME if autonomous is added. May assume zero speed in auto when non-zero speed.
		 */
		if(Robot.getController().getLeftY()==0&&Robot.getController().getRightX()==0&&accelerometer.getX()==0&&
			accelerometer.getY()==0){
			speed = 0;
			return speed;
		}
		else{
			tempTime  = myDate.getTime();
			// 6/275 is ratio to convert from gforce milliseconds to miles per hour
    		dXSpeed   = (tempTime - speedTime) * accelerometer.getX() * (6 / 275);
    		dYSpeed   = (tempTime - speedTime) * accelerometer.getY() * (6 / 275);
    		estXSpeed = estXSpeed + dXSpeed;// Add the change in speed to the last speed
    		estYSpeed = estYSpeed + dYSpeed;
    		speed     = Math.sqrt((estXSpeed * estXSpeed) + (estYSpeed * estYSpeed));// a^2+b^2=c^2
    		speedTime = tempTime;
		}
		return speed;
	}

	public static Accelerometer getAccelerometer(){
		return accelerometer;
	}
}
