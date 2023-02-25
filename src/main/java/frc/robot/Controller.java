package frc.robot;

import edu.wpi.first.wpilibj2.command.button.*;

public class Controller {
  private static CommandXboxController controller = new CommandXboxController(Constants.f310Port);// Compatable with f310

  public Controller() {
		// X button homes arm
    controller.x().onTrue(Robot.getArm().home());
  }

	public double getRightX() {
		return controller.getRightX();
	}

	public double getLeftY() {
		return controller.getLeftY();
	}
}