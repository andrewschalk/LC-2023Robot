package frc.subsystems;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.Encoder;
import frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.*;

public class Arm extends SubsystemBase {
    
  private Spark        motor;// Controls rotation of the arm.
  private DigitalInput forwardLimit;// Limit switch on front of robot
  private Encoder      encoder;// HRPG-ASCA #16c encoder


  /**
   * The arm subsystem. Contains a cim motor on a Spark and two limit switches.
   */
  public Arm() {
    motor        = new Spark(Constants.armMotorPWM);
    forwardLimit = new DigitalInput(Constants.armForwardLimitDIO);
    encoder      = new Encoder(Constants.armEncoderADIO, Constants.armEncoderADIO);

		encoder.setDistancePerPulse(360/Constants.CountPerRev);// 360 degrees per rev
  }

  /**
   * Sets the speed of the arm rotation.
   * 
   * @param speed The speed to run the motor at. Value range -1 to 1.
   * @return The speed that the arm was just set to. Will return zero if one of the limit switches is activated
   */
  public void setSpeed(double speed) {

		Commands.startEnd(
			// Start by moving motor backwards
			() -> motor.set(speed),
			// End by stopping motor
			() -> motor.set(0),
    	this)
			.until(()-> {
				// If arm hits limits, interupt, otherwise keep going
				if(forwardLimit.get()||encoder.get()>=180)
					return true;
				return false;
			});
  }

	/**
	 * Moves the arm until it reaches the given position.
	 * Uses a simple setpoint feedforward method.
	 * 
	 * @param position The angular position to set the arm. Values range from 0 to 180.
	 * @throws IndexOutOfBoundsException If given position is outside {0,180}.
	 */
	public void setPosition(double position) throws IndexOutOfBoundsException{
		if(position<0||position>180) {
			throw new IndexOutOfBoundsException("Encoder angular position must be within {0,180}");
		}

		// Move arm backwards towards given position
		if(encoder.get()>position) {
		Commands.startEnd(
			// Start by moving motor backwards
			() -> motor.set(-.5),
			// End by stopping motor
			() -> motor.set(0),
    	this)
			.until(()-> {
				// If arm has reached its goal, interupt, otherwise keep going
				if(encoder.get()<=position)
					return true;
				return false;
			});
		}

		// Move arm forwards towards given position
		if(encoder.get()<position) {
			Commands.startEnd(
				// Start by moving motor forwards
				() -> motor.set(.5),
				// End by stopping motor
				() -> motor.set(0),
				this)
				.until(()-> {
					// If arm has reached its goal, interupt, otherwise keep going
					if(encoder.get()>=position)
						return true;
					return false;
				});
			}
	}

  /**
   * Logs the state of relavant values to the console.
   */
  public void log() {
    System.out.println("Arm Subcomponent Values");
		System.out.println("------------------------");
    System.out.println("Forward Limit Switch: " + forwardLimit.get());
    System.out.println("Motor Speed: "+ motor.get());
    System.out.println("Position: "+ encoder.get());
		System.out.println("________________________");
  }

  /**
   * Homes the robot arm using a simple setpoint feedforward method. 
	 * Moves the arm forward until it hits the limit switch, then stops.
   */
  public Command home() {
  	return Commands.startEnd(
      // When called, begin arm move forward
      () -> motor.set(.5),
      // When the command is interupted, stop the arm moving and set home
      () -> {
				motor.set(0);
				encoder.reset();
			},
      this)
      // Interupt this command when the limit switch is hit
      .until(forwardLimit::get)
      // In case of something wrong with the limit switch, timeout after 5 seconds
      .withTimeout(5);
  }

	/** @return returns true if arm is at zero degrees */
  public Boolean getForwardLimit() {
    return forwardLimit.get();
  }

  /** @return the arm position in degrees, forward is zero */
  public double getPosition() {
    return encoder.get();
  }
}