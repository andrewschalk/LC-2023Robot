package frc.robot;

import edu.wpi.first.wpilibj2.command.button.*;

public class Controller {
  private static CommandXboxController controller = new CommandXboxController(Constants.f310Port);
  Trigger xButton;

  public Controller() {
    xButton = controller.x().onTrue(Robot.getArm().home());
  }
}