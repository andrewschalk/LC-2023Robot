package frc.sensors;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.cscore.CvSink;
import edu.wpi.first.cscore.CvSource;

public class Camera {
private Thread    visionThread;// Thread containing the CameraServer
private UsbCamera camera;

	/**
	 * Contains a thread that runs a USB camera. Designed for the Lifecam HD-3000.
	 * This will likely plug and play with other usb cameras.
	 * Output stream viewable on shuffleboard.(the stream must be selected, see WPI for camera streams on shuffleboard)
	 * FIXME Find a way to run the camera without creating a new thread. This seems unnecessary and resource intensive.
	 */
  public Camera() {
    visionThread = new Thread(
    () -> {
      // Get usb camera and set its resolution
      camera = CameraServer.startAutomaticCapture();
      camera.setResolution(640, 480);

      // Create a CvSink. This will capture Mats from the camera
      CvSink cvSink = CameraServer.getVideo();

      // Create a CvSource. This will send images back to the Dashboard
      CvSource outputStream = CameraServer.putVideo("camera", 640, 480);

      Mat mat = new Mat();

      // This loop is always running unless the robot is restarted
      while (!Thread.interrupted()) {
      /*
       * Tell the CvSink to grab a frame from the camera and put it
       * in the source mat. If there is an error notify the output.
       */
      if (cvSink.grabFrame(mat) == 0) {
      	outputStream.notifyError(cvSink.getError());// Send the output the error.
        continue;// Skip the rest of the iteration
      }

      // Put a rectangle on the image
      Imgproc.rectangle(
        mat, new Point(100, 100), new Point(400, 400), new Scalar(255, 255, 255), 5);

      // Give the output stream the next image
        outputStream.putFrame(mat);
      }
    });
    visionThread.setDaemon(true);
    visionThread.start();
  }
}
