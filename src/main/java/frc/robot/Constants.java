package frc.robot;

/**
 * Holds all the constants for the robot such as device ports and IDs, and physical constants.
 */
public final class Constants {
    
  // CAN IDs
  public static final int backLeftDriveMotorCAN   = 0;
  public static final int frontLeftDriveMotorCAN  = 1;
  public static final int backRightDriveMotorCAN  = 2;
  public static final int frontRightDriveMotorCAN = 3;

  // PWM channels
  public static final int armMotorPWM = 0;

  // DIO channels
  public static final int armForwardLimitDIO    = 0;
  public static final int armEncoderADIO        = 1;
  public static final int armEncoderBDIO        = 2;
  public static final int leftDriveEncoderADIO  = 3;
  public static final int rightDriveEncoderADIO = 4;
  public static final int leftDriveEncoderBDIO  = 5;
  public static final int rightDriveEncoderBDIO = 6;

  // Input device ports
  public static final int f310Port = 0;

  // Encoder
  public static final int CountPerRev = 120;// HRPG-ASCA #16c encoder count per rev

  // Physical constants
  public static final double wheelCircumference = Math.PI*3;// 6 inch wheel pi*r^2
}