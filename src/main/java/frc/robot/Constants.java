package frc.robot;

/**
 * Holds all the constants for the robot such as device ports and IDs, and physical constants.
 */
public final class Constants {
    
  //CAN IDs
  public static final int backLeftDriveMotorCAN   = 0;
  public static final int frontLeftDriveMotorCAN  = 1;
  public static final int backRightDriveMotorCAN  = 2;
  public static final int frontRightDriveMotorCAN = 3;

  //PWM channels
  public static final int armMotorPWM = 0;

  //DIO channels
  public static final int armForwardLimitDIO = 0;
  public static final int armEncoderADIO     = 1;
  public static final int armEncoderBDIO     = 2;

  //Input device ports
  public static final int f310Port = 0;
}