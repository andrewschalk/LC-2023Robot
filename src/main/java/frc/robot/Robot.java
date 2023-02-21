/**
 * Author: Andrew Schalk
 * Date: 2/20/2023
 * Description: The first program written for control of the robot. Designed for 
 * 4 Victor SPX motor controllers on drive train. Utilizes arcade drive drive 
 * train. Controlled by the logitech F310 controller on port 0. Left stick 
 * Y-axis controls speed, and right stick X-axis controls turning. A USB camera
 * transmits a video feed to the shuffleboard.
 */
package frc.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.subsystems.*;
import frc.sensors.*;

public class Robot extends TimedRobot {

  private static DriveTrain     driveTrain;// Arcade Drive
  private static Camera         camera;// USB camera, designed to work with the Lifecam HD-3000
  private static XboxController controller;// Compatable with the F310


  @Override
  public void teleopInit() {
    driveTrain = new DriveTrain();
	controller = new XboxController(0);// Controller port can be changed from driver station
  }

  @Override
  public void teleopPeriodic() {
    driveTrain.arcadeDrive(
			controller.getLeftY(), controller.getRightX());// Set speed and rotation of drive train respectively
		SmartDashboard.putNumber("Speed", driveTrain.estimateSpeed());
  }

	public static Camera getCamera(){
		return camera;
	}

	public static DriveTrain getDriveTrain(){
		return driveTrain;
	}
	
	public static XboxController getController(){
		return controller;
	}
}