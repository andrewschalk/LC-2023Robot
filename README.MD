# Robotics Engineering Club Robot test
The Lewis and Clark Robotics Engineering Club robot. Built on FIRST Robotics Competition systems. Controlled by the logitech F310 controller connected to a laptop as the base station. Laptop communicates to robot over 5GHz Wi-fi. A USB camera transmits a video feed to be viewed by the operator.
## Architecture
The robot runs on the systems used with the FIRST Robotics Competition. Uses [WPILib v.2023](https://docs.wpilib.org/en/stable/).
## Electronics
- Camera is a Lifecam HD-3000. 
- Primary on-board computer is the roboRIO 1.
- Radio is the Open-Mesh OM5P-AC Radio.
- Power distribution is handled by the PDP from CTRE.
### Drivetrain
Four CIM motors each ran with a Victor SPX motor controller.
## Hardware
Built on the [AndyMark 6 wheel drop center drive base](https://www.andymark.com/products/am14u5-6-wheel-drop-center-robot-drive-base-first-kit-of-parts-chassis) in the long configuration.
## Control Scheme
Controlled with the logitech F310 controller on port 0.
- Left stick Y-axis  -- Drive speed
- Right stick X-axis -- Drive rotation
