package frc.subsystems;
import edu.wpi.first.wpilibj.*;


public class Arm {
    
    DigitalInput forwardLimit = new DigitalInput(0);
    DigitalInput bottomLimit  = new DigitalInput(1);

    public Arm() {

    }

    /**
     * Sets the speed of the arm rotation.
     * 
     * @param speed The speed to run the motor at -1 to 1. Negative values are backwards and positive forwards.
     * @return The speed that the arm was just set to. Will return zero if one of the limit switches is activated
     */
    public double setSpeed(double speed) {
        if(forwardLimit.get()||bottomLimit.get()) {
            speed = 0;
        }
        //motor.setSpeed(speed);
        return speed;
    }
}
