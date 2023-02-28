package frc.robot;

import edu.wpi.first.wpilibj2.command.button.*;
import edu.wpi.first.math.filter.SlewRateLimiter;

public class Controller {
  private static CommandXboxController controller = new CommandXboxController(Constants.f310Port);// Compatable with f310

	private SlewRateLimiter rightXFilter = new SlewRateLimiter(0.5);
	private SlewRateLimiter leftYFilter = new SlewRateLimiter(0.5);
	/**
	 * An f310 controller
	 */
  public Controller() {
		// X button homes arm
    controller.x().onTrue(Robot.getArm().home());
  }

	/**
	 * @return the value of the right stick X axis, ran through a slew filter
	 */
	public double getRightX() {
		return rightXFilter.calculate(controller.getRightX());
	}

	/**
	 * @return the value of the l;eft stick Y axis, ran through a slew filter
	 */
	public double getLeftY() {
		return leftYFilter.calculate(controller.getLeftY());
	}
}