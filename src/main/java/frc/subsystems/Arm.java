package frc.subsystems;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.motorcontrol.Spark;

public class Arm {
    
    Spark motor;// This is the spark on channel zero. Controls rotation of the arm.
    DigitalInput forwardLimit;// Limit switch on front of robot
    DigitalInput backLimit;// Limit switch on back of robot

    /**
     * The arm subsystem on the robot. Contains a cim motor on a Spark and two limit switches.
     */
    public Arm() {
        motor        = new Spark(0);
        forwardLimit = new DigitalInput(0);
        backLimit    = new DigitalInput(1);
    }

    /**
     * Sets the speed of the arm rotation.
     * 
     * @param speed The speed to run the motor at value range -1 to 1.
     * @return The speed that the arm was just set to. Will return zero if one of the limit switches is activated
     */
    public double setSpeed(double speed) {
        // If either limit switch is activated, stop motor
        if(forwardLimit.get()||backLimit.get()) {
            speed = 0;
        }
        motor.set(speed);
        return speed;
    }

    /**
     * Logs the state of all components to the console.
     */
    public void log() {
        System.out.println("Forward Limit Switch: " + forwardLimit.get());
        System.out.println("Back Limit Switch: " + backLimit.get());
        System.out.println("Motor Speed: "+ motor.get());
    }
}
