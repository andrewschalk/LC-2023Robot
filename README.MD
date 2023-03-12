# Robotics Engineering Club Robot
The Lewis and Clark Robotics Engineering Club robot. Built on FIRST Robotics Competition systems. Controlled by an X-Box style controller connected to a laptop as the base station. Laptop communicates to robot over Wi-fi. A USB camera transmits a video feed to be viewed by the operator.
## Architecture
The robot runs on the systems used with the FIRST Robotics Competition. Uses [WPILib v.2023](https://docs.wpilib.org/en/stable/).
## Electronics
- Camera is a [Lifecam HD-3000](https://www.andymark.com/products/microsoft-lifecam-hd-3000-camera). 
- Primary on-board computer is the [roboRIO 1](https://www.andymark.com/products/ni-roborio).
- Radio is the [Open-Mesh OM5P-AC Radio](https://www.andymark.com/products/open-mesh-om5p-ac-dual-band-1-17-gbps-access-point-radio).
- Power distribution is handled by the [PDP](https://store.ctr-electronics.com/power-distribution-panel/) from CTRE.
### Drivetrain
Four [CIM motors](https://www.andymark.com/products/2-5-in-cim-motor) each ran with a [Victor SPX motor controller](https://www.vexrobotics.com/217-9191.html).
## Hardware
Built on the [AndyMark 6 wheel drop center drive base](https://www.andymark.com/products/am14u5-6-wheel-drop-center-robot-drive-base-first-kit-of-parts-chassis) in the long configuration.
## Control Scheme
Controlled with the [logitech F310](https://www.andymark.com/products/logitech-gamepad-f310) controller.
- Left stick Y-axis  -- Drive speed
- Right stick X-axis -- Drive rotation
