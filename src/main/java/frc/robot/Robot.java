package frc.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.subsystems.*;
import frc.sensors.*;
import edu.wpi.first.wpilibj2.command.*;

public class Robot extends TimedRobot {

	private static DriveTrain driveTrain;// Arcade Drive
	private static Camera     camera;// USB camera, designed to work with the Lifecam HD-3000
	private static Controller controller;// Compatable with the F310
	private static Arm        arm;


  @Override
  public void teleopInit() {
		//subsystems
		arm        = new Arm();
  	driveTrain = new DriveTrain();

		// User input
		controller = new Controller();// Controller port can be changed from driver station
  }

  @Override
  public void teleopPeriodic() {
		// Set speed and rotation of drive train respectively
		new RunCommand(() -> driveTrain.arcadeDrive(
    controller.getLeftY(),
    controller.getRightX()),
    driveTrain);

		SmartDashboard.putNumber("Speed", driveTrain.estimateSpeed());
  }

	/**
   * Logs the state of all subcomponents to the console.
   */
	public void log() {
		arm.log();
		driveTrain.log();
		controller.log();
	}

	public static Camera getCamera() {
		return camera;
	}

	public static DriveTrain getDriveTrain() {
		return driveTrain;
	}
	
	public static Controller getController() {
		return controller;
	}

	public static Arm getArm() {
		return arm;
	}
}