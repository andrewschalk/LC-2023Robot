package frc.subsystems;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.Encoder;
import frc.robot.Constants;

public class Arm {
    
    private Spark        motor;// Controls rotation of the arm.
    private DigitalInput forwardLimit;// Limit switch on front of robot
    private DigitalInput backLimit;// Limit switch on back of robot
    private double       position;// Angular position of arm in degrees, forward is zero
    private Encoder      encoder;// FIXME find out what type of encoder this actually will be


    /**
     * The arm subsystem. Contains a cim motor on a Spark and two limit switches.
     */
    public Arm() {
        motor        = new Spark(Constants.armMotorPWM);
        forwardLimit = new DigitalInput(Constants.armForwardLimitDIO);
        backLimit    = new DigitalInput(Constants.armBackLimitDIO);
        encoder      = new Encoder(Constants.armEncoderADIO, Constants.armEncoderADIO);
    }

    /**
     * Sets the speed of the arm rotation.
     * 
     * @param speed The speed to run the motor at. Value range -1 to 1.
     * @return The speed that the arm was just set to. Will return zero if one of the limit switches is activated
     */
    public double setSpeed(double speed) {
        // If either limit switch is activated, stop motor
        if(forwardLimit.get()||backLimit.get()) {
            speed = 0;

            // Position is now known, lets set its position.
            if(forwardLimit.get()) {
                position = 0;
                encoder.reset();
            }
            else {
                position = 180;
            }
        }
        motor.set(speed);
        return speed;
    }

    /**
     * Logs the state of all subcomponents to the console.
     */
    public void log() {
        System.out.println("Arm Subcomponent Values");
        System.out.println("Forward Limit Switch: " + forwardLimit.get());
        System.out.println("Back Limit Switch: " + backLimit.get());
        System.out.println("Motor Speed: "+ motor.get());
        System.out.println("Position: "+ position);
    }

    public boolean home() {
        if(!forwardLimit.get()) {
            motor.set(.5);
            return false;
        }
        motor.set(0);
        return true;
    }

    public Boolean getForwardLimit() {
        return forwardLimit.get();
    }

    public Boolean getBackLimit() {
        return backLimit.get();
    }

    /**
     * @return the arm position in degrees, forward is zero
     */
    public double getPosition() {
        return position;
    }
}