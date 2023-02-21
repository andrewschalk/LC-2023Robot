# Robotics Engineering Club Robot
Code for the Lewis and Clark robot. Utilizes arcade drive drive train. Controlled by the logitech F310 controller. A USB camera transmits a video feed to the shuffleboard.
## Architecture
The robot runs on the systems used with the FIRST Robotics Competition. Uses [WPILib](https://docs.wpilib.org/en/stable/).
## Electronics
Camera is a Lifecam HD-3000. Primary computer is the roborio 1. 
### Drivetrain
Four CIM motors each ran with a Victor SPX motor controller.
## Hardware
Built on the [andymark 6 wheel drop center drive base](https://www.andymark.com/products/am14u5-6-wheel-drop-center-robot-drive-base-first-kit-of-parts-chassis) in the long configuration.
## Control Scheme
Controlled with the logitech F310 controller on port 0.
- Left stick Y-axis  -- Drive speed
- Right stick X-axis -- Drive rotation
